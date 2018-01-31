/**
 * @author LYU
 * @create 2018年01月13日 8:48
 * @Copyright(C) 2010 - 2018 GBSZ
 * All rights reserved
 */

package com.wtown.util.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("bitool.restauraunt")
public class RestaurauntProperties {
    private String detailFile;

    private String detailTempOutFile;

    private String detailTempFile;

    private String summaryFile;

    private String summaryTempOutFile;

    private String path;

    public String getDetailFile() {
        return detailFile;
    }

    public void setDetailFile(String detailFile) {
        this.detailFile = detailFile;
    }

    public String getDetailTempOutFile() {
        return detailTempOutFile;
    }

    public void setDetailTempOutFile(String detailTempOutFile) {
        this.detailTempOutFile = detailTempOutFile;
    }

    public String getDetailTempFile() {
        return detailTempFile;
    }

    public void setDetailTempFile(String detailTempFile) {
        this.detailTempFile = detailTempFile;
    }

    public String getSummaryFile() {
        return summaryFile;
    }

    public void setSummaryFile(String summaryFile) {
        this.summaryFile = summaryFile;
    }

    public String getSummaryTempOutFile() {
        return summaryTempOutFile;
    }

    public void setSummaryTempOutFile(String summaryTempOutFile) {
        this.summaryTempOutFile = summaryTempOutFile;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
