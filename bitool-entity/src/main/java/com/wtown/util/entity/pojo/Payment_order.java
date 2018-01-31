package com.wtown.util.entity.pojo;

public class Payment_order {
  private Long id;
  private String orderid;
  private String ordertype;
  private String paytype;
  private String appid;
  private Double amount;
  private String productname;
  private String createtime;
  private Long notify;
  private String notifyurl;
  private String returnurl;
  private String payid;
  private String path;
  private String paytime;
  private String usedate;
  private String createdate;
  private Long refunded;
  private Long invoiced;
  private Long send;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getOrderid() {
    return orderid;
  }

  public void setOrderid(String orderid) {
    this.orderid = orderid;
  }

  public String getOrdertype() {
    return ordertype;
  }

  public void setOrdertype(String ordertype) {
    this.ordertype = ordertype;
  }

  public String getPaytype() {
    return paytype;
  }

  public void setPaytype(String paytype) {
    this.paytype = paytype;
  }

  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getProductname() {
    return productname;
  }

  public void setProductname(String productname) {
    this.productname = productname;
  }

  public String getCreatetime() {
    return createtime;
  }

  public void setCreatetime(String createtime) {
    this.createtime = createtime;
  }

  public Long getNotify() {
    return notify;
  }

  public void setNotify(Long notify) {
    this.notify = notify;
  }

  public String getNotifyurl() {
    return notifyurl;
  }

  public void setNotifyurl(String notifyurl) {
    this.notifyurl = notifyurl;
  }

  public String getReturnurl() {
    return returnurl;
  }

  public void setReturnurl(String returnurl) {
    this.returnurl = returnurl;
  }

  public String getPayid() {
    return payid;
  }

  public void setPayid(String payid) {
    this.payid = payid;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getPaytime() {
    return paytime;
  }

  public void setPaytime(String paytime) {
    this.paytime = paytime;
  }

  public String getUsedate() {
    return usedate;
  }

  public void setUsedate(String usedate) {
    this.usedate = usedate;
  }

  public String getCreatedate() {
    return createdate;
  }

  public void setCreatedate(String createdate) {
    this.createdate = createdate;
  }

  public Long getRefunded() {
    return refunded;
  }

  public void setRefunded(Long refunded) {
    this.refunded = refunded;
  }

  public Long getInvoiced() {
    return invoiced;
  }

  public void setInvoiced(Long invoiced) {
    this.invoiced = invoiced;
  }

  public Long getSend() {
    return send;
  }

  public void setSend(Long send) {
    this.send = send;
  }

  @Override
  public String toString() {
    return "Payment_order{" +
            "id=" + id +
            ", orderid='" + orderid + '\'' +
            ", ordertype='" + ordertype + '\'' +
            ", paytype='" + paytype + '\'' +
            ", appid='" + appid + '\'' +
            ", amount=" + amount +
            ", productname='" + productname + '\'' +
            ", createtime='" + createtime + '\'' +
            ", notify=" + notify +
            ", notifyurl='" + notifyurl + '\'' +
            ", returnurl='" + returnurl + '\'' +
            ", payid='" + payid + '\'' +
            ", path='" + path + '\'' +
            ", paytime='" + paytime + '\'' +
            ", usedate='" + usedate + '\'' +
            ", createdate='" + createdate + '\'' +
            ", refunded=" + refunded +
            ", invoiced=" + invoiced +
            ", send=" + send +
            '}';
  }
}
