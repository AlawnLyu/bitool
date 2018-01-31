package com.wtown.util.entity.pojo;

public class Rs_food {
  private Long sqlid;
  private String mcode;
  private String ftcode;
  private String cdcode;
  private String fcode;
  private String fname_cn;
  private String fname_en;
  private String unit;
  private Double unitprice;
  private Double costprice;
  private String simplecode;
  private String fpic;
  private String fdetail;
  private Long preference;
  private Long showflag;
  private Long limitnum;
  private Long status;

  public Long getSqlid() {
    return sqlid;
  }

  public void setSqlid(Long sqlid) {
    this.sqlid = sqlid;
  }

  public String getMcode() {
    return mcode;
  }

  public void setMcode(String mcode) {
    this.mcode = mcode;
  }

  public String getFtcode() {
    return ftcode;
  }

  public void setFtcode(String ftcode) {
    this.ftcode = ftcode;
  }

  public String getCdcode() {
    return cdcode;
  }

  public void setCdcode(String cdcode) {
    this.cdcode = cdcode;
  }

  public String getFcode() {
    return fcode;
  }

  public void setFcode(String fcode) {
    this.fcode = fcode;
  }

  public String getFname_cn() {
    return fname_cn;
  }

  public void setFname_cn(String fname_cn) {
    this.fname_cn = fname_cn;
  }

  public String getFname_en() {
    return fname_en;
  }

  public void setFname_en(String fname_en) {
    this.fname_en = fname_en;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public Double getUnitprice() {
    return unitprice;
  }

  public void setUnitprice(Double unitprice) {
    this.unitprice = unitprice;
  }

  public Double getCostprice() {
    return costprice;
  }

  public void setCostprice(Double costprice) {
    this.costprice = costprice;
  }

  public String getSimplecode() {
    return simplecode;
  }

  public void setSimplecode(String simplecode) {
    this.simplecode = simplecode;
  }

  public String getFpic() {
    return fpic;
  }

  public void setFpic(String fpic) {
    this.fpic = fpic;
  }

  public String getFdetail() {
    return fdetail;
  }

  public void setFdetail(String fdetail) {
    this.fdetail = fdetail;
  }

  public Long getPreference() {
    return preference;
  }

  public void setPreference(Long preference) {
    this.preference = preference;
  }

  public Long getShowflag() {
    return showflag;
  }

  public void setShowflag(Long showflag) {
    this.showflag = showflag;
  }

  public Long getLimitnum() {
    return limitnum;
  }

  public void setLimitnum(Long limitnum) {
    this.limitnum = limitnum;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Rs_food{" +
            "sqlid=" + sqlid +
            ", mcode='" + mcode + '\'' +
            ", ftcode='" + ftcode + '\'' +
            ", cdcode='" + cdcode + '\'' +
            ", fcode='" + fcode + '\'' +
            ", fname_cn='" + fname_cn + '\'' +
            ", fname_en='" + fname_en + '\'' +
            ", unit='" + unit + '\'' +
            ", unitprice=" + unitprice +
            ", costprice=" + costprice +
            ", simplecode='" + simplecode + '\'' +
            ", fpic='" + fpic + '\'' +
            ", fdetail='" + fdetail + '\'' +
            ", preference=" + preference +
            ", showflag=" + showflag +
            ", limitnum=" + limitnum +
            ", status=" + status +
            '}';
  }
}
