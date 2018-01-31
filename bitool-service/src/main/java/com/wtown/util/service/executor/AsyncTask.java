/**
 * @author LYU
 * @create 2018年01月12日 16:42
 * @Copyright(C) 2010 - 2018 GBSZ
 * All rights reserved
 */

package com.wtown.util.service.executor;

import com.wtown.util.common.dateutils.DateUtil;
import com.wtown.util.common.excelutils.POIUtils;
import com.wtown.util.common.fileutils.FileUtil;
import com.wtown.util.config.RestaurauntProperties;
import com.wtown.util.dao.RestaurauntDao;
import com.wtown.util.entity.dto.DetailDTO;
import com.wtown.util.entity.dto.IncomeCategoryDTO;
import com.wtown.util.entity.dto.SummaryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

@Component
public class AsyncTask {
    private final Logger logger = LoggerFactory.getLogger(AsyncTask.class);

    @Autowired
    private RestaurauntProperties properties;

    @Autowired
    private RestaurauntDao restaurauntDao;

    @Async("appAsync")
    public void restDetailAsyncTask(String rCode, String startTime, String endTime) {
        if (logger.isDebugEnabled()) {
            logger.debug("开始执行导出报表任务:");
            logger.debug("导出明细报表");
            logger.debug("rCode:" + rCode);
            logger.debug("startTime:" + startTime);
            logger.debug("endTime:" + endTime);
        }
        String[] rcodes = rCode.split(",");

        Map<String, String> map = new HashMap<String, String>();
        map.put("title", "营业明细报表");
        map.put("date1", startTime + "至" + endTime);
        map.put("date2", "制表时间：" + DateUtil.getInstance().getDate("yyyy-MM-dd HH:mm:ss"));

        POIUtils pu = POIUtils.getInstance();
        List<DetailDTO> list = null;
        for (String code : rcodes) {
            if (logger.isDebugEnabled()) {
                logger.debug("获取数据：" + code);
            }
            list = restaurauntDao.getDetailData(code, startTime, endTime);
            if (!list.isEmpty()) {
                pu.exportDetailObj2Excel(list, DetailDTO.class, map);
            }
        }

        String part1 = startTime.split(" ")[0].replace("-", "");
        String part2 = endTime.split(" ")[0].replace("-", "");
        String fileName = String.format(properties.getDetailFile(), part1, part2);
        pu.writeToFile(properties.getPath() + File.separator + properties.getDetailTempOutFile());
        FileUtil.getInstance().renameFile(properties.getPath(), properties.getDetailTempOutFile(), fileName);
        if (logger.isDebugEnabled()) {
            logger.debug("执行导出报表任务成功");
        }
    }

    @Async("appAsync")
    public void restSummaryAsyncTask(String rCode, String startTime, String endTime) {
        if (logger.isDebugEnabled()) {
            logger.debug("开始执行导出报表任务:");
            logger.debug("导出汇总报表");
            logger.debug("rCode:" + rCode);
            logger.debug("startTime:" + startTime);
            logger.debug("endTime:" + endTime);
        }
        String[] rcodes = rCode.split(",");
        List<SummaryDTO> summarylist = new ArrayList<>();
        List<IncomeCategoryDTO> incomelist = new ArrayList<>();
        for (String code : rcodes) {
            if (logger.isDebugEnabled()) {
                logger.debug("获取数据：" + code);
            }
            List<SummaryDTO> temp1 = restaurauntDao.getSummaryData(code, startTime, endTime);
            if (temp1 != null) {
                summarylist.addAll(temp1);
            }
            List<IncomeCategoryDTO> temp2 = restaurauntDao.getIncome(code, startTime, endTime);
            if (temp2 != null) {
                incomelist.addAll(temp2);
            }
        }
        if (logger.isDebugEnabled()) {
            logger.debug("summary数量：" + summarylist.size());
            logger.debug("income数量：" + incomelist.size());
        }
        String part1 = startTime.split(" ")[0].replace("-", "");
        String part2 = endTime.split(" ")[0].replace("-", "");
        String fileName = String.format(properties.getSummaryFile(), part1, part2);
        if (logger.isDebugEnabled()) {
            logger.debug("汇总报表文件名：" + fileName);
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("title", "营业报表汇总");
        map.put("date1", startTime + "至" + endTime);
        map.put("date2", "制表时间：" + DateUtil.getInstance().getDate("yyyy-MM-dd HH:mm:ss"));
        POIUtils pu = POIUtils.getInstance();
        pu.exportSummaryObj2Excel(summarylist, SummaryDTO.class, incomelist, IncomeCategoryDTO.class, map);
        pu.writeToFile(properties.getPath() + File.separator + properties.getSummaryTempOutFile());
        FileUtil.getInstance().renameFile(properties.getPath(), properties.getSummaryTempOutFile(), fileName);
        if (logger.isDebugEnabled()) {
            logger.debug("执行导出报表任务成功");
        }
    }
}
