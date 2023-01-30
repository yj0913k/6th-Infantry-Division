package com.green.nowon.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
//@MapperScan("com.green.nowon.mapper")
public class MybatisConfig {

    public MybatisConfig() {
        System.out.println("MybatisConfig 실행: Bean으로 등록");
    }
    @Bean
    SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        System.out.println("dataSource:"+dataSource);
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource( dataSource );
        factoryBean.setConfiguration( mybatisConfiguration() );

        //mapper.xml 사용시 설정
        Resource[] mapperLocations=	new PathMatchingResourcePatternResolver()
                .getResources("classpath:/sqlmap/**/*-mapper.xml");
        factoryBean.setMapperLocations(mapperLocations);

        return factoryBean.getObject();
    }

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    org.apache.ibatis.session.Configuration mybatisConfiguration() {
        return new org.apache.ibatis.session.Configuration();
    }



    //SqlSessionTemplate is the heart of MyBatis-Spring
    @Bean
    SqlSessionTemplate sqlSessionTemplate(DataSource dataSource) throws Exception {
        return new SqlSessionTemplate( sqlSessionFactory(dataSource) );
    }

}