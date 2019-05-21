/**
 * a vue object for user-edit.html
 */
const vm = new Vue({
    el:'#app',
    data: function(){
        return {
            param:{
                bh:'',
                user:{
                    bh:'',
                    name:'',
                    sex:'',
                    age:'',
                    tel:'',
                },
                course:{
                    bh:'',
                    name:''
                },
                sysks:'',
                ksjg:''
            },
            courses:[]
        }
    },
    mounted:function(){
        this.loadCourse();
        this.loadData();
    },
    methods:{
        submitEdit:function(){
            let _this = this;
            if(_this.param.user.name==''){
                alert("姓名不能为空！");
                return;
            }
            if(_this.param.user.sex==''){
                alert("性别不能为空！");
                return;
            }
            if(_this.param.user.age==''){
                alert("年龄不能为空！");
                return;
            }
            if(_this.param.user.tel==''){
                alert("电话不能为空！");
                return;
            }
            if(_this.param.course.bh==''){
                alert("课程分类不能为空！");
                return;
            }
            if(_this.param.sysks==''){
                alert("剩余课时数不能为空！");
                return;
            }
            if(_this.param.ksjg==''){
                alert("课时价格不能为空！");
                return;
            }
            $.ajax({
                type: "POST",
                url: "/user/edit",
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify(_this.param),
                dataType: "json",
                success: function () {
                    window.location.href="/user";
                }
            });
        },
        loadCourse:function(){
            let _this = this;
            $.ajax({
                type: "POST",
                url: "/course/list",
                dataType: "json",
                success: function (result) {
                    _this.courses = result.data;
                }
            });
        },
        loadData:function(){
            let _this = this;
            let bh = localStorage.getItem("ksBh");
            _this.param.bh = bh;
            $.ajax({
                type: "GET",
                url: "/user/edit/"+bh,
                dataType: "json",
                success: function (result) {
                    _this.param = result.data;
                }
            });
        }
    }
})