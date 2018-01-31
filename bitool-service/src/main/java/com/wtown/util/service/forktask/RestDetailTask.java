/**
 * @author LYU
 * @create 2018年01月15日 9:22
 * @Copyright(C) 2010 - 2018 GBSZ
 * All rights reserved
 */

package com.wtown.util.service.forktask;

import com.wtown.util.common.dateutils.DateUtil;
import com.wtown.util.config.RestaurauntProperties;
import com.wtown.util.dao.RestaurauntDao;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class RestDetailTask extends RecursiveTask<String> {

    private String[] rcodes;

    private int start, end;

    private String startTime, endTime;

    private RestaurauntDao restaurauntDao;

    private RestaurauntProperties properties;

    public RestDetailTask(String[] rcodes, int start, int end, String startTime, String endTime, RestaurauntDao restaurauntDao, RestaurauntProperties properties) {
        super();
        this.rcodes = rcodes;
        this.start = start;
        this.end = end;
        this.startTime = startTime;
        this.endTime = endTime;
        this.restaurauntDao = restaurauntDao;
        this.properties = properties;
    }

    @Override
    protected String compute() {
        String result = "";
        if (end - start < 5) {
            result = processDetail(rcodes, start, end);
        } else {
            int mid = (start + end) / 2;
            RestDetailTask task1 = new RestDetailTask(rcodes, start, mid, startTime, endTime, restaurauntDao, properties);
            RestDetailTask task2 = new RestDetailTask(rcodes, mid + 1, end, startTime, endTime, restaurauntDao, properties);
            invokeAll(task1, task2);
            try {
                result = groupResults(task1.get(), task2.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private String groupResults(String result1, String result2) {
        String groupResult = "";
        if ("".equals(result1)) {
            groupResult = result2;
        } else if ("".equals(result2)) {
            groupResult = result1;
        } else {
            groupResult = result1 + "," + result2;
        }
        return groupResult;
    }

    private String processDetail(String[] rcodes, int start, int end) {
        String result = "";
        List<DateDetailTask> tasks = new LinkedList<DateDetailTask>();
        List<String> dateList = null;
        try {
            dateList = DateUtil.getInstance().getDateList(startTime.split(" ")[0], endTime.split(" ")[0]);
            for (int i = start; i <= end; i++) {
                DateDetailTask task = new DateDetailTask(dateList, 0, dateList.size() - 1, rcodes[i], restaurauntDao, properties);
                tasks.add(task);
            }
            invokeAll(tasks);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < tasks.size(); i++) {
            DateDetailTask task = tasks.get(i);
            try {
                result += task.get() + ",";
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }
        if (result.length() > 0 && ",".equals(result.substring(result.length() - 1))) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }
}
