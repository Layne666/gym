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
            newKsjg:'',
            dkks:'1',
            dkksjg:''
        }
    },
    mounted:function(){
        this.loadData();
    },
    watch:{
        dkks:function(val){
            this.dkksjg = '￥' + val*this.param.ksjg;
        },
    },
    methods:{
        submitEdit:function(){
            let _this = this;
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
                    _this.newKsjg = '￥' + result.data.ksjg;
                    _this.dkksjg = '￥' + result.data.ksjg;
                }
            });
        }
    }
})