/**
 * a vue object for center-data.html
 */
const vm = new Vue({
    el:'#app',
    data:{
        account:{
            username:'',
            password:'',
            coach:{
                name:'',
                sex:'',
                age:'',
                tel:''
            }
        }
    },
    mounted:function(){
        this.loadData();
    },
    methods:{
        loadData:function(){
            let _this = this;
            $.ajax({
                type:'post',
                url:'/center/data',
                data:_this.account,
                dataType:'json',
                success:function(result){
                    _this.account = result.data;
                }
            });
        },
        submitEdit: function(){
            let _this = this;
            if(_this.account.coach.name==''){
                alert("姓名不能为空！");
                return;
            }
            if(_this.account.coach.sex==''){
                alert("性别不能为空！");
                return;
            }
            if(_this.account.coach.age==''){
                alert("年龄不能为空！");
                return;
            }
            if(_this.account.coach.tel==''){
                alert("电话号码不能为空！");
                return;
            }
            if(_this.account.username==''){
                alert("登录名不能为空！");
                return;
            }
            if(_this.account.password==''){
                alert("登录密码不能为空！");
                return;
            }
            $.ajax({
                type:'post',
                url:'/center/data/edit',
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify(_this.account),
                dataType:'json',
                success:function(result){
                    alert(result.message);
                    if(result.success){
                        window.location.href="/center/data";
                    }
                }
            });
        }
    }

})