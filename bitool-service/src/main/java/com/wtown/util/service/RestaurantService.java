/**
 * @author LYU
 * @create 2018-01-11-16:09
 * @Copyright(C) 2010 - 2018 GBSZ
 * All rights reserved
 */

package com.wtown.util.service;

public interface RestaurantService {

    String getDetailData(String rCode, String startTime, String endTime);

    String getSummaryData(String rCode, String startTime, String endTime);
}
