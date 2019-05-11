/**
 * a vue object for course.html
 */
const vm = new Vue({
    el:'#app',
    data: function(){
        return {
            courses:[]
        }
    },
    created:function(){
        this.loadData();
    },
    methods:{
        //跳转添加页面
        add:function(){
            window.location.href = "/course/add";
        },
        //跳转编辑页面
        edit:function(){
            window.location.href = "/user/edit";
        },
        //跳转打卡页面
        daka:function(){
            window.location.href = "/user/daka";
        },
        //加载分类数据
        loadData:function(){
            let _this = this;
            $.ajax({
                type: "GET",
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
                if (!isNaN($(this).val())) {
                    ids.push($(this).val());
                }
            });
            if(ids.length==0){
                $('#checkDel').modal();
                return false;
            }
            this.delByIds(ids);
        },
        //进行批量删除
        delByIds:function(ids){
            let _this = this;
            $('#delByIds').modal({
                onConfirm: function() {
                    alert('你要批量删除的链接 ID 为' + ids);
                },
                onCancel: function() {
                    alert('算求，不弄了');
                }
            });
        },
        //多选框全选/反选
        checkAll:function(){
            $('tbody input[type="checkbox"]').prop('checked',$("#checkAll").prop('checked'));
        },
        //删除的确定/取消
        del:function(id){
            let _this = this;
            $('#del').modal({
                onConfirm: function() {
                    alert('你要删除的链接 ID 为' + id);
                },
                onCancel: function() {
                    alert('算求，不弄了');
                }
            });
        }
    }
})