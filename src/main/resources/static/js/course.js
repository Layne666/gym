/**
 * a vue object for course.html
 */
const vm = new Vue({
    el:'#app',
    data: function(){
        return {
            courses:[],
            page:{
                //当前页
                pageNum:'',
                //总页数
                pages:'',
                //总个数
                total:''
            },
            name:''
        }
    },
    mounted:function(){
        this.loadData();
    },
    methods:{
        //跳转添加页面
        add:function(){
            window.location.href = "/course/add";
        },
        //跳转编辑页面
        edit:function(bh){
            localStorage.setItem("courseBh", bh);
            window.location.href = "/course/edit";
        },
        //跳转打卡页面
        daka:function(){
            window.location.href = "/user/daka";
        },
        //加载分类数据
        loadData:function(pageNum){
            let _this = this;
            $.ajax({
                type: "POST",
                url: "/course",
                data:{
                    pageNum:pageNum,
                    name:_this.name
                },
                dataType: "json",
                success: function (result) {
                    _this.courses = result.data.list;
                    _this.page.pageNum = result.data.pageNum;
                    _this.page.pages = result.data.pages;
                    _this.page.total = result.data.total;
                }
            });
        },
        //批量删除的点击事件
        batchDelete:function(){
            let ids = new Array();
            let $cks = $('tbody input[type="checkbox"]:checked');
            $cks.each(function(){
                ids.push($(this).val());
            });
            if(ids.length==0){
                $('#checkDel').modal();
                return false;
            }
            this.delByIds(ids);
        },
        //进行批量删除
        delByIds:function(bhs){
            $('#delByIds').modal({
                onConfirm: function() {
                    $.ajax({
                        type: "POST",
                        url: "/course/del",
                        data:{bhs:bhs},
                        success: function () {
                            window.location.href="/course";
                        }
                    });
                }
            });
        },
        //多选框全选/反选
        checkAll:function(){
            $('tbody input[type="checkbox"]').prop('checked',$("#checkAll").prop('checked'));
        },
        //删除的确定/取消
        del:function(bh){
            $('#del').modal({
                onConfirm: function() {
                    $.ajax({
                        type: "POST",
                        url: "/course/del/"+bh,
                        dataType: "json",
                        success: function () {
                            window.location.href="/course";
                        }
                    });
                }
            });
        }
    }
})