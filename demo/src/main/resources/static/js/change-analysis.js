// 配置入离职情况
/**
 * @param titleConf 表格title的总样式[必填]
 * @param ins 得到的对应echarts表格实例[可选]
 * @param opts 配置项[可选]
 * @prop list1[Array] 入职人员数据
 * @prop list2[Array] 离职人员数据
 * @prop list3[Array] 待入职人员数据
 * @prop list4[Array] 待离职人员数据
 */
function confJobChange(titleConf, ins, opts) {
    // 基于准备好的dom，初始化echarts实例
    var colors = [
        'rgba(90, 177, 239, 1)',
        'rgba(252, 151, 175, 1)',
        'rgba(229, 207, 13, 1)',
        'rgba(46, 199, 201, 1)'
    ]
    var chartIns
    if (!ins) {
        chartIns = echarts.init($('#jobChangeCase')[0], 'macarons')
    } else {
        chartIns = ins
    }
    // 指定图表的配置项和数据
    var curTitle = $.extend({}, titleConf, {
        text: '入离职情况'
    })
    // 解析opts参数
    var list1 = [opts[0].entryNum,opts[1].entryNum,opts[2].entryNum,opts[3].entryNum,opts[4].entryNum,opts[5].entryNum];
    var list2 = [opts[0].leaveNum,opts[1].leaveNum,opts[2].leaveNum,opts[3].leaveNum,opts[4].leaveNum,opts[5].leaveNum];
    var list3 = [opts[0].waitEntryNum,opts[1].waitEntryNum,opts[2].waitEntryNum,opts[3].waitEntryNum,opts[4].waitEntryNum,opts[5].waitEntryNum];
    var list4 = [opts[0].waitLeaveNum,opts[1].waitLeaveNum,opts[2].waitLeaveNum,opts[3].waitLeaveNum,opts[4].waitLeaveNum,opts[5].waitLeaveNum];

    var option = {
        title: curTitle,
        grid: {
            bottom: '8%'
        },
        color: colors,
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            top: '8%',
            data: ['入职人员', '离职人员', '待入职人员', '待离职人员']
        },
        toolbox: {
            show: true,
            right: '3%',
            feature: {
                saveAsImage: {
                    backgroundColor:'#fff',
                    iconStyle:{
                        borderColor:'#2d8cf0',
                    },
                    emphasis:{
                        iconStyle:{
                            borderColor:'#2d8cf0',
                        },
                    }
                },
            }
        },
        xAxis: [
            {
                type: 'category',
                axisTick: {show: false},
                data: [opts[0].endDate, opts[1].endDate, opts[2].endDate,opts[3].endDate, opts[4].endDate, opts[5].endDate]
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '入职人员',
                type: 'bar',
                barGap: 0,
                data: list1
            },
            {
                name: '离职人员',
                type: 'bar',
                data: list2
            },
            {
                name: '待入职人员',
                type: 'bar',
                data: list3
            },
            {
                name: '待离职人员',
                type: 'bar',
                data: list4
            }
        ]
    }
    console.log(option)
    chartIns.setOption(option)
    return chartIns
}

// 配置离职类型
/**
 * @param titleConf 表格title的总样式[必填]
 * @param ins 得到的对应echarts表格实例[可选]
 * @param opts 配置项[可选]
 * @prop list[Array] 离职类型数据
 */
function confLeaveJobType(titleConf, ins, opts) {
    // 基于准备好的dom，初始化echarts实例
    var colors = [
        'rgba(90, 177, 239, 1)',
        'rgba(252, 151, 175, 1)',
        'rgba(229, 207, 13, 1)',
        'rgba(46, 199, 201, 1)'
    ]
    var chartIns
    if (!ins) {
        chartIns = echarts.init($('#leaveJobType')[0], 'macarons')
    } else {
        chartIns = ins
    }
    // 指定图表的配置项和数据
    var curTitle = $.extend({}, titleConf, {
        text: '离职类型'
    })
    // list模板
    var list = (opts && opts.list) || [
            {value: 0, name: '辞职'},
            {value: 0, name: '辞退'},
            {value: 0, name: '自离'},
            {value: 0, name: '开除'},
            {value: 0, name: '其他'},
            {value: 0, name: '离职人数'}
        ]
    var total = 0
    list.forEach(function (item, index) {
        if (item.name !== '离职人数') {
            total += Number(item.value)
        }
    })
    // 饼图的展示数据
    var data = list.map(function (item, index) {
        if (item.name === '离职人数') {
            item.value = ''
        }
        return item
    })
    var option = {
        color: colors,
        title: curTitle,
        tooltip: {
            trigger: 'item',
            formatter: '{b}<br/>人数：{c}<br/>占比：{d}%',
            padding: [12, 20, 12, 20],
            textStyle: {
                fontSize: 12,
                lineHeight: 10
            }
        },
        toolbox: {
            right: 20,
            feature: {
                saveAsImage: {
                    backgroundColor:'#fff',
                    iconStyle:{
                        borderColor:'#2d8cf0',
                    },
                    emphasis:{
                        iconStyle:{
                            borderColor:'#2d8cf0',
                        },
                    }
                },
            }
        },
        legend: {
            orient: 'vertical',
            right: '3%',
            bottom: '15%',
            itemGap: 20,
            itemWidth: 8,
            itemHeight: 8,
            selectedMode: false,
            data: [
                {
                    name: '离职人数'
                },
                {
                    name: '辞职',
                    icon: 'circle'
                },
                {
                    name: '辞退',
                    icon: 'circle'
                },
                {
                    name: '自离',
                    icon: 'circle'
                },
                {
                    name: '开除',
                    icon: 'circle'
                },
                {
                    name: '其他',
                    icon: 'circle'
                }
            ],
            formatter: function (name) {
                var curIndex =
                    name === '辞职'
                        ? 0
                        : name === '辞退'
                        ? 1
                        : name === '自离' ? 2 : name === '开除' ? 3 : 4
                if (name === '离职人数') {
                    return '{prft16|' + name + '} {ft16|' + total + '人}'
                }
                total = total || 1
                return (
                    '{pr|' +
                    name +
                    '}{verHr|}' +
                    '{perc|' +
                    (list[curIndex].value * 100/ total).toFixed(2) +
                    '%}' +
                    list[curIndex].value +
                    '人'
                )
            },
            textStyle: {
                fontSize: 12,
                rich: {
                    prft16: {
                        padding: [0, 15, 0, 0],
                        fontSize: 15
                    },
                    ft16: {
                        fontSize: 15
                    },
                    pr: {
                        padding: [0, 15, 0, 0]
                    },
                    verHr: {
                        width: 0,
                        height: 16,
                        borderWidth: 1,
                        borderColor: '#e6e6e6'
                    },
                    perc: {
                        color: '#999',
                        padding: [0, 30, 0, 10]
                    }
                }
            }
        },
        series: [
            {
                type: 'pie',
                selectedMode: false,
                radius: ['30%', '50%'],
                center: ['30%', '50%'],
                slient: false,
                label: {
                    show: false,
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                },
                labelLine: {
                    normal: {
                        show: true
                    }
                },
                data: data
            }
        ]
    }
    chartIns.setOption(option)
    return chartIns
}

// 配置调离情况
/**
 * @param titleConf 表格title的总样式[必填]
 * @param ins 得到的对应echarts表格实例[可选]
 * @param opts 配置项[可选]
 * @prop list1[Array] 调离人数数据
 * @prop list2[Array] 调离占比数据
 */
function confAdjustLeave(titleConf, ins, opts) {
    // 基于准备好的dom，初始化echarts实例
    var chartIns
    if (!ins) {
        chartIns = echarts.init($('#adjustLeave')[0], 'macarons')
    } else {
        chartIns = ins
    }
    // 指定图表的配置项和数据
    var curTitle = $.extend({}, titleConf, {
        text: '调离情况'
    })
    var list1 = [];
    var list2 = [];//list[curIndex].value * 100/ total).toFixed(2)
    var xAxisData = [];
    var total = 0;
    opts.forEach(function (item, index) {
        total += Number(item.value)
        list1.push(item.value);
        xAxisData.push(item.name);
    })
    opts.forEach(function (item, index) {
        list2.push((item.value * 100 / total).toFixed(2));
    })

    var option = {
        title: curTitle,
        toolbox: {
            feature: {
                saveAsImage: {
                    backgroundColor:'#fff',
                    iconStyle:{
                        borderColor:'#2d8cf0',
                    },
                    emphasis:{
                        iconStyle:{
                            borderColor:'#2d8cf0',
                        },
                    }
                },
            }
        },
        grid: {
            top: '15%',
            left: '13%',
            right: '3%',
            bottom: '30%'
        },
        xAxis: {
            type: 'category',
            axisLabel: {
                margin: 3
            },
            data: xAxisData
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                data: list1,
                type: 'bar',
                barWidth: 20
            }
        ]
    }
    initAdjustLeaveTable(list1,list2)
    chartIns.setOption(option)
    return chartIns
}
// 配置table
function initAdjustLeaveTable(list1,list2) {
    var list1 = (list1) || [0, 0, 0, 0, 0, 0, 0]
    var list2 = (list2) || [0, 0, 0, 0, 0, 0, 0]
    list2 = list2.map(function (item) {
        return item + '%'
    })
    layui.use('laytpl', function () {
        var laytpl = layui.laytpl
        var tbTpl = $('#tbTpl').html()
        var o = {
            list: [list1, list2]
        }
        laytpl(tbTpl).render(o, function (html) {
            $('#adjustLeaveTable').html(html)
        })
    })
}

var now = new Date(); //当前日期
var nowDayOfWeek = now.getDay(); //今天本周的第几天
var nowDay = now.getDate(); //当前日
var nowMonth = now.getMonth(); //当前月
var nowYear = now.getYear(); //当前年
nowYear += (nowYear < 2000) ? 1900 : 0; //

//格局化日期：yyyy-MM-dd
function formatDate(date) {
    var myyear = date.getFullYear();
    var mymonth = date.getMonth() + 1;
    var myweekday = date.getDate();
    if (mymonth < 10) {
        mymonth = "0" + mymonth;
    }
    if (myweekday < 10) {
        myweekday = "0" + myweekday;
    }
    return (myyear + "-" + mymonth + "-" + myweekday);
}

//获得本周的开端日期
function getWeekStartDate() {
    var weekStartDate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek);
    return formatDate(weekStartDate);
}

//获得本周的停止日期
function getWeekEndDate() {
    var weekEndDate = new Date(nowYear, nowMonth, nowDay + (6 - nowDayOfWeek));
    return formatDate(weekEndDate);
}

//获得某月的天数
function getMonthDays(myMonth) {
    var monthStartDate = new Date(nowYear, myMonth, 1);
    var monthEndDate = new Date(nowYear, myMonth + 1, 1);
    var days = (monthEndDate - monthStartDate) / (1000 * 60 * 60 * 24);
    return days;
}

//获得本月的开端日期
function getMonthStartDate() {
    var monthStartDate = new Date(nowYear, nowMonth, 1);
    return formatDate(monthStartDate);
}

//获得本月的停止日期
function getMonthEndDate() {
    var monthEndDate = new Date(nowYear, nowMonth, getMonthDays(nowMonth));
    return formatDate(monthEndDate);
}

// 加载
$(function () {
    // 全局配置
    var titleConf = {
        top: '1%',
        left: '2%',
        textStyle: {
            color: '#333',
            width: 120,
            lineHeight: 38,
            fontSize: 14
        }
    }
    var chartIns = {
        jobChange: null,
        leaveJobType: null,
        adjustLeave: null
    }
    // 初始人员异动分析
    initChangeSearch()
    // 初始化入离职情况
    $.get('ceo/flow', {}, function (result) {
        confJobChange(titleConf,null,result.data)
    })
    //chartIns.jobChange = confJobChange(titleConf)
    // 初始化离职类型
    leaveType()
    //chartIns.leaveJobType = confLeaveJobType(titleConf)
    // 初始化调离情况
    changeMove()
    //chartIns.adjustLeave = confAdjustLeave(titleConf)

    function leaveType() {
        var dates = $('#staffChange_rangeDate').val()
        var params = {date: dates}
        $.get('analysis/leaveType', params, function (result) {
            confLeaveJobType(titleConf,null,result.data)
        })
    }

    function changeMove() {
        var dates = $('#staffChange_rangeDate').val()
        var params = {date: dates}
        $.get('analysis/changeMove', params, function (result) {
            confAdjustLeave(titleConf,null,result.data)
        })
    }

    // 配置人员异动分析的搜索效果
    function initChangeSearch() {
        layui.use([ 'laydate', 'table'], function () {
            var laydate = layui.laydate
            var table = layui.table

            //执行一个laydate实例
            laydate.render({
                elem: '#staffChange_rangeDate', //指定元素
                type: 'date',
                range: '~',
                trigger: 'click',
                done:function(value,date){//value, date, endDate点击日期、清空、现在、确定均会触发。回调返回三个参数，分别代表：生成的值、日期时间对象、结束的日期时间对象
                    $('#staffChange_rangeDate').val(value)
                    reloadTable()
                    leaveType()
                    changeMove()
                }
            })

            //导出功能
            $("#export").click(function (e) {
                var dates = $('#staffChange_rangeDate').val()
                var params = {
                    date: dates
                }
                var index = layer.load(1, {
                    shade: [0.1,'#fff'] //0.1透明度的白色背景
                });
                $.post("analysis/exportPersonChange", params, function (result) {
                    layer.close(index);
                    if (result.code == 0) {
                        window.location.href = "合肥聚财统计分析/" + result.data + "人员异动分析.xlsx";
                    } else {
                        layer.msg('暂无数据');
                    }
                })
            });

            // table初始化
            createTable()
            $('#staffChange_curWeek').click(function () {
                $(this).addClass('active').siblings('.layui-btn').removeClass('active')
                $('#staffChange_rangeDate').val(getWeekStartDate() + ' ~ ' + getWeekEndDate())
                reloadTable()
                leaveType()
                changeMove()
            })
            $('#staffChange_curMonth').click(function () {
                $(this).addClass('active').siblings('.layui-btn').removeClass('active')
                $('#staffChange_rangeDate').val(getMonthStartDate() + ' ~ ' + getMonthEndDate())
                reloadTable()
                leaveType()
                changeMove()
            })

            function createTable() {
                 table.render({
                    elem: '#staffChange',
                    url: 'analysis/personChange',
                    cols: [
                        [
                            {field: 'name', title: '部门', width: 150},
                            {field: 'initialNum', title: '初始人数', width: 120},
                            {field: 'entryNum', title: '已入职人数', width: 120},
                            {field: 'resignedNum', title: '辞职', width: 100},
                            {field: 'dismissNum', title: '辞退', width: 100},
                            {field: 'selfLeaveNum', title: '自离', width: 100},
                            {field: 'fireNum', title: '开除', width: 100},
                            {field: 'otherNum', title: '其他', width: 100},
                            {field: 'leaveNum', title: '已离职人数', width: 120},
                            {field: 'leaveRatio', title: '离职率', width: 120},
                            {field: 'changeStationNum', title: '调岗', width: 100},
                            {field: 'changeSarayNum', title: '调薪', width: 100},
                            {field: 'changeGroupNum', title: '调级', width: 100},
                            {field: 'changeMoveNum', title: '调离', width: 100}
                        ]
                    ],
                    id: 'initStaffChange'
                })
            }

            function reloadTable() {
                var dates = $('#staffChange_rangeDate').val()
                var params = {
                    date: dates
                }
                table.reload('initStaffChange', {
                    where: params
                })
            }
        })
    }
})
