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
                username:'',
                password:''
            }
        }
    },
    methods:{
        submitAdd:function(){
            let _this = this;
            $.ajax({
                type: "POST",
                url: "/coach/add",
                data: _this.param,
                dataType: "json",
                success: function () {
                    window.location.href="/coach";
                }
            });
        }
    }
})