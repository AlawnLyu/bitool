/**
 * 用来在对象的get方法上加入的annotation，通过该annotation说明某个属性所对应的标题
 * @author LYU
 * @create 2018-01-12-9:43
 * @Copyright(C) 2010 - 2018 GBSZ
 * All rights reserved
 */

package com.wtown.util.common.excelutils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelResources {
    /**
     * 属性的标题名称
     * @return
     */
    String title();
    /**
     * 在excel的顺序
     * @return
     */
    int order() default 9999;
}
