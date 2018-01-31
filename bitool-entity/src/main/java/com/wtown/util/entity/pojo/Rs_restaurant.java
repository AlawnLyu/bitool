package com.wtown.util.entity.pojo;

public class Rs_restaurant {
  private Long sqlid;
  private String rcode;
  private String rname;
  private Long free;

  public Long getSqlid() {
    return sqlid;
  }

  public void setSqlid(Long sqlid) {
    this.sqlid = sqlid;
  }

  public String getRcode() {
    return rcode;
  }

  public void setRcode(String rcode) {
    this.rcode = rcode;
  }

  public String getRname() {
    return rname;
  }

  public void setRname(String rname) {
    this.rname = rname;
  }

  public Long getFree() {
    return free;
  }

  public void setFree(Long free) {
    this.free = free;
  }

  @Override
  public String toString() {
    return "Rs_restaurant{" +
            "sqlid=" + sqlid +
            ", rcode='" + rcode + '\'' +
            ", rname='" + rname + '\'' +
            ", free=" + free +
            '}';
  }
}
