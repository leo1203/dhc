window.onload = function () {
    new Vue({
            el: "#score",
            data: {
                value2: [],
                currentPage: 1,
                totalPage: 0,
                pageSize: 8,
                tableData: [],
                questions: new Map(),
                dialogTableVisible: false,
                gridData: [],
                detailsName: "",
                detailsTel: "",
                info:["主要反映身体不适感，包括心血管、胃肠道、呼吸和其他系统的不适，和头痛、背痛、肌肉酸痛，以及焦虑等躯体不适表现。\n" +
                "该分量表的得分在0-48分之间。得分在24分以上，表明个体在身体上有较明显的不适感，并常伴有头痛、肌肉酸痛等症状。得分在12分以下，躯体症状表现不明显。总的说来，得分越高，躯体的不适感越强；得分越低，症状体验越不明显。",
                    "主要指那些明知没有必要，但又无法摆脱的无意义的思想、冲动和行为，还有一些比较一般的认知障碍的行为征象也在这一因子中反映。\n" +
                    "该分量表的得分在0-40分之间。得分在20分以上，强迫症状较明显。得分在10分以下，强迫症状不明显。总的说来，得分越高，表明个体越无法摆脱一些无意义的行为、思想和冲动，并可能表现出一些认知障碍的行为征兆。得分越低，表明个体在此种症状上表现越不明显，没有出现强迫行为。",
                    "主要是指某些人际的不自在与自卑感，特别是与其他人相比较时更加突出。在人际交往中的自卑感，心神不安，明显的不自在，以及人际交流中的不良自我暗示，消极的期待等是这方面症状的典型原因。\n" +
                    "该分量表的得分在0-36分之间。得分在18分以上，表明个体人际关系较为敏感，人际交往中自卑感较强，并伴有行为症状（如坐立不安，退缩等）。得分在9分以下，表明个体在人际关系上较为正常。总的说来，得分越高，个体在人际交往中表现的问题就越多，自卑，自我中心越突出，并且已表现出消极的期待。得分越低，个体在人际关系上越能应付自如，人际交流自信、胸有成竹，并抱有积极的期待。",
                    "苦闷的情感与心境为代表性症状，还以生活兴趣的减退，动力缺乏，活力丧失等为特征。还表现出失望、悲观以及与抑郁相联系的认知和躯体方面的感受，另外，还包括有关死亡的思想和自杀观念。\n" +
                    "该分量表的得分在0-52分之间。得分在26分以上，表明个体的抑郁程度较强，生活缺乏足够的兴趣，缺乏运动活力，极端情况下，可能会有想死亡的思想和自杀的观念。得分在13分以下，表明个体抑郁程度较弱，生活态度乐观积极，充满活力，心境愉快。总的说来，得分越高，抑郁程度越明显，得分越低，抑郁程度越不明显。",
                    "一般指那些烦躁，坐立不安，神经过敏，紧张以及由此产生的躯体征象，如震颤等。\n" +
                    "该分量表的得分在0-40分之间。得分在20分以上，表明个体较易焦虑，易表现出烦躁、不安静和神经过敏，极端时可能导致惊恐发作。得分在10分以下，表明个体不易焦虑，易表现出安定的状态。总的说来，得分越高，焦虑表现越明显。得分越低，越不会导致焦虑。",
                    "主要从三方面来反映敌对的表现：思想、感情及行为。其项目包括厌烦的感觉，摔物，争论直到不可控制的脾气暴发等各方面。\n" +
                    "该分量表的得分在0-24分之间。得分在12分以上，表明个体易表现出敌对的思想、情感和行为。得分在6分以下表明个体容易表现出友好的思想、情感和行为。总的说来，得分越高，个体越容易敌对，好争论，脾气难以控制。得分越低，个体的脾气越温和，待人友好，不喜欢争论、无破坏行为。",
                    "恐惧的对象包括出门旅行，空旷场地，人群或公共场所和交通工具。此外，还有社交恐怖。\n" +
                    "该分量表的得分在0-28分之间。得分在14分以上，表明个体恐怖症状较为明显，常表现出社交、广场和人群恐惧，得分在7分以下，表明个体的恐怖症状不明显。总的说来，得分越高，个体越容易对一些场所和物体发生恐惧，并伴有明显的躯体症状。得分越低，个体越不易产生恐怖心理，越能正常的交往和活动。",
                    "主要指投射性思维，敌对，猜疑，妄想，被动体验和夸大等。\n" +
                    "该分量表的得分在0-24分之间。得分在12分以上，表明个体的偏执症状明显，较易猜疑和敌对，得分在6分以下，表明个体的偏执症状不明显。总的说来，得分越高，个体越易偏执，表现出投射性的思维和妄想，得分越低，个体思维越不易走极端。",
                    "反映各式各样的急性症状和行为，即限定不严的精神病性过程的症状表现。该分量表的得分在0-40分之间。得分在20分以上，表明个体的精神病性症状较为明显，得分在10分以下，表明个体的精神病性症状不明显。总的说来，得分越高，越多的表现出精神病性症状和行为。得分越低，就越少表现出这些症状和行为。",
                    "作为附加项目或其他，作为第10个因子来处理，以便使各因子分之和等于总分。",
                    "是指总的来看，自我症状评价介于“没有”到“严重”的哪一个水平。总症状指数的分数在1～1.5之间，表明自我感觉没有量表中所列的症状；在1.5～2.5之间，表明感觉有点症状，但发生得并不频繁；在2.5～3.5之间，表明感觉有症状，其严重程度为轻到中度；在3.5～4.5之间，表明感觉有症状，其程度为中到严重；在4.5～5之间表明感觉有，且症状的频度和强度都十分严重。"],
                current:5 // 表示减去前面列数
            },
            methods: {
                download() {
                    this.$message({
                        message: '开始导出...',
                        type: 'info'
                    });
                    let xlsxParam = {raw: true};
                    let wb = XLSX.utils.table_to_book(document.querySelector('#outTable'), xlsxParam); //表格id
                    let wbout = XLSX.write(wb, {bookType: 'xlsx', bookSST: true, type: 'array'});
                    try {
                        saveAs(
                            new Blob([wbout], {type: "application/octet-stream;charset=utf-8"}), "sheet-" + this.currentPage + ".xlsx");
                    } catch (e) {
                        if (typeof console !== "undefined") console.log(e, wbout);
                    }
                    return wbout;
                },
                handleSizeChange(val) {
                    console.log(`每页 ${val} 条`);
                },
                handleCurrentChange(val) {
                    console.log(`当前页: ${val}`);
                    this.currentPage = val;
                    this.getResultByTime();
                },
                getResultByTime() {
                    let params = new URLSearchParams();
                    params.append("startTime", this.value2[0]);
                    params.append("endTime", this.value2[1]);
                    params.append("pageIndex", this.currentPage);
                    params.append("pageSize", this.pageSize);
                    axios.post("http://localhost:8888/epqapi/epq/getAllAnswerByTime", params)
                        .then(response => {
                            console.log(response.data.data.data);
                            this.tableData = response.data.data.data;
                            this.currentPage = response.data.data.pageIndex;
                            this.totalPage = response.data.data.count;
                            this.pageSize = response.data.data.pageSize;
                            this.questions = response.data.data.questions;
                        })
                        .catch(error => {
                            console.log(error);
                        });
                },
                getNowFormatDate() {
                    var date = new Date();
                    var seperator1 = "-";
                    var year = date.getFullYear();
                    var month = date.getMonth() + 1;
                    var strDate = date.getDate();
                    if (month >= 1 && month <= 9) {
                        month = "0" + month;
                    }
                    if (strDate >= 0 && strDate <= 9) {
                        strDate = "0" + strDate;
                    }
                    var currentdate = year + seperator1 + month + seperator1 + strDate;
                    console.log("currentdate = " + currentdate);
                    return currentdate;
                },
                tableRowClassName({row, rowIndex}) {
                    if (rowIndex % 2 != 0) {
                        return 'success-row';
                    }
                    return '';
                },
                exportInfo(answersMap, userName, userTel) {
                    this.$message({
                        message: '开始导出...',
                        type: 'info'
                    });
                    let exportData = [{"Question": "Question", "Answer": "Answer"}];
                    let json = {};
                    for (let key in this.questions) {
                        if (answersMap[key] === 0) {
                            json = {
                                "Question": [key + "." + this.questions[key]],
                                "Answer": "没有"
                            };
                        } else if (answersMap[key] === 1) {
                            json = {
                                "Question": [key + "." + this.questions[key]],
                                "Answer": "较轻"
                            };
                        } else if (answersMap[key] === 2) {
                            json = {
                                "Question": [key + "." + this.questions[key]],
                                "Answer": "中等"
                            };
                        } else if (answersMap[key] === 3) {
                            json = {
                                "Question": [key + "." + this.questions[key]],
                                "Answer": "较重"
                            };
                        }else if (answersMap[key] === 4) {
                            json = {
                                "Question": [key + "." + this.questions[key]],
                                "Answer": "严重"
                            };
                        }else {
                            json = {
                                "Question": [key + "." + this.questions[key]],
                                "Answer": "未答"
                            };
                        }
                        exportData.push(json);
                    }
                    console.log(exportData);
                    this.dataToExcel(exportData, userName + "-" + userTel + ".xlsx");

                },
                dataToExcel(data, filename) {
                    let keys = Object.keys(data[0]);
                    let firstRow = {};
                    keys.forEach(function (item) {
                        firstRow[item] = item;
                    });
                    data.unshift(firstRow);

                    let content = {};
                    // 把json格式的数据转为excel的行列形式
                    data = data.splice(1);
                    let sheetsData = data.map(function (item, rowIndex) {
                        return keys.map(function (key, columnIndex) {
                            return Object.assign({}, {
                                value: item[key],
                                position: (columnIndex > 25 ?
                                    this.getCharCol(columnIndex) : String.fromCharCode(65 + columnIndex)) + (rowIndex + 1),
                            });
                        });
                    }).reduce(function (prev, next) {
                        return prev.concat(next);
                    });

                    sheetsData.forEach(function (item, index) {
                        content[item.position] = {v: item.value};
                    });

                    //设置区域,比如表格从A1到D10,SheetNames:标题，
                    let coordinate = Object.keys(content);
                    let workBook = {
                        SheetNames: ["Sheet"],
                        Sheets: {
                            "Sheet": Object.assign({}, content, {"!ref": coordinate[0] + ":" + coordinate[coordinate.length - 1]}),
                        }
                    };
                    //这里的数据是用来定义导出的格式类型

                    let excelData = XLSX.write(workBook, {bookType: "xlsx", bookSST: false, type: "binary"});

                    let blob = new Blob([this.string2ArrayBuffer(excelData)], {type: "application/octet-stream"});
                    saveAs(blob, filename);
                },
                detailsInfo(answersMap, userName, userTel) {
                    this.dialogTableVisible = true;

                    for (let key in this.questions) {
                        let json = {};
                        json["id"] = key;
                        json["question"] = this.questions[key];
                        if (answersMap[key] === 0) {
                            json["answer"] = "没有";
                        } else if (answersMap[key] === 1) {
                            json["answer"] = "较轻";
                        }else if (answersMap[key] === 2) {
                            json["answer"] = "中等";
                        }else if (answersMap[key] === 3) {
                            json["answer"] = "较重";
                        }else if (answersMap[key] === 4) {
                            json["answer"] = "严重";
                        }
                        this.detailsName = userName;
                        this.detailsTel = userTel;
                        this.gridData.push(json);
                    }
                },

                exportDetailsInfo() {
                    this.$message({
                        message: '开始导出...',
                        type: 'info'
                    });
                    let xlsxParam = {raw: true};
                    let wb = XLSX.utils.table_to_book(document.querySelector('.details-info'), xlsxParam); //表格id
                    let wbout = XLSX.write(wb, {bookType: 'xlsx', bookSST: true, type: 'array'});
                    try {
                        saveAs(
                            new Blob([wbout], {type: "application/octet-stream;charset=utf-8"}),
                            this.detailsName + "-" + this.detailsTel + ".xlsx");
                    } catch (e) {
                        if (typeof console !== "undefined") console.log(e, wbout);
                    }
                    return wbout;
                },
                printInfo() {
                    $("#printInfo").print({
                        globalStyles: true,
                        mediaPrint: false,
                        stylesheet: null,
                        noPrintSelector: ".no-print",
                        iframe: true,
                        append: null,
                        prepend: null,
                        manuallyCopyFormValues: true,
                        deferred: $.Deferred(),
                        timeout: 750,
                        title: null,
                        doctype: '<!doctype html>'
                    });
                },
                string2ArrayBuffer(s) {
                    let buf = new ArrayBuffer(s.length);
                    let view = new Uint8Array(buf);
                    for (let i = 0; i != s.length; ++i) view[i] = s.charCodeAt(i) & 0xFF;
                    return buf;
                },
                getCharCol(n) {
                    let temCol = "",
                        s = "",
                        m = 0;
                    while (n > 0) {
                        m = n % 26 + 1;
                        s = String.fromCharCode(m + 64) + s;
                        n = (n - m) / 26;
                    }
                    return s
                },
                getAcolor(source){
                    if (source > 24) {
                        return {color: '#e31618'};
                    }else if (source >12 && source<=24) {
                        return {color: '#edc221'};
                    }else {
                        return {color: '#58e45d'};
                    }
                },
                getBcolor(source){
                    if (source > 20) {
                        return {color: '#e31618'};
                    }else if (source >10 && source<=20) {
                        return {color: '#edc221'};
                    }else {
                        return {color: '#58e45d'};
                    }
                },
                getCcolor(source){
                    if (source > 18) {
                        return {color: '#e31618'};
                    }else if (source >9 && source<=18) {
                        return {color: '#edc221'};
                    }else {
                        return {color: '#58e45d'};
                    }
                },
                getDcolor(source){
                    if (source > 24) {
                        return {color: '#e31618'};
                    }else if (source >12 && source<=24) {
                        return {color: '#edc221'};
                    }else {
                        return {color: '#58e45d'};
                    }
                },
                getEcolor(source){
                    if (source > 26) {
                        return {color: '#e31618'};
                    }else if (source >13 && source<=26) {
                        return {color: '#edc221'};
                    }else {
                        return {color: '#58e45d'};
                    }
                },
                getFcolor(source){
                    if (source > 20) {
                        return {color: '#e31618'};
                    }else if (source >10 && source<=20) {
                        return {color: '#edc221'};
                    }else {
                        return {color: '#58e45d'};
                    }
                },
                getGcolor(source){
                    if (source > 12) {
                        return {color: '#e31618'};
                    }else if (source >6 && source<=12) {
                        return {color: '#edc221'};
                    }else {
                        return {color: '#58e45d'};
                    }
                },
                getHcolor(source){
                    if (source > 14) {
                        return {color: '#e31618'};
                    }else if (source >7 && source<=14) {
                        return {color: '#edc221'};
                    }else {
                        return {color: '#58e45d'};
                    }
                },
                getIcolor(source){
                    if (source > 12) {
                        return {color: '#e31618'};
                    }else if (source >6 && source<=12) {
                        return {color: '#edc221'};
                    }else {
                        return {color: '#58e45d'};
                    }
                },
                getJcolor(source){
                    if (source > 20) {
                        return {color: '#e31618'};
                    }else if (source >10 && source<=20) {
                        return {color: '#edc221'};
                    }else {
                        return {color: '#58e45d'};
                    }
                },
                getAVcolor(source){
                    if (source >= 4.5) {
                        return {color: '#e31618'};
                    }else if (source >3.5 && source<4.5) {
                        return {color: '#edc221'};
                    }else {
                        return {color: '#58e45d'};
                    }
                },
                //自定义列标题内容
                renderHeader(h, { column, $index },index){
                    return h('span', {}, [
                        h('span', {}, column.label),
                        h('el-popover', { props: { placement: 'bottom', width: '400', trigger: 'hover',
                                content: this.info[$index-this.current]}}, [
                            h('i', { slot: 'reference', class:'el-icon-question'}, '')
                        ])
                    ])
                },
            },
            filters: {
                numberFormat(num) {

                    switch (num) {
                        case 0:
                            return "没有";
                        case 1:
                            return "较轻";
                        case 2:
                            return "中等";
                        case 3:
                            return "较重";
                        case 4:
                            return "严重";
                        default:
                            return "未答";
                    }

                }

            },
            mounted: function () {
                this.value2.push(this.getNowFormatDate());
                this.value2.push(this.getNowFormatDate());
                this.getResultByTime();
            },
        }
    )
}