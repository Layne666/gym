/**
 * a vue object for coach-edit.html
 */
const vm = new Vue({
    el:'#app',
    data: function(){
        return {
            param:{
                username:'',
                password:'',
                coach:{
                    name:'',
                    sex:'',
                    age:'',
                    tel:''
                }
            }
        }
    },
    mounted:function(){
        this.loadData();
    },
    methods:{
        submitEdit:function(){
            let _this = this;
            $.ajax({
                type: "POST",
                url: "/coach/edit",
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify(_this.param),
                dataType: "json",
                success: function () {
                    window.location.href="/coach";
                }
            });
        },
        loadData:function(){
            let _this = this;
            let bh = localStorage.getItem("accountBh");
            _this.param.bh = bh;
            $.ajax({
                type: "GET",
                url: "/coach/edit/"+bh,
                dataType: "json",
                success: function (result) {
                    _this.param = result.data;
                }
            });
        }
    }
})