package com.aoineko.common;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.jdbc.Driver;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by com.aoineko on 2017/7/16.
 */
@Configuration
public class CommonConfig {

    @Value("${mysql.url}")
    private String url;
    @Value("${mysql.username}")
    private String username;
    @Value("${mysql.password}")
    private String password;
//    @Value("${mysql.driver-class-name}")
//    private String driverClassName;
    @Value("${mysql.maxActive}")
    private Integer maxActive;
    @Value("${mysql.initialSize}")
    private Integer initialSize;
    @Value("${mysql.maxWait}")
    private Long maxWait;
    @Value("${mysql.minIdle}")
    private Integer minIdle;
    @Value("${mysql.timeBetweenEvictionRunsMillis}")
    private Long timeBetweenEvictionRunsMillis;
    @Value("${mysql.minEvictableIdleTimeMillis}")
    private Long minEvictableIdleTimeMillis;
    @Value("${mysql.testWhileIdle}")
    private Boolean testWhileIdle=false;
    @Value("${mysql.testOnBorrow}")
    private Boolean testOnBorrow=false;
    @Value("${mysql.testOnReturn}")
    private Boolean testOnReturn=true;
    @Value("${mysql.validationQuery}")
    private String validationQuery;


    @Bean(name = "dataSource", destroyMethod = "close")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxActive(maxActive);
        dataSource.setMinIdle(minIdle);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setValidationQuery(validationQuery);
        return dataSource;
    }


    @Bean(name = "sqlSessionFactoryBeanName")
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("dataSource") DataSource ds) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(ds);
        return sqlSessionFactoryBean;
    }



    @Bean(name = "transactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dataSource") DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }


}
