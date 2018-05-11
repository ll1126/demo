// 配置请假情况
/**
 * @param ins 得到的对应echarts表格实例[可选]
 * @param opts 配置项[可选]
 * @prop list1[Array] 请假人数数据
 * @prop list2[Array] 请假占比数据
 */
function confVacate(ins, opts) {
    // 基于准备好的dom，初始化echarts实例
    var chartIns
    if (!ins) {
        chartIns = echarts.init($('#vacateChart')[0], 'macarons')
    } else {
        chartIns = ins
    }
    var list1 = [opts.sick,opts.thing,opts.marriage,opts.funeral,opts.pub,opts.injury,opts.maternity,opts.nursing,opts.other];
    var list2 = [];
    list1.forEach(function (item, index) {
        list2.push((item * 100 / opts.count).toFixed(2))
    })
    // 指定图表的配置项和数据
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: '{b}<br/>人数：{c}',
            padding: [12, 20, 12, 20],
            textStyle: {
                fontSize: 12,
                lineHeight: 10
            }
        },
        grid: {
            top: '3%',
            left: '10.5%',
            right: '4%',
            bottom: '33%'
        },
        xAxis: {
            type: 'category',
            axisLabel: {
                margin: 3
            },
            data: [
                '病假',
                '事假',
                '婚假',
                '丧假',
                '公假',
                '工伤',
                '产假',
                '护理假',
                '其他'
            ]
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
    initVacateTable(list1,list2)
    chartIns.setOption(option)
    return chartIns
}
// 配置table
function initVacateTable(list1,list2) {

    list2 = list2.map(function (item) {
        return item + '%'
    })
    layui.use('laytpl', function () {
        var laytpl = layui.laytpl
        var tbTpl = $('#tbTpl').html()
        var o = {
            list: [list1]
        }
        laytpl(tbTpl).render(o, function (html) {
            $('#vacateTable').html(html)
        })
    })
}
// END

// 配置加班情况
/**
 * @param ins 得到的对应echarts表格实例[可选]
 * @param opts 配置项[可选]
 * @prop list[Array] 请假人数数据
 */
function confOvertime(ins, opts) {
    // 基于准备好的dom，初始化echarts实例
    var chartIns
    if (!ins) {
        chartIns = echarts.init($('#overtimeChart')[0], 'macarons')
    } else {
        chartIns = ins
    }
    var list = [];
    var xAxisData = [];
    opts.forEach(function (item, index) {
        list.push(item.num)
        xAxisData.push(item.date)
    })
    // 指定图表的配置项和数据
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: '加班人数<br/>{b}：{c}',
            padding: [12, 20, 12, 20],
            textStyle: {
                fontSize: 12,
                lineHeight: 10
            }
        },
        grid: {
            top: '5%',
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
                data: list,
                type: 'bar',
                barWidth: 20
            }
        ]
    }

    chartIns.setOption(option)
    return chartIns
}
// 配置调休情况
function confTakeWorkDay(ins, opts) {
    // 基于准备好的dom，初始化echarts实例
    var chartIns
    if (!ins) {
        chartIns = echarts.init($('#takeWorkDayChart')[0], 'macarons')
    } else {
        chartIns = ins
    }
    var list = [];
    var xAxisData = [];
    opts.forEach(function (item, index) {
        list.push(item.num)
        xAxisData.push(item.date)
    })
    // 指定图表的配置项和数据
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: '调休人数<br/>{b}：{c}',
            padding: [12, 20, 12, 20],
            textStyle: {
                fontSize: 12,
                lineHeight: 10
            }
        },
        grid: {
            top: '5%',
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
                data: list,
                type: 'bar',
                barWidth: 20
            }
        ]
    }

    chartIns.setOption(option)
    return chartIns
}
$(function () {
    // 全局配置
    var chartIns = {
        vacate: null,
        overtime: null,
        takeWorkDay: null
    }

    // 初始化出勤统计
    attendance()

    // 初始化请假情况
    askLeaveInfo()

    // 初始化加班情况
    $.post("work/overTimeInfo", {}, function (result) {
        var data = result.data;
        $("#overtime").html(result.count);
        chartIns.overtime = confOvertime(null,result.data)
    })


    // 初始化调休情况
    $.post("work/askBreakInfo", {}, function (result) {
        var data = result.data;
        $("#askBreak").html(result.count);
        chartIns.takeWorkDay = confTakeWorkDay(null,result.data)
    })

})

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

//出勤统计
function attendance() {
    var date = $('#dailyAnalysis_rangeDate').val();
    var departmentId = $('#departmentId').val();
    $.post("work/turnOutForWork", {date: date, departmentId: departmentId}, function (result) {
        var data = result.data;
        $("#workNum").html(data.workNum+'人');
        $("#employess").html('公司总人数：'+data.employess+'人');
        $("#lateNum").html(data.lateNum+'人');
        $("#earlyNum").html(data.earlyNum+'人');
        $("#fillcardNum").html(data.fillcardNum+'人');
    })
}

function askLeaveInfo() {
    var date = $('#adjustWeek_rangeDate').val();
    var departmentId = $('#departmentId2').val();
    $.post("work/askLeaveInfo", {date: date, departmentId: departmentId}, function (result) {
        var data = result.data;
        $("#askLeave").html(data.count);
        confVacate(null,result.data)
    })
}

layui.use(['layer', 'laydate', 'tree'], function () {
    var laydate = layui.laydate,
        layer = layui.layer;

    $('#adjustWeek_curWeek').click(function () {
        $(this).addClass('active').siblings('.layui-btn').removeClass('active')
        $('#adjustWeek_rangeDate').val(getWeekStartDate() + ' ~ ' + getWeekEndDate())
        askLeaveInfo()
    })
    $('#adjustWeek_curMonth').click(function () {
        $(this).addClass('active').siblings('.layui-btn').removeClass('active')
        $('#adjustWeek_rangeDate').val(getMonthStartDate() + ' ~ ' + getMonthEndDate())
        askLeaveInfo()
    })

    //执行一个laydate实例
    laydate.render({
        elem: '#dailyAnalysis_rangeDate', //指定元素
        type: 'date',
        range: false,
        trigger: 'click',
        done: function (value, date) {//value, date, endDate点击日期、清空、现在、确定均会触发。回调返回三个参数，分别代表：生成的值、日期时间对象、结束的日期时间对象
            $('#dailyAnalysis_rangeDate').val(value);
            attendance()
        }
    })

    //执行一个laydate实例
    laydate.render({
        elem: '#adjustWeek_rangeDate', //指定元素
        type: 'date',
        range: '~',
        trigger: 'click',
        done: function (value, date) {//value, date, endDate点击日期、清空、现在、确定均会触发。回调返回三个参数，分别代表：生成的值、日期时间对象、结束的日期时间对象
            $('#adjustWeek_rangeDate').val(value);
            askLeaveInfo()
        }
    })

    $("#department-x").on('click', function () {
        $("#departmentsearch").hide();
        $("#departsearch").val("");
        $("#departmentId").val("");
        attendance()
    })

    $("#departsearch").on('click', function () {
        $("#departmentsearch").toggle();
    })

    $.post("department/getListByTree", {}, function (result) {
        layui.tree({
            elem: '#departmentsearch',
            nodes: result,
            click: function (node) {
                $("#departmentsearch").hide();
                $("#departsearch").val(node.name);
                $("#departmentId").val(node.id);
                attendance()
            }
        });
    })

    $("#department-x2").on('click', function () {
        $("#departmentsearch2").hide();
        $("#departsearch2").val("");
        $("#departmentId2").val("");
        askLeaveInfo()
    })

    $("#departsearch2").on('click', function () {
        $("#departmentsearch2").toggle();
    })

    $.post("department/getListByTree", {}, function (result) {
        layui.tree({
            elem: '#departmentsearch2',
            nodes: result,
            click: function (node) {
                $("#departmentsearch2").hide();
                $("#departsearch2").val(node.name);
                $("#departmentId2").val(node.id);
                askLeaveInfo()
            }
        });
    })



})
