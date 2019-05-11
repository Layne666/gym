/**
 * a vue object for course-add.html
 */
const vm = new Vue({
    el:'#app',
    data: function(){
        return {
            name:''
        }
    },
    methods:{
        submitAdd:function(){
            let _this = this;
            $.ajax({
                type: "POST",
                url: "/course/add",
                data: {name: _this.name},
                dataType: "json",
                success: function () {
                    window.location.href="/course";
                }
            });
        }
    }
})