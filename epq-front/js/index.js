window.onload = function () {
    new Vue({
        el: '#my',//element
        data: { //数据
            name: 'sonia',
            num: 0,//轮播标记
            flag:0,//做题标记
            progress:"",//进度条宽度,
            styleFlag:960519152,//选中的标记 
            answerMap:new Map(),
            form: {
                username: '',
                password: ''
            },
            imges: [
                    // 'img/banner1.jpg',
                    // 'img/banner2.jpg',
                    'img/banner3.jpg',
                    'img/banner4.jpg',
                    // 'img/banner5.jpg',
                    // 'img/logo.gif',
                ],
            imgess: [
                'img/logo.gif',
            ],    
            questionsArry: "",
            chooseArry: ""

        },
        methods: {   //方法
            numChange() {
                if (this.num == this.imges.length-1) {
                    this.num = 0;
                }else{
                    this.num++;
                }  
            },
            timeChange() {
                setInterval(this.numChange, 3000);
            },
            //上一题
            backQuestion(index){  
                if(this.flag==0){//当下标为0时，停止--
                    this.flag=0;
                }else{
                    this.flag--;
                }
                //修改进度条进度
                this.progress=Math.round((this.flag*100/(this.questionsArry.length-1)));
                //还原
                //this.styleFlag=960519152;
                this.styleFlag=this.answerMap.get(index-1);
            },
            //下一题
            nextQuestion(index){
                //console.log("这是下标为"+index+"的题");              
                if(this.answerMap.get(index)>=0){
                    if(this.flag==this.questionsArry.length-1){ //当下标和题目数组长度-1相等时，重置此标记为数组长度，防止数组超出范围
                        this.flag=this.questionsArry.length-1; //小标要比长度少一位，所以把长度-1 把值给下标
                    }else{
                        this.flag++;             
                    }
                    //修改进度条进度
                    this.progress=Math.round(((this.flag)*100/(this.questionsArry.length-1)));
                    //如果是最后一条则进行弹出框提示已经完成 
                    if(index==this.questionsArry.length-1){
                        this.$message({
                            message: '提交成功！',
                            type: 'success',
                            center: true
                          });
                          console.log(this.answerMap);
                          let answerInfo="";
                          for(let[key,val] of this.answerMap){
                                console.log(key+"******"+val);
                                //answerInfo+="下标为"+key+"的题目,选项下标为"+val+"***********";
                                answerInfo+="第"+(key+1)+"题:"+(val+1)+"；";
                          }
                          alert(answerInfo)
                          console.log(answerInfo);
                    }else{
                        //如果不是最后一条值则把下一题的下标给他
                        this.styleFlag=this.answerMap.get(index+1);
                    }
                }else{  //如果此题没有进行选择，那么就不能继续进行（flag也不能在进行累加）
                    this.$message({
                        message: '当前题目未选择！',
                        type: 'warning'
                    });
                    return false;
                }          
            },
            //选择
            getChoose(index,indexs){
                console.log("这是下标为"+index+"的题,选择的是下标为"+indexs+"项答案");
                //将已选中的下标传过来，并把值赋予给我们声明的标记，让表达式成立，因为下标是唯一的，就保证了样式的唯一性
                this.styleFlag=indexs;
                //this.nextQuestion();
                //var info=document.getElementById("list-group-item-id").getElementsByTagName("li");  
                //将题号和选项放入map对象
                this.answerMap.set(index,indexs);
            },
            //提交
            submit(){
                //判断最后一题是否做了
                this.nextQuestion(this.questionsArry.length-1);  
            }
        },
        mounted: function () {//生命周期函数 每次进入就会调用
            // this.timeChange();
            this.questionsArry = [
                "1.你是否有广泛的爱好?","2.在做任何事情之前，你是否都要考虑一番?","3.你的情绪时常波动吗?",
                "4.当别人做了好事，而周围的人却认为是你做的时候，你是否感到洋洋得意","5.你是一个健谈的人吗?"

            ];
            this.chooseArry = [
                ["是", "否"],
                ["是", "否"],
                ["是", "否"],
                ["是", "否"],
                ["是", "否"]
            ];
        }
    })
}