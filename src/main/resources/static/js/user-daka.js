/**
 * a vue object for user-daka.html
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
        submitDaka:function(){
            let _this = this;
            if(parseInt(_this.dkks)>parseInt(_this.param.sysks)){
                alert("打卡课时超过剩余课时..请重新填写");
                return;
            }
            $.ajax({
                type: "POST",
                url: "/user/daka",
                data: {
                    bh:_this.param.bh,
                    dkks:_this.dkks
                },
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