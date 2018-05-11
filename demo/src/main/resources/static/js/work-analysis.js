// 配置招聘分析
/**
 * @param ins 得到的对应echarts表格实例[可选]
 * @param opts 配置项[可选]
 * @prop list1[Array] 需招人数数据
 * @prop list2[Array] 应聘人数数据
 * @prop list3[Array] 面试通过人数数据
 * @prop list4[Array] 入职人数数据
 * @prop list5[Array] 招聘有效率数据
 * @prop list6[Array] 招聘达成率数据
 */
function confRecruitAnalysis(ins, opts) {
    // 基于准备好的dom，初始化echarts实例
    var chartIns
    if (!ins) {
        chartIns = echarts.init($('#recruitAnalysis')[0], 'macarons')
    } else {
        chartIns = ins
    }
    // 指定图表的配置项和数据
    var colors = [
        'rgba(90, 177, 239, 1)',
        'rgba(252, 151, 175, 1)',
        'rgba(229, 207, 13, 1)',
        'rgba(46, 199, 201, 1)'
    ]
    // 解析opts参数
    var list1 = [];
    var list2 = [];
    var list3 = [];
    var list4 = [];
    var list5 = [];
    var list6 = [];
    var list7 = [];
    var list21 = [];
    var list22 = [];
    var list23 = [];
    var list24 = [];
    var list25 = [];
    var list26 = [];
    var list27 = [];
    var xAxisData = [];
    var map = [];
    opts.forEach(function (item, index) {
        if (item.name != '合计') {
            list1.push(item.needPcs);
            list2.push(item.quizPcs);
            list3.push(item.sendOfferPcs);
            list4.push(item.employeePcs);
            list5.push(item.needPcs - item.employeePcs);
            list6.push(item.efficiency);
            list7.push(item.reach);

            xAxisData.push(item.name)
        }
        list21.push(item.needPcs);
        list22.push(item.quizPcs);
        list23.push(item.sendOfferPcs);
        list24.push(item.employeePcs);
        list25.push(item.needPcs - item.employeePcs);
        list26.push(item.efficiency);
        list27.push(item.reach);

    })

    var option = {
        color: colors,
        grid: {
            top:50,
            left:70,
            right: 50,
            height: 400
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
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
        xAxis: [
            {
                type: 'category',
                data: xAxisData,
                axisLabel: {
                    interval:0,
                    rotate:40
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                min: 0,
                position: 'left',
                axisLine: {
                    lineStyle: {
                        color: '#333'
                    }
                }
            },
            {
                type: 'value',
                min: 0,
                max: 100,
                position: 'right',
                axisLine: {
                    lineStyle: {
                        color: colors[1]
                    }
                },
                axisLabel: {
                    formatter: '{value} %'
                }
            }
        ],
        series: [
            {
                name: '需招人数',
                type: 'bar',
                data: list1
            },
            {
                name: '应聘人数',
                type: 'bar',
                data: list2
            },
            {
                name: '面试通过人数',
                type: 'bar',
                data: list3
            },
            {
                name: '入职人数',
                type: 'bar',
                data: list4
            },
            {
                name: '招聘有效率',
                type: 'line',
                yAxisIndex: 1,
                data: list5
            },
            {
                name: '招聘达成率',
                type: 'line',
                yAxisIndex: 1,
                data: list6
            }
        ]
    }
    initRecruitAnalysisTable(list21, list22, list23, list24, list25, list26, list27)
    chartIns.setOption(option)
    return chartIns
}
// 配置表格
function initRecruitAnalysisTable(list1, list2, list3, list4, list5, list6, list7) {
    list6 = list6.map(function (item) {
        return item + '%'
    })
    list7 = list7.map(function (item) {
        return item + '%'
    })
    layui.use('laytpl', function () {
        var laytpl = layui.laytpl
        var tbTpl = $('#tbTpl').html()
        var o = {
            list: [list1, list2, list3, list4, list5, list6, list7]
        }
        laytpl(tbTpl).render(o, function (html) {
            $('#recruitAnalysisTable').html(html)
        })
    })
}
// END

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
    // 初始化招聘分析
    recruit()
})

function recruit() {
    var date = $('#staffChange_rangeDate').val();
    var departmentId = $('#departmentId').val();
    $.post("analysis/recruit", {date: date, departmentId: departmentId}, function (result) {
        confRecruitAnalysis(null, result.data)
    })
}

layui.use(['layer', 'laydate', 'tree','ajaxhtml'], function () {
    var ajaxhtml = layui.ajaxhtml,
        laydate = layui.laydate;

    $('.go_other').on('click', function (e) {
        var url = $(this).attr('data-url');
        ajaxhtml.pageLoad(url);
    });

    //执行一个laydate实例
    laydate.render({
        elem: '#staffChange_rangeDate', //指定元素
        type: 'date',
        range: '~',
        trigger: 'click',
        done: function (value, date) {//value, date, endDate点击日期、清空、现在、确定均会触发。回调返回三个参数，分别代表：生成的值、日期时间对象、结束的日期时间对象
            $('#staffChange_rangeDate').val(value);
            recruit()
        }
    });

    $('#recruit_curWeek').click(function () {
        $(this).addClass('active').siblings('.layui-btn').removeClass('active')
        $('#staffChange_rangeDate').val(getWeekStartDate() + ' ~ ' + getWeekEndDate())
        recruit()
    })
    $('#recruit_curMonth').click(function () {
        $(this).addClass('active').siblings('.layui-btn').removeClass('active')
        $('#staffChange_rangeDate').val(getMonthStartDate() + ' ~ ' + getMonthEndDate())
        recruit()
    })
    $('#recruit_curYear').click(function () {
        $(this).addClass('active').siblings('.layui-btn').removeClass('active')
        $('#staffChange_rangeDate').val(nowYear + '-01-01 ~ ' + nowYear+'-12-31')
        recruit()
    })

    $(".department-x").on('click', function () {
        $("#departmentsearch").hide();
        $("#departsearch").val("");
        $("#departmentId").val("");
        recruit()
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
                recruit()
            }
        });
    })

})
