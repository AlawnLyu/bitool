/**
 * @author LYU
 * @create 2018-01-11-13:34
 * @Copyright(C) 2010 - 2018 GBSZ
 * All rights reserved
 */

package com.wtown.util.dao;

import com.wtown.util.entity.dto.DetailDTO;
import com.wtown.util.entity.dto.IncomeCategoryDTO;
import com.wtown.util.entity.dto.SummaryDTO;

import java.util.List;

public interface RestaurauntDao {

    List<DetailDTO> getDetailData(String rCode,String startTime,String endTime);

    List<SummaryDTO> getSummaryData(String rCode, String startTime, String endTime);

    List<IncomeCategoryDTO> getIncome(String rCode, String startTime, String endTime);
}
