/**
 * @author LYU
 * @create 2018年01月11日 16:10
 * @Copyright(C) 2010 - 2018 GBSZ
 * All rights reserved
 */

package com.wtown.util.service.impl;

import com.wtown.util.config.RestaurauntProperties;
import com.wtown.util.service.RestaurantService;
import com.wtown.util.service.executor.AsyncTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurauntProperties properties;

    @Autowired
    private AsyncTask asyncTask;

    @Override
    public String getDetailData(String rCode, String startTime, String endTime) {
        asyncTask.restDetailAsyncTask(rCode, startTime, endTime);
        String part1 = startTime.split(" ")[0].replace("-", "");
        String part2 = endTime.split(" ")[0].replace("-", "");
        return String.format(properties.getDetailFile(), part1, part2);
    }

    @Override
    public String getSummaryData(String rCode, String startTime, String endTime) {
        asyncTask.restSummaryAsyncTask(rCode, startTime, endTime);
        String part1 = startTime.split(" ")[0].replace("-", "");
        String part2 = endTime.split(" ")[0].replace("-", "");
        return String.format(properties.getSummaryFile(), part1, part2);
    }
}
