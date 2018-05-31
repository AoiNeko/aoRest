package com.aoineko.common;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * Created by aoineko on 2018/5/25.
 */
@Configuration
//@AutoConfigureAfter(CommonConfig.class)
public class MyBatisConfig {

    @Bean
    public MapperScannerConfigurer mybatisConfig() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.aoineko.dao.mapper");
        Properties properties = new Properties();
        properties.setProperty("mappers", "tk.mybatis.mapper.common.Mapper");
        configurer.setProperties(properties);
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBeanName");
        return configurer;
    }

}
