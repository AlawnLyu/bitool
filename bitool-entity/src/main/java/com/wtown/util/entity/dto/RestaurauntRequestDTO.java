/**
 * @author LYU
 * @create 2018年01月13日 8:18
 * @Copyright(C) 2010 - 2018 GBSZ
 * All rights reserved
 */

package com.wtown.util.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RestaurauntRequestDTO {
    @JsonProperty("rcode")
    private String rCode;
    @JsonProperty("starttime")
    private String startTime;
    @JsonProperty("endtime")
    private String endTime;

    public RestaurauntRequestDTO() {
    }

    public String getrCode() {
        return rCode;
    }

    public void setrCode(String rCode) {
        this.rCode = rCode;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "RestaurauntRequestDTO{" +
                "rCode='" + rCode + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
