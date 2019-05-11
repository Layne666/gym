/**
 * a vue object for center-data.html
 */
const vm = new Vue({
    el:'#app',
    data:{
        account:{
            username:'',
            password:'',
            coach:{
                name:'',
                sex:'',
                age:'',
                tel:''
            }
        }
    },
    methods:{
        tijiao: function(){
            let _this = this;
            $.ajax({
                type:'post',
                url:'/center/data/edit',
                data:_this.account,
                dataType:'json',
                success:function(result){

                }
            });
        }
    }

})