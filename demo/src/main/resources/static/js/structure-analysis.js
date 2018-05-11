// 配置公司人员数量统计
/**
 * @param titleConf 表格title的总样式[必填]
 * @param ins 得到的对应echarts表格实例[可选]
 * @param opts 配置项[可选]
 * @prop list[Array] 侧边栏的数据展示
 * @prop series[Object] 图表的数据展示
 */
function confFilmStaff(titleConf, ins, opts) {
    // 基于准备好的dom，初始化echarts实例
    var chartIns
    if (!ins) {
        chartIns = echarts.init(document.getElementById('tj-filmStaff'), 'macarons')
    } else {
        chartIns = ins
    }
    // 解析数据
    var list = (opts && opts.list) || [0, 0, 0, 0, 0, 0]
    /**
     * list参数说明
     * 下标0  - 当前日期的数据
     * 下标1  - 总人数的数据
     * 下标2  - 正式员工的数据
     * 下标3  - 试用员工的数据
     * 下标4  - 10天内待转正的数据
     * 下标5  - 预计下月总人数
     */

    var series = opts && opts.series
    /**
     * series参数说明
     * @prop yhData  已获数据
     * @prop ypData  预判数据
     */
    var yhData = ['-', '-', '-', '-', '-', '-', '-']
    var ypData = ['-', '-', '-', '-', '-', '-', '-']
    var xAxisData = ['2017-08', '2017-09', '2017-10', '2017-11', '2017-12', '2018-01', '2018-02']
    if (series && (series.yhData || series.ypData || series.xAxisData)) {
        yhData = series.yhData
        ypData = series.ypData
        xAxisData = series.xAxisData
    }

    // 指定图表的配置项和数据
    var curTitle = $.extend({}, titleConf, {
        text: '公司人员数量统计'
    })
    var option = {
        title: curTitle,
        legend: {
            top: 30,
            right: 0,
            itemWidth: 0,
            orient: 'vertical',
            align: 'right',
            inactiveColor: '#999',
            selected: [false, false, false, false, false, false],
            formatter: function (name) {
                if (name === '当前日期') {
                    return ('{title|}' + name + '       {black|' + list[0] + '}' + '\n' + '{hr|}')
                }
                if (name === '总人数') {
                    return name + '       {black|' + list[1] + '}'
                }
                if (name === '正式员工') {
                    return name + '       {black|' + list[2] + '}'
                }
                if (name === '试用员工') {
                    return name + '       {black|' + list[3] + '}'
                }
                if (name === '10天内待转正') {
                    return name + '       {black|' + list[4] + '}'
                }
                if (name === '预计下月总人数') {
                    return name + '       {black|' + list[5] + '}'
                }
            },
            textStyle: {
                color: '#999',
                fontSize: 12,
                padding: 5,
                rich: {
                    title: {
                        padding: [20, 0, 0, 0]
                    },
                    hr: {
                        borderColor: '#e6e6e6',
                        width: '100%',
                        borderWidth: 1,
                        height: 0
                    },
                    black: {
                        color: '#333'
                    }
                }
            },
            data: [
                {
                    name: '当前日期',
                    icon: 'arrow'
                },
                {
                    name: '总人数',
                    icon: 'arrow'
                },
                {
                    name: '正式员工',
                    icon: 'arrow'
                },
                {
                    name: '试用员工',
                    icon: 'arrow'
                },
                {
                    name: '10天内待转正',
                    icon: 'arrow'
                },
                {
                    name: '预计下月总人数',
                    icon: 'arrow'
                }
            ]
        },
        tooltip:{
            show:true,
            formatter:'日期：{b}<br/> 总人数：{c}'
        },
        grid: {
            right: '25%',
            left: '2%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            show:true,
            right: 20,
            feature: {
                saveAsImage: {
                    show: true,
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
        xAxis: {
            type: 'category',
            data: xAxisData
        },
        yAxis: {
            type: 'value',
            axisTick: {
                show: false
            },
            axisLine: {
                show: false
            },
            splitLine: {
                lineStyle: {
                    type: 'dotted'
                }
            },
            min:100
        },
        series: [
            {
                name: '已获数据',
                type: 'line',
                data: yhData
            },
            {
                name: '预判数据',
                type: 'line',
                smooth: false, //关键点，为true是不支持虚线，实线就用true
                itemStyle: {
                    normal: {
                        lineStyle: {
                            width: 2,
                            type: 'dotted' //'dotted'虚线 'solid'实线
                        }
                    }
                },
                data: ypData
            },
            // 用来显示额外数据的部分
            {
                name: '当前日期',
                type: 'line'
            },
            {
                name: '总人数',
                type: 'line'
            },
            {
                name: '正式员工',
                type: 'line'
            },
            {
                name: '试用员工',
                type: 'line'
            },
            {
                name: '10天内待转正',
                type: 'line'
            },
            {
                name: '预计下月总人数',
                type: 'line'
            }
        ]
    }
    console.log('gl:', option)
    chartIns.setOption(option)
    return chartIns
}

// 配置部门人员
/**
 * @param titleConf 表格title的总样式[必填]
 * @param ins 得到的对应echarts表格实例[可选]
 * @param opts 配置项[可选]
 * @prop list1[Array] 内圈圆的数据
 * @prop list2[Array] 外圈圆的数据
 */
function confDepStaff(titleConf, ins, opts) {
    // 基于准备好的dom，初始化echarts实例
    var chartIns
    if (!ins) {
        chartIns = echarts.init(document.getElementById('tg-depStaff'), 'macarons')
    } else {
        chartIns = ins
    }

    // 指定图表的配置项和数据
    var curTitle = $.extend({}, titleConf, {
        text: '各部门人员数量统计'
    })
    // 内圈圆数据模板
    var list1 = (opts && opts.list1) || [
            {value: 10, name: '业务中心'},
            {value: 20, name: '技术中心'},
            {value: 30, name: 'VIP中心'},
            {value: 40, name: '品牌中心'},
            {value: 50, name: '行政中心'}
        ]
    var list2 = (opts && opts.list2) || [
            {value: 0, name: '业务中心-流量部'},
            {value: 0, name: '业务中心-策划部'},
            {value: 0, name: '技术中心-前端组'},
            {value: 0, name: 'VIP中心-vip'},
            {value: 0, name: '品牌中心-品牌'},
            {value: 0, name: '行政中心-行政一姐'}
        ]
    // 计算内圈圆人数总数
    var total1 = 0
    var legendData = [];
    list1.forEach(function (item, index) {
        total1 += Number(item.value);
        legendData.push({name: item.name, icon: 'circle'});
    })

    var option = {
        title: curTitle,
        tooltip: {
            trigger: 'item',
            formatter: '{b}<br/>部门: {c}人',
            textStyle: {
                padding: [20, 15, 20, 15]
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
            top: 40,
            right: 10,
            itemGap: 20,
            itemWidth: 8,
            itemHeight: 8,
            data: legendData,
            formatter: function (name) {
                var curPerc = 0
                var curPers = 0
                total1 = total1 > 0 ? total1 : 1
                list1.forEach(function (item, index) {
                    if (name === item.name) {
                        curPerc = (item.value*100 / total1).toFixed(2)
                        curPers = item.value
                    }
                })
                return (
                    '{pr|' +
                    name +
                    '}' +
                    '{verHr|}' +
                    '{perc|' +
                    curPerc +
                    '%}' +
                    curPers +
                    '人'
                )
            },
            textStyle: {
                fontSize: 14,
                rich: {
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
                selectedMode: 'single',
                radius: [0, '50%'],
                center: ['33%', '45%'],
                label: {
                    normal: {
                        position: 'inner'
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: list1
            },
            {
                name: '访问来源',
                type: 'pie',
                radius: ['60%', '70%'],
                center: ['33%', '45%'],
                label: {
                    normal: {
                        show:false,
                        formatter: '{b|{b}人数：{c}人}  ',
                        backgroundColor: '#fff',
                        borderColor: '#e6e6e6',
                        borderWidth: 1,
                        borderRadius: 4,
                        rich: {
                            a: {
                                color: '#999',
                                lineHeight: 22,
                                align: 'center'
                            },
                            hr: {
                                borderColor: '#aaa',
                                width: '100%',
                                borderWidth: 0.5,
                                height: 0
                            },
                            b: {
                                fontSize: 12,
                                lineHeight: 33,
                                color: '#333'
                            }
                        }
                    },
                    emphasis:{
                        show:true
                    }
                },
                labelLine:{
                    normal:{
                        show:false,

                    },
                    emphasis:{
                        show:true,
                    },
                },
                data: list2
            }
        ]
    }

    chartIns.setOption(option)
    return chartIns
}

// 配置工龄部分
/**
 * @param titleConf 表格title的总样式[必填]
 * @param type 区分在职，离职[可选]
 * @param ins 得到的对应echarts表格实例[可选]
 * @param opts 配置项[可选]
 * @prop list1[Array] 工龄人数数据
 * @prop list2[Array] 工龄占比数据
 */
function confGl(titleConf, type, ins, opts) {
    // 基于准备好的dom，初始化echarts实例
    var chartIns
    type = type || 0
    if (!ins) {
        if (type === 0) {
            chartIns = echarts.init($('#inService .gl')[0], 'macarons')
        } else {
            chartIns = echarts.init($('#hired .gl')[0], 'macarons')
        }
    } else {
        if (type === 1) {
            ins.clear()
            chartIns = echarts.init($('#hired .gl')[0], 'macarons')
        } else {
            chartIns = ins
        }
    }

    var list1 = (opts && opts.list1) || [0, 0, 0, 0, 0, 0, 0]
    // 指定图表的配置项和数据
    var curTitle = $.extend({}, titleConf, {
        text: '工龄分析'
    })
    var option = {
        title: curTitle,
        toolbox: {
            right: '2%',
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
            left: '13%',
            right: '3%',
            bottom: '28%'
        },
        xAxis: {
            type: 'category',
            data: [
                '7天\n以内',
                '7天-\n1个月',
                '1-\n3个月',
                '3-\n6个月',
                '6-\n1个年',
                '1-\n2个年',
                '2年\n以上'
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

    initGlTable(type, opts)
    chartIns.setOption(option)
    return chartIns
}
// 配置table
function initGlTable(type, opts) {
    var list1 = (opts && opts.list1) || [0, 0, 0, 0, 0, 0, 0]
    var list2 = (opts && opts.list2) || [0, 0, 0, 0, 0, 0, 0]
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
            if (type === 0) {
                $('#inService .gl-tb').html(html)
            } else {
                $('#hired .gl-tb').html(html)
            }
        })
    })
}
// END

// 配置学历部分
/**
 * @param titleConf 表格title的总样式[必填]
 * @param type 区分在职，离职[可选]
 * @param ins 得到的对应echarts表格实例[可选]
 * @param opts 配置项[可选]
 * @prop list1[Array] 学历人数数据
 * @prop list2[Array] 学历占比数据
 */
function confXl(titleConf, type, ins, opts) {
    // 基于准备好的dom，初始化echarts实例
    var chartIns
    type = type || 0
    if (!ins) {
        if (type === 0) {
            chartIns = echarts.init($('#inService .xl')[0], 'macarons')
        } else {
            chartIns = echarts.init($('#hired .xl')[0], 'macarons')
        }
    } else {
        chartIns = ins
    }

    var list1 = (opts && opts.list1) || [0, 0, 0, 0, 0, 0]
    // 指定图表的配置项和数据
    var curTitle = $.extend({}, titleConf, {
        text: '学历'
    })
    var option = {
        title: curTitle,
        toolbox: {
            right: '2%',
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
            left: '13%',
            right: '3%',
            bottom: '28%'
        },
        xAxis: {
            type: 'category',
            data: ['高中\n以下', '高中', '大专', '本科', '硕士\n及以上', '其他']
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

    initXlTable(type, opts)
    chartIns.setOption(option)
    return chartIns
}

// 配置table
function initXlTable(type, opts) {
    var list1 = (opts && opts.list1) || [0, 0, 0, 0, 0, 0]
    var list2 = (opts && opts.list2) || [0, 0, 0, 0, 0, 0]
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
            if (type === 0) {
                $('#inService .xl-tb').html(html)
            } else {
                $('#hired .xl-tb').html(html)
            }
        })
    })
}
// END

// 配置性别比例
/**
 * @param titleConf 表格title的总样式[必填]
 * @param type 区分在职，离职[可选]
 * @param ins 得到的对应echarts表格实例[可选]
 * @param opts 配置项[可选]
 * @prop list[Array] 性别数据
 */
function confXb(titleConf, type, ins, opts) {
    // 基于准备好的dom，初始化echarts实例
    var chartIns
    type = type || 0
    if (!ins) {
        if (type === 0) {
            chartIns = echarts.init($('#inService .xb')[0], 'macarons')
        } else {
            chartIns = echarts.init($('#hired .xb')[0], 'macarons')
        }
    } else {
        chartIns = ins
    }

    // list模板
    var list = (opts && opts.list) || [
            {value: 0, name: '男'},
            {value: 0, name: '女'}
        ]
    // 总人数
    var total = 0
    list.forEach(function (item, index) {
        total += Number(item.value)
    })
    // 拼接总人数
    list = list.concat({name: '总人数'})
    // 指定图表的配置项和数据
    var curTitle = $.extend({}, titleConf, {
        text: '性别比例'
    })
    var option = {
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
            left: '24%',
            bottom: '5%',
            itemGap: 20,
            itemWidth: 8,
            itemHeight: 8,
            selectedMode: false,
            data: [
                {
                    name: '总人数',
                    icon: ''
                },
                {
                    name: '男',
                    icon: 'circle'
                },
                {
                    name: '女',
                    icon: 'circle'
                }
            ],
            formatter: function (name) {
                var curIndex = name === '男' ? 0 : 1
                if (name === '总人数') {
                    return '{prft16|' + name + '}{ft16|' + total + '}'
                }
                total = total > 0 ? total : 1
                return (
                    name +
                    '{plf|' +
                    list[curIndex].value +
                    '人}' +
                    (list[curIndex].value * 100/ total).toFixed(2) +
                    '%'
                )
            },
            textStyle: {
                fontSize: 12,
                rich: {
                    plf: {
                        padding: [0, 30, 0, 40]
                    },
                    prft16: {
                        padding: [0, 15, 0, 0],
                        fontSize: 15
                    },
                    ft16: {
                        fontSize: 15
                    }
                }
            }
        },
        series: [
            {
                type: 'pie',
                selectedMode: 'single',
                radius: ['35%', '65%'],
                center: ['50%', '40%'],
                labelLine: {
                    normal: {
                        show: true
                    }
                },
                label: {
                    normal: {
                        show: true
                    }
                },
                data: list
            }
        ]
    }

    chartIns.setOption(option)
    return chartIns
}

// 配置年龄分析
/**
 * @param titleConf 表格title的总样式[必填]
 * @param type 区分在职，离职[可选]
 * @param ins 得到的对应echarts表格实例[可选]
 * @param opts 配置项[可选]
 * @prop list[Array] 各个年龄的数据
 */
function confNl(titleConf, type, ins, opts) {
    // 基于准备好的dom，初始化echarts实例
    var chartIns
    type = type || 0
    if (!ins) {
        if (type === 0) {
            chartIns = echarts.init($('#inService .nl')[0], 'macarons')
        } else {
            chartIns = echarts.init($('#hired .nl')[0], 'macarons')
        }
    } else {
        chartIns = ins
    }
    // 指定图表的配置项和数据
    var curTitle = $.extend({}, titleConf, {
        text: '年龄比例'
    })
    // list模板
    var list = (opts && opts.list) || [
            {value: 0, name: '20岁以下'},
            {value: 0, name: '20-30岁'},
            {value: 0, name: '30-40岁'},
            {value: 0, name: '40-50岁'},
            {value: 0, name: '50岁以上'},
            {value: 0, name: '平均年龄'}
        ]
    var total = 0;
    var avg = 0;
    list.forEach(function (item, index) {
        if (item.name !== '平均年龄') {
            total += Number(item.value)
        }else{
            avg=item.value
        }
    })
    // 饼图的展示数据
    var data = list.map(function (item, index) {
        if (item.name === '平均年龄') {
            item.value = ''
        }
        return item
    })
    var option = {
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
            top: '20%',
            right: '3%',
            itemGap: 20,
            itemWidth: 8,
            itemHeight: 8,
            selectedMode: false,
            data: [
                {
                    name: '平均年龄',
                    icon: ''
                },
                {
                    name: '20岁以下',
                    icon: 'circle'
                },
                {
                    name: '20-30岁',
                    icon: 'circle'
                },
                {
                    name: '30-40岁',
                    icon: 'circle'
                },
                {
                    name: '40-50岁',
                    icon: 'circle'
                },
                {
                    name: '50岁以上',
                    icon: 'circle'
                }
            ],
            formatter: function (name) {
                var curIndex =
                    name === '20岁以下'? 0: name === '20-30岁'? 1: name === '30-40岁' ? 2 : name === '40-50岁' ? 3 : 4
                if (name === '平均年龄') {
                    return '{prft16|' + name + '} {ft16|' + avg + '}'
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
                radius: ['40%', '60%'],
                center: ['35%', '50%'],
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: 16,
                            rich: {
                                title: {
                                    fontSize: 12,
                                    color: '#999',
                                    padding: [15, 0, 0, 0]
                                },
                                em: {
                                    fontSize: 18,
                                    color: '#333'
                                }
                            }
                        }
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

// 配置人员职能层次
/**
 * @param titleConf 表格title的总样式[必填]
 * @param type 区分在职，离职[可选]
 * @param ins 得到的对应echarts表格实例[可选]
 * @param opts 配置项[可选]
 * @prop list[Array] 各个职能的数据
 */
function confZn(titleConf, type, ins, opts) {
    // 基于准备好的dom，初始化echarts实例
    var chartIns
    type = type || 0
    if (!ins) {
        if (type === 0) {
            chartIns = echarts.init($('#inService .zn')[0], 'macarons')
        } else {
            chartIns = echarts.init($('#hired .zn')[0], 'macarons')
        }
    } else {
        chartIns = ins
    }

    // 指定图表的配置项和数据
    var curTitle = $.extend({}, titleConf, {
        text: '人员职能层次'
    })
    // list模板
    var list = (opts && opts.list) || [
            {value: 0, name: '基层类'},
            {value: 0, name: '职能类'},
            {value: 0, name: '技术类'},
            {value: 0, name: '管理类'},
            {value: 0, name: '其他'},
            {value: 0, name: '总人数'}
        ]
    var total = list[list.length - 1].value || 0
    // 饼图的展示数据
    var data = list.map(function (item, index) {
        if (item.name === '总人数') {
            item.value = ''
        }
        return item
    })

    var option = {
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
            top: '20%',
            right: '3%',
            itemGap: 20,
            itemWidth: 8,
            itemHeight: 8,
            selectedMode: false,
            data: [
                {
                    name: '总人数'
                },
                {
                    name: '基层类',
                    icon: 'circle'
                },
                {
                    name: '职能类',
                    icon: 'circle'
                },
                {
                    name: '技术类',
                    icon: 'circle'
                },
                {
                    name: '管理类',
                    icon: 'circle'
                },
                {
                    name: '其他',
                    icon: 'circle'
                }
            ],
            formatter: function (name) {
                var curPerc = '';
                var curPers = '';
                list.forEach(function (item, index) {
                    if (name === item.name) {
                        curPerc = (item.value*100 / total).toFixed(2)
                        curPers = item.value
                    }
                })
                if (name === '总人数') {
                        return '{prft16|' + name + '} {ft16|' + total + '人}'
                    }
                return (
                    '{pr|' +
                    name +
                    '}' +
                    '{verHr|}' +
                    '{perc|' +
                    curPerc +
                    '%}' +
                    curPers +
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
                radius: ['40%', '60%'],
                center: ['35%', '50%'],
                slient: false,
                label: {
                    show: false,
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: 16,
                            rich: {
                                title: {
                                    fontSize: 12,
                                    color: '#999',
                                    padding: [15, 0, 0, 0]
                                },
                                em: {
                                    fontSize: 18,
                                    color: '#333'
                                }
                            }
                        }
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

// 配置岗位层级
/**
 * @param titleConf 表格title的总样式[必填]
 * @param type 区分在职，离职[可选]
 * @param ins 得到的对应echarts表格实例[可选]
 * @param opts 配置项[可选]
 * @prop list1[Array] 层级的总体数据
 * @prop list2[Array] 管理人员的组成数据
 */
function confCj(titleConf, type, ins, opts) {
    // 基于准备好的dom，初始化echarts实例
    var chartIns
    type = type || 0
    if (!ins) {
        if (type === 0) {
            chartIns = echarts.init($('#inService .cj')[0], 'macarons')
        } else {
            chartIns = echarts.init($('#hired .cj')[0], 'macarons')
        }
    } else {
        chartIns = ins
    }
    // var chartIns = echarts.init($('#inService .cj')[0], 'macarons')

    // 指定图表的配置项和数据
    var curTitle = $.extend({}, titleConf, {
        text: '岗位层级'
    })
    // list模板
    var list1 = (opts && opts.list1) || [
            {value: 0, name: '管理人员'},
            {value: 0, name: '普通员工'},
            {value: 0, name: '总人数'}
        ]
    var list2 = (opts && opts.list2) || [
            {value: 0, name: '运营总监'},
            {value: 0, name: '技术总监'},
            {value: 0, name: '运营副总监'},
            {value: 0, name: '部门经理'},
            {value: 0, name: '组长'},
            {value: 0, name: '副组长'}
        ]
    var total2 = list1[0].value // 管理人员的总数
    total2 = total2 > 0 ? total2 : 1
    var glLabel = {
        normal: {
            formatter: [
                '  {title|运营总监：' +
                list2[0].value +
                '人（' +
                (list2[0].value * 100 / total2).toFixed(2) +
                '%）} {aborg|}',

                '  {title|技术总监：' +
                list2[1].value +
                '人（' +
                (list2[1].value * 100 / total2).toFixed(2) +
                '%）} {abblue|}',

                '  {title|运营副总监：' +
                list2[2].value +
                '人（' +
                (list2[2].value * 100 / total2).toFixed(2) +
                '%）}{abgreen|}',

                '  {title|部门经理：' +
                list2[3].value +
                '人（' +
                (list2[3].value * 100 / total2).toFixed(2) +
                '%）}{abpurple|}',
                '  {title|组长：' +
                list2[4].value +
                '人（' +
                (list2[4].value * 100 / total2).toFixed(2) +
                '%）}{abpink|}',

                '  {title|副组长：' +
                list2[5].value +
                '人（' +
                (list2[5].value * 100 / total2).toFixed(2) +
                '%）}{ablightgreen|}'
            ].join('\n'),
            backgroundColor: '#eee',
            rich: {
                title: {
                    color: '#333',
                    align: 'center'
                },
                aborg: {
                    backgroundColor: 'rgba(229, 207, 13, 1)',
                    width: '100%',
                    align: 'right',
                    height: 15
                },
                abblue: {
                    backgroundColor: 'rgba(200, 150, 13, 1)',
                    width: '100%',
                    align: 'right',
                    height: 18
                },
                abgreen: {
                    backgroundColor: 'rgba(46, 199, 201, 1)',
                    width: '100%',
                    align: 'right',
                    height: 25
                },
                abpurple: {
                    backgroundColor: 'rgba(182, 162, 222, 1)',
                    width: '100%',
                    align: 'right',
                    height: 33.5
                },
                abpink: {
                    backgroundColor: 'rgba(252, 151, 175, 1)',
                    width: '100%',
                    align: 'right',
                    height: 35
                },
                ablightgreen: {
                    backgroundColor: 'rgba(210, 245, 166, 1)',
                    width: '100%',
                    align: 'right',
                    height: 40
                }
            }
        }
    }
    var total1 = list1[0].value + list1[1].value// 岗位总数
    // 饼图的展示数据
    var data = list1.map(function (item, index) {
        if (item.name === '管理人员') {
            item.label = glLabel
        }
        if (item.name === '总人数') {
            item.value = ''
        }
        return item
    })
    var option = {
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
            bottom: '10%',
            itemGap: 20,
            itemWidth: 8,
            itemHeight: 8,
            selectedMode: false,
            data: [
                {
                    name: '总人数'
                },
                {
                    name: '普通员工',
                    icon: 'circle'
                },
                {
                    name: '管理人员',
                    icon: 'circle'
                }
            ],
            formatter: function (name) {
                var curIndex = name === '管理人员' ? 0 : 1
                if (name === '总人数') {
                    return '{prft16|' + name + '} {ft16|' + total1 + '人}'
                }
                total1 = total1 || 1
                return (
                    '{pr|' +
                    name +
                    '}{verHr|}' +
                    '{perc|' +
                    (list1[curIndex].value * 100/ total1).toFixed(2) +
                    '%}' +
                    list1[curIndex].value +
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
                radius: ['40%', '60%'],
                center: ['40%', '50%'],
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

// 配置婚姻状况
/**
 * @param titleConf 表格title的总样式[必填]
 * @param type 区分在职，离职[可选]
 * @param ins 得到的对应echarts表格实例[可选]
 * @param opts 配置项[可选]
 * @prop list[Array] 性别数据
 */
function confHy(titleConf, type, ins, opts) {
    // 基于准备好的dom，初始化echarts实例
    var chartIns
    type = type || 0
    if (!ins) {
        if (type === 0) {
            chartIns = echarts.init($('#inService .hy')[0], 'macarons')
        } else {
            chartIns = echarts.init($('#hired .hy')[0], 'macarons')
        }
    } else {
        chartIns = ins
    }

    // 指定图表的配置项和数据
    var curTitle = $.extend({}, titleConf, {
        text: '婚姻状况'
    })

    // list模板
    var list = (opts && opts.list) || [
            {value: 0, name: '已婚'},
            {value: 0, name: '未婚'}
        ]
    // 总人数
    var total = 0
    var legendData = [{
        name: '总人数'
    }];
    list.forEach(function (item, index) {
        total += Number(item.value)
        legendData.push({ name:item.name, icon: 'circle'})
    })
    // 拼接总人数
    list = list.concat({name: '总人数'})

    var option = {
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
            left: '24%',
            bottom: '5%',
            itemGap: 20,
            itemWidth: 8,
            itemHeight: 8,
            selectedMode: false,
            data: legendData,
            formatter: function (name) {
                var curIndex = name === '已婚' ? 0 : 1
                if (name === '总人数') {
                    return '{prft16|' + name + '}{ft16|' + total + '人}'
                }
                total = total || 1
                return (
                    name +
                    '{plf|' +
                    list[curIndex].value +
                    '人}' +
                    (list[curIndex].value * 100/ total).toFixed(2) +
                    '%'
                )
            },
            textStyle: {
                fontSize: 12,
                rich: {
                    plf: {
                        padding: [0, 30, 0, 40]
                    },
                    prft16: {
                        padding: [0, 15, 0, 0],
                        fontSize: 15
                    },
                    ft16: {
                        fontSize: 15
                    }
                }
            }
        },
        series: [
            {
                type: 'pie',
                selectedMode: 'single',
                radius: ['35%', '65%'],
                center: ['50%', '40%'],
                labelLine: {
                    normal: {
                        show: true
                    }
                },
                label: {
                    normal: {
                        show: true
                    }
                },
                data: list
            }
        ]
    }

    chartIns.setOption(option)
    return chartIns
}

// 配置人员状态
/**
 * @param titleConf 表格title的总样式[必填]
 * @param type 区分在职，离职[可选]
 * @param ins 得到的对应echarts表格实例[可选]
 * @param opts 配置项[可选]
 * @prop list[Array] 性别数据
 */
function confZt(titleConf, type, ins, opts) {
    // 基于准备好的dom，初始化echarts实例
    var chartIns
    type = type || 0
    if (!ins) {
        if (type === 0) {
            chartIns = echarts.init($('#inService .zt')[0], 'macarons')
        } else {
            chartIns = echarts.init($('#hired .zt')[0], 'macarons')
        }
    } else {
        chartIns = ins
    }
    // 指定图表的配置项和数据
    var curTitle = $.extend({}, titleConf, {
        text: '人员状态'
    })
    // list模板
    var list = (opts && opts.list) || [
            {value: 0, name: '正式员工'},
            {value: 0, name: '试用员工'}
        ]
    // 总人数
    var total = 0
    var legendData = [{name: '总人数'}];
    list.forEach(function (item, index) {
        total += Number(item.value)
        legendData.push({name:item.name, icon: 'circle'});
    })
    // 拼接总人数
    list = list.concat({name: '总人数'})
    var option = {
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
            left: '24%',
            bottom: '5%',
            itemGap: 20,
            itemWidth: 8,
            itemHeight: 8,
            selectedMode: false,
            data: legendData,
            formatter: function (name) {
                var curPerc = 0
                var curPers = 0
                var count = total;
                total = total > 0 ? total : 1
                list.forEach(function (item, index) {
                    if (name === item.name) {
                        curPerc = (item.value * 100 / total).toFixed(2)
                        curPers = item.value
                    }
                })
                if (name === '总人数') {
                    return '{prft16|' + name + '}{ft16|' + count + '人}'
                }
                return (
                name +
                '{plf|' +
                curPers +
                '人}' +
                curPerc +
                '%'

                )
            },
            textStyle: {
                fontSize: 12,
                rich: {
                    plf: {
                        padding: [0, 30, 0, 40]
                    },
                    prft16: {
                        padding: [0, 15, 0, 0],
                        fontSize: 15
                    },
                    ft16: {
                        fontSize: 15
                    }
                }
            }
        },
        series: [
            {
                type: 'pie',
                selectedMode: 'single',
                radius: ['35%', '65%'],
                center: ['50%', '40%'],
                labelLine: {
                    normal: {
                        show: true
                    }
                },
                label: {
                    normal: {
                        show: true,
                        formatter: function (o) {
                            if (o.name === '正式员工') {
                                return '正式\n员工'
                            }else if(o.name === '试用员工'){
                                return '试用\n员工'
                            }else if(o.name === '离职员工'){
                                return '离职\n员工'
                            }else if(o.name === '调离员工'){
                                return '调离\n员工'
                            }
                            return '试用\n员工'
                        }
                    }
                },
                data: list
            }
        ]
    }

    chartIns.setOption(option)
    return chartIns
}

// 判断基础概况中哪些需要带nomr
function checkAddNomr() {
    var $baseCondEl = $('.kxz-baseCond')
    var stArr = JSON.parse($baseCondEl.find('.openBtn').data('seltype'))

    // 2个模板上带nomr
    // 第一种情况
    var case1 = JSON.stringify([
        {
            type: 'nl',
            wth: 49.5
        },
        {
            type: 'zn',
            wth: 49.5
        }
    ])
    // 第二种情况
    var case2 = JSON.stringify([
        {
            type: 'gl',
            wth: 42
        },
        {
            type: 'xl',
            wth: 35
        },
        {
            type: 'xb',
            wth: 21
        }
    ])

    // 如果属于第一种情况
    if (stArr.length >= 3 && JSON.stringify(stArr.slice(0, 3)) === case2) {
        $baseCondEl.find('.layui-show .xb').addClass('nomr')
    }
    if (
        (stArr.length >= 2 && JSON.stringify(stArr.slice(0, 2)) === case1) ||
        (stArr.length >= 5 && JSON.stringify(stArr.slice(3, 5)) === case1)
    ) {
        $baseCondEl.find('.layui-show .zn').addClass('nomr')
        return
    }
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
    var initCase = JSON.stringify([
        {
            type: 'gl',
            wth: 42
        },
        {
            type: 'xl',
            wth: 35
        },
        {
            type: 'xb',
            wth: 21
        }
    ])
    var filmStaffIns // 公司人员的图表
    var depStaffIns // 部门人员的图表
    var type = 0 // 标记在职 0 离职 1
    // 管理在职图表实例
    var inService = {
        gl: {
            ins: null,
            data: null
        },
        xl: {
            ins: null,
            data: null
        },
        xb: {
            ins: null,
            data: null
        },
        nl: {
            ins: null,
            data: null
        },
        zn: {
            ins: null,
            data: null
        },
        cj: {
            ins: null,
            data: null
        },
        hy: {
            ins: null,
            data: null
        },
        zt: {
            ins: null,
            data: null
        }
    }
    // 管理离职图表实例
    var hired = {
        gl: {
            ins: null,
            data: null
        },
        xl: {
            ins: null,
            data: null
        },
        xb: {
            ins: null,
            data: null
        },
        nl: {
            ins: null,
            data: null
        },
        zn: {
            ins: null,
            data: null
        },
        cj: {
            ins: null,
            data: null
        },
        hy: {
            ins: null,
            data: null
        },
        zt: {
            ins: null,
            data: null
        }
    }

    var date = '';
    var startDate = '';
    var endDate = '';

    $('.kxz-baseCond__toolBox .openBtn').data('seltype', initCase)

    // 初始化公司人员数量统计
    $.get('analysis/personNum', {}, function (result) {
        filmStaffIns = confFilmStaff(titleConf, filmStaffIns, result.data)
        filmStaffIns.on('click', function(params) {
            date = params.name // 拿到时间区间调接口重新渲染图表
            departmentPersonNum();
            workage();
            education();
            gender();
            renderChartBySeltype();
        })
    })

    // 初始化部门人员
    departmentPersonNum();
    function departmentPersonNum() {
        $.get('analysis/departmentPersonNum', {date:date}, function (result) {
            confDepStaff(titleConf, depStaffIns, result.data)
        })
    }


    // 初始化基础概况图表[初始化所有的视图实例]
    // 初始化工龄
    workage()
    function workage() {
        $.get('analysis/workage', {type:type,date:date,startDate:startDate,endDate:endDate}, function (result) {
            inService.gl.ins = confGl(titleConf, type, null, result.data)
        })
    }


    // 初始化学历
    education();
    function education() {
        $.get('analysis/education', {type:type,date:date,startDate:startDate,endDate:endDate}, function (result) {
            inService.xl.ins = confXl(titleConf, type, null, result.data)
        })
    }


    // 初始化性别
    gender();
    function gender() {
        $.get('analysis/gender', {type:type,date:date,startDate:startDate,endDate:endDate}, function (result) {
            inService.xb.ins = confXb(titleConf, type, null, result.data)
        })
    }


    // 初始化基础概况的交互部分
    initBaseInteractive()
    checkAddNomr()
    // 加载结束

    // 配置基础概况交互部分
    function initBaseInteractive() {

        // 箭头交互
        $('.kxz-baseCond__toolBox .openBtn').click(function () {
            if ($(this).hasClass('active')) {
                cancelEffect()
            } else {
                $(this)
                    .addClass('active')
                    .siblings('.toolbarBox')
                    .fadeIn()
            }
        })
        // 标签交互
        $('.kxz-baseCond__toolBox .toolbarItem').click(function () {
            $(this).toggleClass('active')
        })
        // 确定 取消按钮的交互
        $('.kxz-baseCond__toolBox .cancel').click(function () {
            cancelEffect()
        })

        $('.kxz-baseCond__toolBox .confirm').click(function () {
            var selTypes = []
            var that = this
            $(this)
                .parent()
                .siblings('ul')
                .children()
                .each(function (index, dom) {
                    var curType = $(dom).data('type')
                    var curWth = $(dom).data('wth')
                    if ($(dom).hasClass('active')) {
                        selTypes.push({
                            type: curType,
                            wth: curWth
                        })
                        $(that)
                            .closest('.kxz-baseCond')
                            .find('.' + curType + '_item')
                            .addClass('show')
                    } else {
                        $(that)
                            .closest('.kxz-baseCond')
                            .find('.' + curType + '_item')
                            .removeClass('show')
                    }
                })
            $(this)
                .closest('.toolbarBox')
                .fadeOut()
                .siblings('.openBtn')
                .data('seltype', JSON.stringify(selTypes))
                .removeClass('active')
            checkAddNomr()
            renderChartBySeltype()
        })

        function cancelEffect() {
            var $toolBoxEl = $('.kxz-baseCond__toolBox')
            var selTypes = JSON.parse($toolBoxEl.find('.openBtn').data('seltype'))
            $toolBoxEl.find('.toolbarItem').each(function (index, dom) {
                if (
                    selTypes.some(function (item) {
                        return item.type === $(dom).data('type')
                    })
                ) {
                    $(dom).addClass('active')
                } else {
                    $(dom).removeClass('active')
                }
            })
            $toolBoxEl
                .children('.toolbarBox')
                .fadeOut()
                .siblings('.openBtn')
                .removeClass('active')
        }

        // 监听tab切换
        layui.use([ 'laydate', 'table', 'element'], function () {
            var element = layui.element
            var laydate = layui.laydate

            element.on('tab(baseCond__tabs)', function (el) {
                type = $(this).html() === '在职人员' ? 0 : 1
                $('#leaveJob_rangeDate').val('');
                endDate = '';
                startDate = '';
                checkAddNomr();
                renderChartBySeltype()
            })

            laydate.render({
                elem: '#leaveJob_rangeDate', //指定元素
                type: 'date',
                range: '~',
                trigger: 'click',
                done:function(value,date){//value, date, endDate点击日期、清空、现在、确定均会触发。回调返回三个参数，分别代表：生成的值、日期时间对象、结束的日期时间对象
                    $('#leaveJob_rangeDate').val(value);
                    if(value != null && value !=''){
                        endDate = value.split(' ~ ')[1];
                        startDate = value.split(' ~ ')[0];
                    }else{
                        endDate = '';
                        startDate = '';
                    }
                    workage();
                    education();
                    gender();
                    renderChartBySeltype()
                }
            })
        })
    }
    // 根据seltype进行渲染
    function renderChartBySeltype(data) {
        var seltype = JSON.parse(
            $('.kxz-baseCond__toolBox .openBtn').data('seltype')
        )
        var types = []
        var curIns = null
        var curData = null
        seltype.forEach(function (item, index) {
            types.push(item.type)
        })
        // 工龄
        if (types.indexOf('gl') > -1) {

            $.get('analysis/workage', {type:type,date:date,startDate:startDate,endDate:endDate}, function (result) {
                curData = data ? data : result.data
                var chartIns
                curIns = type === 0 ? inService.gl.ins : hired.gl.ins
                chartIns = confGl(titleConf, type, curIns, curData)
                if (!curIns) {
                    if (type === 0) {
                        inService.gl.ins = chartIns
                    } else {
                        hired.gl.ins = chartIns
                    }
                }
            })

        }
        // 学历
        if (types.indexOf('xl') > -1) {

            $.get('analysis/education', {type:type,date:date,startDate:startDate,endDate:endDate}, function (result) {
                curData = result.data
                var chartIns
                curIns = type === 0 ? inService.xl.ins : hired.xl.ins
                //curData = type === 0 ? inService.xl.data : hired.xl.data
                chartIns = confXl(titleConf, type, curIns, curData)
                if (!curIns) {
                    if (type === 0) {
                        inService.xl.ins = chartIns
                    } else {
                        hired.xl.ins = chartIns
                    }
                }
            })

        }
        // 性别
        if (types.indexOf('xb') > -1) {
            $.get('analysis/gender', {type:type,date:date,startDate:startDate,endDate:endDate}, function (result) {
                var chartIns
                curIns = type === 0 ? inService.xb.ins : hired.xb.ins
                inService.xb.ins = confXb(titleConf, type, null, result.data)
                curData = result.data;
                chartIns = confXb(titleConf, type, curIns, curData)
                if (!curIns) {
                    if (type === 0) {
                        inService.xb.ins = chartIns
                    } else {
                        hired.xb.ins = chartIns
                    }
                }
            })
        }
        // 年龄
        if (types.indexOf('nl') > -1) {
            $.get('analysis/age', {type:type,date:date,startDate:startDate,endDate:endDate}, function (result) {
                curData = result.data;
                var chartIns
                curIns = type === 0 ? inService.nl.ins : hired.nl.ins
                chartIns = confNl(titleConf, type, curIns, curData)
                if (!curIns) {
                    if (type === 0) {
                        inService.nl.ins = chartIns
                    } else {
                        hired.nl.ins = chartIns
                    }
                }
            })

        }
        // 职能
        if (types.indexOf('zn') > -1) {
            $.get('analysis/function', {type:type,date:date,startDate:startDate,endDate:endDate}, function (result) {
                curData = result.data;
                var chartIns
                curIns = type === 0 ? inService.zn.ins : hired.zn.ins
                chartIns = confZn(titleConf, type, curIns, curData)
                if (!curIns) {
                    if (type === 0) {
                        inService.zn.ins = chartIns
                    } else {
                        hired.zn.ins = chartIns
                    }
                }
            })

        }
        // 层级
        if (types.indexOf('cj') > -1) {
            $.get('analysis/stationLevel', {type:type,date:date,startDate:startDate,endDate:endDate}, function (result) {
                curData = result.data;
                var chartIns
                curIns = type === 0 ? inService.cj.ins : hired.cj.ins
                chartIns = confCj(titleConf, type, curIns, curData)
                if (!curIns) {
                    if (type === 0) {
                        inService.cj.ins = chartIns
                    } else {
                        hired.cj.ins = chartIns
                    }
                }
            })

        }
        // 婚姻
        if (types.indexOf('hy') > -1) {
            $.get('analysis/marriage', {type:type,date:date,startDate:startDate,endDate:endDate}, function (result) {
                curData = result.data;
                var chartIns
                curIns = type === 0 ? inService.hy.ins : hired.hy.ins
                chartIns = confHy(titleConf, type, curIns, curData)
                if (!curIns) {
                    if (type === 0) {
                        inService.hy.ins = chartIns
                    } else {
                        hired.hy.ins = chartIns
                    }
                }
            })

        }
        // 状态
        if (types.indexOf('zt') > -1) {
            $.get('analysis/personStatus', {type:type,date:date,startDate:startDate,endDate:endDate}, function (result) {
                curData = result.data;
                var chartIns
                curIns = type === 0 ? inService.zt.ins : hired.zt.ins
                chartIns = confZt(titleConf, type, curIns, curData)
                if (!curIns) {
                    if (type === 0) {
                        inService.zt.ins = chartIns
                    } else {
                        hired.zt.ins = chartIns
                    }
                }
            })

        }
    }
});
