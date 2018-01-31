/**
 * @author LYU
 * @create 2018年01月11日 15:58
 * @Copyright(C) 2010 - 2018 GBSZ
 * All rights reserved
 */

package com.wtown.util.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.wtown.util.convert.json.JsonDataUtil;
import com.wtown.util.convert.json.impl.JsonDataUtilImpl;
import com.wtown.util.convert.xml.XmlDataUtil;
import com.wtown.util.convert.xml.impl.XmlDataUtilImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class AppConfiguration {
    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.druid.one")
    public DataSource dataSourceOne() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSourceOne());
        return bean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public JsonDataUtil jsonDataUtil() {
        return new JsonDataUtilImpl();
    }

    @Bean
    public XmlDataUtil xmlDataUtil() {
        return new XmlDataUtilImpl();
    }
}
