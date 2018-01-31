/**
 * @author LYU
 * @create 2018年01月11日 13:34
 * @Copyright(C) 2010 - 2018 GBSZ
 * All rights reserved
 */

package com.wtown.util.dao.impl;

import com.wtown.util.dao.RestaurauntDao;
import com.wtown.util.dao.mapper.RestaurauntBiMapper;
import com.wtown.util.entity.dto.DetailDTO;
import com.wtown.util.entity.dto.IncomeCategoryDTO;
import com.wtown.util.entity.dto.SummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RestaurauntDaoImpl implements RestaurauntDao {

    @Autowired
    private RestaurauntBiMapper restaurauntBiMapper;

    @Override
    public List<DetailDTO> getDetailData(String rCode, String startTime, String endTime) {
        return restaurauntBiMapper.getDetailData(rCode, startTime, endTime);
    }

    @Override
    public List<SummaryDTO> getSummaryData(String rCode, String startTime, String endTime) {
        return restaurauntBiMapper.getSummaryData(rCode, startTime, endTime);
    }

    @Override
    public List<IncomeCategoryDTO> getIncome(String rCode, String startTime, String endTime) {
        return restaurauntBiMapper.getIncome(rCode, startTime, endTime);
    }
}
