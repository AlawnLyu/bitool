/**
 * @author LYU
 * @create 2018年01月17日 13:29
 * @Copyright(C) 2010 - 2018 GBSZ
 * All rights reserved
 */

package com.wtown.util.service.forktask;

import com.wtown.util.config.RestaurauntProperties;

import java.util.concurrent.RecursiveTask;

public class CombineDetailTask extends RecursiveTask<String> {

    private String[] restArray;

    private int start, end;

    private String[] fileArray;

    private RestaurauntProperties properties;

    public CombineDetailTask(String[] restArray, int start, int end, String[] fileArray, RestaurauntProperties properties) {
        this.restArray = restArray;
        this.start = start;
        this.end = end;
        this.fileArray = fileArray;
        this.properties = properties;
    }

    @Override
    protected String compute() {
        return null;
    }
}
