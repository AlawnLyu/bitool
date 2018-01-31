/**
 * @author LYU
 * @create 2018年01月15日 15:40
 * @Copyright(C) 2010 - 2018 GBSZ
 * All rights reserved
 */

package com.wtown.util.service.forktask;

import com.wtown.util.common.excelutils.ExcelUtil;
import com.wtown.util.config.RestaurauntProperties;
import com.wtown.util.dao.RestaurauntDao;
import com.wtown.util.entity.dto.DetailDTO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class DateDetailTask extends RecursiveTask<String> {

    private String rcode;

    private int start, end;

    private RestaurauntDao restaurauntDao;

    private RestaurauntProperties properties;

    private List<String> dateList;

    public DateDetailTask(List<String> dateList, int start, int end, String rcode, RestaurauntDao restaurauntDao, RestaurauntProperties properties) {
        this.dateList = dateList;
        this.start = start;
        this.end = end;
        this.rcode = rcode;
        this.restaurauntDao = restaurauntDao;
        this.properties = properties;
    }

    @Override
    protected String compute() {
        String result = "";
        if (end - start < 30) {
            result = processData(dateList.get(start), dateList.get(end));
        } else {
            int mid = (start + end) / 2;
            DateDetailTask task1 = new DateDetailTask(dateList, start, mid, rcode, restaurauntDao, properties);
            DateDetailTask task2 = new DateDetailTask(dateList, mid + 1, end, rcode, restaurauntDao, properties);
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

    private String processData(String startTime, String endTime) {
        String result = "";
        List<DetailDTO> list;
        list = restaurauntDao.getDetailData(rcode, startTime + " 00:00:00", endTime + " 23:59:59");
        if (list.size() > 0) {
            String part1 = startTime.replace("-", "");
            String part2 = endTime.replace("-", "");
            String fileName = String.format(properties.getDetailTempOutFile(), rcode, part1, part2);
            try {
                ExcelUtil.getInstance().exportObj2ExcelByTemplate((Map<String, String>) null, properties.getDetailTempFile(), new FileOutputStream(properties.getPath() + "/" + fileName),
                        list, DetailDTO.class, true);
                result = fileName;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
