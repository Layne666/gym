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
                courseBh:'',
                sysks:'',
                ksjg:''
            },
            courses:[]
        }
    },
    mounted:function(){
        this.loadData();
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
            if(_this.param.courseBh==''){
                alert("课程分类不能为空！");
                return;
            }
            if(_this.param.sysks==''){
                alert("剩余课时数不能为空！");
                return;
            }
            if(_this.param.sysks<0){
                alert("剩余课时数不能为负数！");
                return;
            }
            if(_this.param.ksjg==''){
                alert("课时价格不能为空！");
                return;
            }
            if(_this.param.ksjg<0){
                alert("课时价格不能为负数！");
                return;
            }
            $.ajax({
                type: "POST",
                url: "/user/add",
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify(_this.param),
                dataType: "json",
                success: function () {
                    window.location.href="/user";
                }
            });
        },
        loadData:function(){
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
    }
})