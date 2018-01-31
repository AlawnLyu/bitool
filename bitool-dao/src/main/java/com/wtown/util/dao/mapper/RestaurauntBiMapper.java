/**
 * @author LYU
 * @create 2018-01-11-8:33
 * @Copyright(C) 2010 - 2018 GBSZ
 * All rights reserved
 */

package com.wtown.util.dao.mapper;

import com.wtown.util.entity.dto.DetailDTO;
import com.wtown.util.entity.dto.IncomeCategoryDTO;
import com.wtown.util.entity.dto.SummaryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

@Mapper
public interface RestaurauntBiMapper {

    @SelectProvider(type = RestaurauntSqlBuilder.class, method = "getDetailData")
    List<DetailDTO> getDetailData(@Param("rcode") String rCode, @Param("starttime") String startTime, @Param("endtime") String endTime);

    @SelectProvider(type = RestaurauntSqlBuilder.class, method = "getSummaryData")
    List<SummaryDTO> getSummaryData(@Param("rcode") String rCode, @Param("starttime") String startTime, @Param("endtime") String endTime);

    @SelectProvider(type = RestaurauntSqlBuilder.class, method = "getIncome")
    List<IncomeCategoryDTO> getIncome(@Param("rcode") String rCode, @Param("starttime") String startTime, @Param("endtime") String endTime);

    class RestaurauntSqlBuilder {
        public String getDetailData(@Param("rcode") String rCode, @Param("starttime") String startTime, @Param("endtime") String endTime) {
            return new SQL() {{
                SELECT("rs_food.fname_cn,rs_order.unitprice,rs_order.num,rs_order.unitprice * rs_order.num as totalprice,payment_order.orderid,payment_order.payid,payment_order.paytime,payment_order.paytype,rs_restaurant.rname");
                FROM("rs_bill");
                INNER_JOIN("rs_order on rs_order.bcode=rs_bill.bcode");
                INNER_JOIN("rs_food on rs_food.fcode=rs_order.fcode");
                INNER_JOIN("rs_restaurant on rs_restaurant.rcode=rs_bill.rcode");
                INNER_JOIN("payment_order on payment_order.orderid=rs_bill.bcode");
                WHERE("rs_bill.rcode = #{rcode}");
                WHERE("notify = '1'");
                WHERE("rs_bill.ostatus IN ('1', '5')");
                WHERE("rs_bill.createtime BETWEEN #{starttime} AND #{endtime}");
            }}.toString();
        }

        public String getSummaryData(@Param("rcode") String rCode, @Param("starttime") String startTime, @Param("endtime") String endTime) {
            return new SQL() {{
                SELECT("rname,fname_cn,rs_order.unitprice,sum(rs_order.num) as num,ftname,sum(rs_order.num) * rs_order.unitprice as totalprice");
                FROM("rs_bill");
                INNER_JOIN("rs_order on rs_order.bcode=rs_bill.bcode");
                INNER_JOIN("rs_food on rs_food.fcode=rs_order.fcode");
                INNER_JOIN("rs_restaurant on rs_restaurant.rcode=rs_bill.rcode");
                INNER_JOIN("rs_foodtype on rs_foodtype.ftcode=rs_food.ftcode");
                WHERE("rs_bill.rcode = #{rcode}");
                WHERE("rs_bill.ostatus IN ('1', '5')");
                WHERE("rs_bill.createtime BETWEEN #{starttime} AND #{endtime}");
                GROUP_BY("rs_food.fcode");
            }}.toString();
        }

        public String getIncome(@Param("rcode") String rCode, @Param("starttime") String startTime, @Param("endtime") String endTime) {
            return new SQL() {{
                SELECT("t1.rname,t1.amount as wxamount,t2.amount as aliamount,t1.amount+t2.amount as totalamount");
                FROM("(" + getIncomeCategory(rCode, startTime, endTime, "weixin") + ") t1");
                LEFT_OUTER_JOIN("(" + getIncomeCategory(rCode, startTime, endTime, "alipay") + ") t2 ON t1.rname = t2.rname");
            }}.toString();
        }

        public String getIncomeCategory(@Param("rcode") String rCode, @Param("starttime") String startTime, @Param("endtime") String endTime, @Param("paytype") String payType) {
            return new SQL() {{
                SELECT("rname,sum(rs_bill.amount) as amount");
                FROM("rs_bill");
                INNER_JOIN("payment_order on rs_bill.bcode=payment_order.orderid");
                INNER_JOIN("rs_restaurant on rs_restaurant.rcode=rs_bill.rcode");
                WHERE("rs_bill.rcode = #{rcode}");
                WHERE("notify = '1'");
                WHERE("rs_bill.ostatus IN ('1', '5')");
                WHERE("rs_bill.createtime BETWEEN #{starttime} AND #{endtime}");
                WHERE("payment_order.paytype = '" + payType + "'");
                GROUP_BY("rname");
            }}.toString();
        }
    }
}
