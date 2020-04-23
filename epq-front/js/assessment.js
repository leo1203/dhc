window.onload = function () {
    new Vue({
        el: '#my',//element
        data: { //数据
            name: 'Test',
            num: 0,//轮播标记
            flag: 0,//做题标记
            progress: "",//进度条宽度,
            styleFlag: 960519152,//选中的标记
            answerMap: new Map(),
            user: {
                name: '',
                tel: '',
                gender: "",
                age: ""
            },
            questionsArry: [],
            chooseArry: []

        },
        methods: {   //方法
            //上一题
            backQuestion(index) {
                if (this.flag == 0) {//当下标为0时，停止--
                    this.flag = 0;
                } else {
                    this.flag--;
                }
                //修改进度条进度
                this.progress = Math.round((this.flag * 100 / (this.questionsArry.length - 1)));
                //还原
                //this.styleFlag=960519152;
                this.styleFlag = this.answerMap.get(index - 1);
            },
            //下一题
            nextQuestion(index) {
                //console.log("这是下标为"+index+"的题");              
                if (this.answerMap.get(index) >= 0) {
                    if (this.flag == this.questionsArry.length - 1) { //当下标和题目数组长度-1相等时，重置此标记为数组长度，防止数组超出范围
                        this.flag = this.questionsArry.length - 1; //小标要比长度少一位，所以把长度-1 把值给下标
                    } else {
                        this.flag++;
                    }
                    //修改进度条进度
                    this.progress = Math.round(((this.flag) * 100 / (this.questionsArry.length - 1)));
                    //如果是最后一条则进行弹出框提示已经完成 
                    if (index == this.questionsArry.length - 1) {
                        this.$message({
                            message: '提交成功！',
                            type: 'success',
                            center: true
                        });
                        console.log(this.answerMap);
                        let answerInfo = {};
                        for (let [key, val] of this.answerMap) {
                            console.log(key + "******" + val);
                            //answerInfo+="下标为"+key+"的题目,选项下标为"+val+"***********";
                            // answerInfo += "第" + (key + 1) + "题:" + (val + 1) + "；";
                            let qid = this.questionsArry[key].id;
                            answerInfo[qid] = val;
                        }
                        console.log(answerInfo);
                        this.score(answerInfo);
                    } else {
                        //如果不是最后一条值则把下一题的下标给他
                        this.styleFlag = this.answerMap.get(index + 1);
                    }
                } else {  //如果此题没有进行选择，那么就不能继续进行（flag也不能在进行累加）
                    this.$message({
                        message: '当前题目未选择！',
                        type: 'warning'
                    });
                    return false;
                }
            },
            //选择
            getChoose(index, indexs) {
                console.log("这是下标为" + index + "的题,选择的是下标为" + indexs + "项答案");
                //将已选中的下标传过来，并把值赋予给我们声明的标记，让表达式成立，因为下标是唯一的，就保证了样式的唯一性
                this.styleFlag = indexs;
                //this.nextQuestion();
                //var info=document.getElementById("list-group-item-id").getElementsByTagName("li");  
                //将题号和选项放入map对象
                this.answerMap.set(index, indexs);
            },
            //提交
            submit() {
                //判断最后一题是否做了
                this.nextQuestion(this.questionsArry.length - 1);
            },
            //获取所有题
            getQuestions() {
                axios.get("http://localhost:8888/epqapi/epq/getQuestion")
                    .then(response => {
                        console.log("123123123123131231")
                        console.log(response);
                        let quesObj = response.data.data;
                        console.log(quesObj);
                        for (let key in quesObj) {
                            let qarray = {};
                            qarray.id = key;
                            qarray.title = quesObj[key];
                            // if (key < 10) {
                            this.questionsArry.push(qarray);
                            // }
                        }
                        for (var i = 0; i < this.questionsArry.length; i++) {
                            this.chooseArry.push(["没有", "较轻", "中等", "较重", "严重"]);
                        }
                        // console.log(this.chooseArry);
                    })
                    .catch(error => {
                        console.log(error);
                    });
            },
            score(answerInfo) {

                if (!this.user.name) {
                    this.$message({
                        message: '请输入姓名！',
                        type: 'warning'
                    });
                    return;
                }
                if (!this.user.tel) {
                    this.$message({
                        message: '请输入电话！',
                        type: 'warning'
                    });
                    return;
                }
                if (!this.user.age) {
                    this.$message({
                        message: '请输入年龄！',
                        type: 'warning'
                    });
                    return;
                }
                if (!this.user.gender) {
                    this.$message({
                        message: '请输入性别！',
                        type: 'warning'
                    });
                    return;
                }

                let params = new URLSearchParams();
                params.append("answers", JSON.stringify(answerInfo));
                params.append("name", this.user.name);
                params.append("tel", this.user.tel);
                params.append("gender", this.user.gender);
                params.append("age", this.user.age);
                axios.post("http://localhost:8888/epqapi/epq/submit", params)
                    .then(response => {
                        console.log(response);
                        location.reload();
                    })
                    .catch(error => {
                        console.log(error);
                    });
            }
        },
        mounted: function () {//生命周期函数 每次进入就会调用
            this.getQuestions();
        }
    })
};