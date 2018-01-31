/**
 * @author LYU
 * @create 2018年01月11日 13:28
 * @Copyright(C) 2010 - 2018 GBSZ
 * All rights reserved
 */

package com.wtown.util.entity.dto;

import com.wtown.util.common.excelutils.ExcelResources;

public class DetailDTO {

    private String fname_cn;
    private Double unitprice;
    private Long num;
    private Double totalprice;
    private String orderid;
    private String payid;
    private String paytime;
    private String paytype;
    private String rname;

    @ExcelResources(title="菜名",order=1)
    public String getFname_cn() {
        return fname_cn;
    }

    public void setFname_cn(String fname_cn) {
        this.fname_cn = fname_cn;
    }

    @ExcelResources(title="单价",order=2)
    public Double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Double unitprice) {
        this.unitprice = unitprice;
    }

    @ExcelResources(title="数量",order=3)
    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    @ExcelResources(title="应收总价",order=4)
    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    @ExcelResources(title="订单号",order=5)
    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    @ExcelResources(title="商户订单号",order=6)
    public String getPayid() {
        return payid;
    }

    public void setPayid(String payid) {
        this.payid = payid;
    }

    @ExcelResources(title="支付时间",order=7)
    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }

    @ExcelResources(title="支付方式",order=8)
    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        if ("weixin".equals(paytype)) {
            this.paytype = "微信";
        } else if ("alipay".equals(paytype)) {
            this.paytype = "支付宝";
        } else {
            this.paytype = "未支付";
        }
    }

    @ExcelResources(title="餐饮点",order=9)
    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    @Override
    public String toString() {
        return "DetailDTO{" +
                "fname_cn='" + fname_cn + '\'' +
                ", unitprice=" + unitprice +
                ", num=" + num +
                ", totalprice=" + totalprice +
                ", orderid='" + orderid + '\'' +
                ", payid='" + payid + '\'' +
                ", paytime='" + paytime + '\'' +
                ", paytype='" + paytype + '\'' +
                ", rname='" + rname + '\'' +
                '}';
    }
}
