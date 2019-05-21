/**
 * a vue object for course-edit.html
 */
const vm = new Vue({
    el:'#app',
    data: function(){
        return {
            name:''
        }
    },
    mounted:function(){
        this.loadData();
    },
    methods:{
        saveEdit:function(){
            let _this = this;
            if(_this.name==''){
                alert("分类名称不能为空！");
                return;
            }
            let bh = localStorage.getItem("courseBh");
            $.ajax({
                type: "POST",
                url: "/course/edit",
                data: {
                    name: _this.name,
                    bh:bh
                },
                dataType: "json",
                success: function () {
                    window.location.href="/course";
                }
            });
        },
        loadData:function(){
            let _this = this;
            let bh = localStorage.getItem("courseBh");
            $.ajax({
                type: "GET",
                url: "/course/edit/"+bh,
                dataType: "json",
                success: function (result) {
                    _this.name = result.data.name;
                }
            });
        }
    }
})