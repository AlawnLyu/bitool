package com.wtown.util.entity.pojo;

public class Rs_bill {
  private Long sqlid;
  private String bcode;
  private String userid;
  private String rcode;
  private String tcode;
  private Double amount;
  private String createtime;
  private Long ostatus;

  public Long getSqlid() {
    return sqlid;
  }

  public void setSqlid(Long sqlid) {
    this.sqlid = sqlid;
  }

  public String getBcode() {
    return bcode;
  }

  public void setBcode(String bcode) {
    this.bcode = bcode;
  }

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public String getRcode() {
    return rcode;
  }

  public void setRcode(String rcode) {
    this.rcode = rcode;
  }

  public String getTcode() {
    return tcode;
  }

  public void setTcode(String tcode) {
    this.tcode = tcode;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getCreatetime() {
    return createtime;
  }

  public void setCreatetime(String createtime) {
    this.createtime = createtime;
  }

  public Long getOstatus() {
    return ostatus;
  }

  public void setOstatus(Long ostatus) {
    this.ostatus = ostatus;
  }

  @Override
  public String toString() {
    return "Rs_bill{" +
            "sqlid=" + sqlid +
            ", bcode='" + bcode + '\'' +
            ", userid='" + userid + '\'' +
            ", rcode='" + rcode + '\'' +
            ", tcode='" + tcode + '\'' +
            ", amount=" + amount +
            ", createtime='" + createtime + '\'' +
            ", ostatus=" + ostatus +
            '}';
  }
}
