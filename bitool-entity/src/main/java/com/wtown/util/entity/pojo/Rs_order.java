package com.wtown.util.entity.pojo;

public class Rs_order {
  private Long sqlid;
  private String fcode;
  private Double unitprice;
  private Long num;
  private String bcode;
  private String createtime;

  public Long getSqlid() {
    return sqlid;
  }

  public void setSqlid(Long sqlid) {
    this.sqlid = sqlid;
  }

  public String getFcode() {
    return fcode;
  }

  public void setFcode(String fcode) {
    this.fcode = fcode;
  }

  public Double getUnitprice() {
    return unitprice;
  }

  public void setUnitprice(Double unitprice) {
    this.unitprice = unitprice;
  }

  public Long getNum() {
    return num;
  }

  public void setNum(Long num) {
    this.num = num;
  }

  public String getBcode() {
    return bcode;
  }

  public void setBcode(String bcode) {
    this.bcode = bcode;
  }

  public String getCreatetime() {
    return createtime;
  }

  public void setCreatetime(String createtime) {
    this.createtime = createtime;
  }

  @Override
  public String toString() {
    return "Rs_order{" +
            "sqlid=" + sqlid +
            ", fcode='" + fcode + '\'' +
            ", unitprice=" + unitprice +
            ", num=" + num +
            ", bcode='" + bcode + '\'' +
            ", createtime='" + createtime + '\'' +
            '}';
  }
}
