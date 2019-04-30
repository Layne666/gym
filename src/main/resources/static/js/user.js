/**
 * a vue object for user.html
 */
const vm = new Vue({
    el:'#app',
    data:{},
    methods:{
        edit:function(){
            window.location.href = "/user/edit";
        },
        daka:function(){
            window.location.href = "/user/daka";
        }
    }

})