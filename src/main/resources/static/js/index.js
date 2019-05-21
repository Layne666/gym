/**
 * a vue object for index.html
 */
const vm = new Vue({
    el:'#app',
    data:function(){
        return {
            userNum:'',
            coachNum:'',
            courseNum:'',
            totalPrice:'',
            month:{
                bh:'',
                records:[],
                skks:'',
                kszj:'',
                srzb:''
            }
        }
    },
    mounted:function(){
        this.getUserNum();
        this.getCoachNum();
        this.getCourseNum();
        this.getTotalPrice();
        this.loadData();
    },
    methods:{
        loadData:function(){
            let _this = this;
            $.ajax({
                type: "POST",
                url: "/record/month",
                dataType: "json",
                success: function (result) {
                    _this.month.records = result.data.records;
                    _this.month.skks = result.data.skks;
                    _this.month.kszj = result.data.kszj;
                    _this.month.srzb = result.data.srzb;
                }
            });
        },
        getUserNum:function(){
            let _this = this;
            $.ajax({
                type: "POST",
                url: "/user/count",
                dataType: "json",
                success: function (result) {
                    _this.userNum = result.data;
                }
            });
        },
        getCoachNum:function(){
            let _this = this;
            $.ajax({
                type: "POST",
                url: "/coach/count",
                dataType: "json",
                success: function (result) {
                    _this.coachNum = result.data;
                }
            });
        },
        getCourseNum:function(){
            let _this = this;
            $.ajax({
                type: "POST",
                url: "/course/count",
                dataType: "json",
                success: function (result) {
                    _this.courseNum = result.data;
                }
            });
        },
        getTotalPrice:function(){
            let _this = this;
            $.ajax({
                type: "POST",
                url: "/record/count",
                dataType: "json",
                success: function (result) {
                    _this.totalPrice = result.data;
                }
            });
        }
    }

})