/**
 * a vue object for record.html
 */
const vm = new Vue({
    el:'#app',
    data:function(){
        return {
            records:'',
            totalPrice:'',
            page:{
                //当前页
                pageNum:'',
                //总页数
                pages:'',
                //总个数
                total:''
            },
            courses:[],
            name:'',
            courseBh:''
        }
    },
    watch: {
        courseBh: function () {
            this.loadData();
            this.loadCourse();
        }
    },
    mounted:function(){
        this.loadData();
        this.loadCourse();
    },
    methods:{
        loadData:function(pageNum){
            let _this = this;
            $.ajax({
                type: "POST",
                url: "/record",
                data:{
                    pageNum:pageNum,
                    name:_this.name,
                    courseBh:_this.courseBh
                },
                dataType: "json",
                success: function (result) {
                    _this.records = result.data.pageInfo.list;
                    _this.totalPrice = result.data.totalPrice;
                    _this.page.pageNum = result.data.pageInfo.pageNum;
                    _this.page.pages = result.data.pageInfo.pages;
                    _this.page.total = result.data.pageInfo.total;
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
                        url: "/record/del",
                        data:{bhs:bhs},
                        success: function () {
                            window.location.href="/record";
                        }
                    });
                }
            });
        },
        //多选框全选/反选
        checkAll:function(){
            $('tbody input[type="checkbox"]').prop('checked',$("#checkAll").prop('checked'));
        },
        del:function(bh){
            $('#del').modal({
                onConfirm: function() {
                    $.ajax({
                        type: "POST",
                        url: "/record/del/"+bh,
                        dataType: "json",
                        success: function () {
                            window.location.href="/record";
                        }
                    });
                }
            });
        },
        //导出
        exportExcel:function(){
            window.location.href = "/record/export?name=" + encodeURIComponent(this.name);
        }
    }

})