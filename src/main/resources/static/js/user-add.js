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
                courseBh:'',
                sysks:'',
                ksjg:''
            },
            courses:[]
        }
    },
    mounted:function(){
        this.loadData();
    },
    methods:{
        submitAdd:function(){
            let _this = this;
            $.ajax({
                type: "POST",
                url: "/user/add",
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
            $.ajax({
                type: "POST",
                url: "/course/list",
                dataType: "json",
                success: function (result) {
                    _this.courses = result.data;
                }
            });
        },
    }
})