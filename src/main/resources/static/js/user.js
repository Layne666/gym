/**
 * a vue object for user.html
 */
const vm = new Vue({
    el:'#app',
    data:{},
    methods:{
        add:function(){
            window.location.href = "/user/add";
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

        edit:function(){
            window.location.href = "/user/edit";
        },
        daka:function(){
            window.location.href = "/user/daka";
        },
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