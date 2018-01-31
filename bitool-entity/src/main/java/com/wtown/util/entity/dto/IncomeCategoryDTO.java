package com.wtown.util.entity.dto;

import com.wtown.util.common.excelutils.ExcelResources;

public class IncomeCategoryDTO {
    private String rname;
    private Double wxamount;
    private Double aliamount;
    private Double totalamount;

    @ExcelResources(title = "餐饮点", order = 1)
    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    @ExcelResources(title = "微信实收", order = 2)
    public Double getWxamount() {
        return wxamount;
    }

    public void setWxamount(Double wxamount) {
        this.wxamount = wxamount;
    }

    @ExcelResources(title = "支付宝实收", order = 3)
    public Double getAliamount() {
        return aliamount;
    }

    public void setAliamount(Double aliamount) {
        this.aliamount = aliamount;
    }

    @ExcelResources(title = "实收总金额", order = 4)
    public Double getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(Double totalamount) {
        this.totalamount = totalamount;
    }

    @Override
    public String toString() {
        return "IncomeCategoryDTO{" +
                "rname='" + rname + '\'' +
                ", wxamount=" + wxamount +
                ", aliamount=" + aliamount +
                ", totalamount=" + totalamount +
                '}';
    }
}
