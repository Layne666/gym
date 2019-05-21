/**
 * a vue object for coach-edit.html
 */
const vm = new Vue({
    el:'#app',
    data: function(){
        return {
            param:{
                username:'',
                password:'',
                coach:{
                    name:'',
                    sex:'',
                    age:'',
                    tel:''
                }
            }
        }
    },
    mounted:function(){
        this.loadData();
    },
    methods:{
        submitEdit:function(){
            let _this = this;
            if(_this.param.coach.name==''){
                alert("姓名不能为空！");
                return;
            }
            if(_this.param.coach.sex==''){
                alert("性别不能为空！");
                return;
            }
            if(_this.param.coach.age==''){
                alert("年龄不能为空！");
                return;
            }
            if(_this.param.coach.tel==''){
                alert("电话不能为空！");
                return;
            }
            if(_this.param.username==''){
                alert("登录名不能为空！");
                return;
            }
            if(_this.param.password==''){
                alert("登录密码不能为空！");
                return;
            }
            $.ajax({
                type: "POST",
                url: "/coach/edit",
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify(_this.param),
                dataType: "json",
                success: function (result) {
                    if(result.success){
                        window.location.href="/coach";
                    }else {
                        alert(result.message);
                    }
                }
            });
        },
        loadData:function(){
            let _this = this;
            let bh = localStorage.getItem("accountBh");
            _this.param.bh = bh;
            $.ajax({
                type: "GET",
                url: "/coach/edit/"+bh,
                dataType: "json",
                success: function (result) {
                    _this.param = result.data;
                }
            });
        }
    }
})