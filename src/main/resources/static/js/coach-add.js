/**
 * a vue object for user-add.html
 */
const vm = new Vue({
    el:'#app',
    data: function(){
        return {
            param:{
                name:'',
                sex:'',
                age:'',
                tel:'',
                username:'',
                password:''
            }
        }
    },
    methods:{
        submitAdd:function(){
            let _this = this;
            if(_this.param.name==''){
                alert("姓名不能为空！");
                return;
            }
            if(_this.param.sex==''){
                alert("性别不能为空！");
                return;
            }
            if(_this.param.age==''){
                alert("年龄不能为空！");
                return;
            }
            if(_this.param.tel==''){
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
                url: "/coach/add",
                data: _this.param,
                dataType: "json",
                success: function () {
                    window.location.href="/coach";
                }
            });
        }
    }
})