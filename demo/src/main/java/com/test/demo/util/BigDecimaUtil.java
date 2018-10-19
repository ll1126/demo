package com.test.demo.util;

import java.math.BigDecimal;


/**
 * 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精
 * 确的浮点数运算，包括加减乘除和四舍五入。
 */
public class BigDecimaUtil {
    /**
     * 精确的加法运算
     *
     * 小数位默认保留2位，保留方式是四舍五入，如需其他方式请自行传值
     *
     * @param num1         加数
     * @param num2         被加数
     * @param scale        保留小数位
     * @param roundingMode 保留小数方式 例如:BigDecimal.ROUND_HALF_UP
     * @return
     */
    public static double add(Object num1, Object num2, Integer scale, Integer roundingMode) {
        if (!numVerify(num1, num2)) {
            return 0;
        }
        scale = scaleVerify(scale);
        BigDecimal b1 = new BigDecimal(String.valueOf(num1));
        BigDecimal b2 = new BigDecimal(String.valueOf(num2));
        return b1.add(b2).setScale(scale, roundingMode == null ? BigDecimal.ROUND_HALF_UP : roundingMode).doubleValue();
    }

    /**
     * 精确的减法运算
     *
     * 小数位默认保留2位，保留方式是四舍五入，如需其他方式请自行传值
     *
     * @param num1         减数
     * @param num2         被减数
     * @param scale        保留小数位
     * @param roundingMode 保留小数方式 例如:BigDecimal.ROUND_HALF_UP
     * @return
     */
    public static double sub(Object num1, Object num2, Integer scale, Integer roundingMode) {
        if (!numVerify(num1, num2)) {
            return 0;
        }
        scale = scaleVerify(scale);
        BigDecimal b1 = new BigDecimal(String.valueOf(num1));
        BigDecimal b2 = new BigDecimal(String.valueOf(num2));
        return b1.subtract(b2).setScale(scale, roundingMode == null ? BigDecimal.ROUND_HALF_UP : roundingMode).doubleValue();
    }

    /**
     * 精确的乘法运算
     *
     * 小数位默认保留2位，保留方式是四舍五入，如需其他方式请自行传值
     *
     * @param num1         乘数
     * @param num2         被乘数
     * @param scale        保留小数位
     * @param roundingMode 保留小数方式 例如:BigDecimal.ROUND_HALF_UP
     * @return
     */
    public static double mul(Object num1, Object num2, Integer scale, Integer roundingMode) {
        if (!numVerify(num1, num2)) {
            return 0;
        }
        scale = scaleVerify(scale);
        BigDecimal b1 = new BigDecimal(String.valueOf(num1));
        BigDecimal b2 = new BigDecimal(String.valueOf(num2));
        return b1.multiply(b2).setScale(scale, roundingMode == null ? BigDecimal.ROUND_HALF_UP : roundingMode).doubleValue();
    }

    /**
     * 精确的除法运算
     *
     * 小数位默认保留2位，保留方式是四舍五入，如需其他方式请自行传值
     *
     * @param num1         被除数
     * @param num2         除数
     * @param scale        保留小数位
     * @param roundingMode 保留小数方式 例如:BigDecimal.ROUND_HALF_UP
     * @return
     */
    public static double div(Object num1, Object num2, Integer scale, Integer roundingMode) {
        if (!numVerify(num1, num2)) {
            return 0;
        }
        scale = scaleVerify(scale);
        BigDecimal b1 = new BigDecimal(String.valueOf(num1));
        BigDecimal b2 = new BigDecimal(String.valueOf(num2));
        return b1.divide(b2, scale, roundingMode == null ? BigDecimal.ROUND_HALF_UP : roundingMode).doubleValue();
    }

    /**
     * 验证传进来的是不是数字
     *
     * @param num1
     * @param num2
     * @return
     */
    public static boolean numVerify(Object num1, Object num2) {
        if (RegexUtil.isNumeric(num1.toString()) && RegexUtil.isNumeric(num2.toString())) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 保留小数位不能为负数
     *
     * @param scale
     */
    public static Integer scaleVerify(Integer scale) {

        if (scale == null) {
            scale = 2;
        } else {
            if (scale < 0) {
                throw new IllegalArgumentException("The scale must be a positive integer or zero");
            }
        }
        return scale;

    }

    public static void main(String[] args) {
//        String a = "0";
//        String b = "1";
//        System.out.println(add(a, b, 1, null));
//        System.out.println(sub(a, b, 2, null));
//        System.out.println(mul(a, b, 5, null));
//        System.out.println(div(a, b, 4, null));
        System.out.println(numVerify("a",2));


    }


}