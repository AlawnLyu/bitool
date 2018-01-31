package com.wtown.util.entity.dto;

import com.wtown.util.common.excelutils.ExcelResources;

public class SummaryDTO {
    private String rname;
    private String fname_cn;
    private Double unitprice;
    private Long num;
    private String ftname;
    private Double totalprice;

    @ExcelResources(title = "餐饮点", order = 1)
    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    @ExcelResources(title = "菜名", order = 2)
    public String getFname_cn() {
        return fname_cn;
    }

    public void setFname_cn(String fname_cn) {
        this.fname_cn = fname_cn;
    }

    @ExcelResources(title = "单价", order = 3)
    public Double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Double unitprice) {
        this.unitprice = unitprice;
    }

    @ExcelResources(title = "数量", order = 4)
    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    @ExcelResources(title = "菜类", order = 5)
    public String getFtname() {
        return ftname;
    }

    public void setFtname(String ftname) {
        this.ftname = ftname;
    }

    @ExcelResources(title = "应收总价", order = 6)
    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

}
