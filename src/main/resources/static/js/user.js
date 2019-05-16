/**
 * a vue object for user.html
 */
const vm = new Vue({
    el:'#app',
    data:function(){
        return {
            kss:[],
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
        add:function(){
            window.location.href = "/user/add";
        },
        edit:function(bh){
            localStorage.setItem("ksBh", bh);
            window.location.href = "/user/edit";
        },
        daka:function(bh){
            localStorage.setItem("ksBh", bh);
            window.location.href = "/user/daka";
        },
        loadData:function(pageNum){
            let _this = this;
            $.ajax({
                type: "POST",
                url: "/user",
                data:{
                    pageNum:pageNum,
                    name:_this.name
                },
                dataType: "json",
                success: function (result) {
                    _this.kss = result.data.list;
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
                        url: "/user/del",
                        data:{bhs:bhs},
                        success: function () {
                            window.location.href="/user";
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
                        url: "/user/del/"+bh,
                        dataType: "json",
                        success: function () {
                            window.location.href="/user";
                        }
                    });
                }
            });
        },
        //导出
        exportExcel:function(){
            window.location.href = "/user/export?name=" + encodeURIComponent(this.name);
        }
    }

})