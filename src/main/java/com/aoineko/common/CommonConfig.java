package com.aoineko.common;

import com.alibaba.druid.pool.DruidDataSource;
import com.aoineko.entity.User;
import com.aoineko.service.UserService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mysql.jdbc.Driver;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import sun.misc.BASE64Decoder;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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
    private Boolean testWhileIdle = false;
    @Value("${mysql.testOnBorrow}")
    private Boolean testOnBorrow = false;
    @Value("${mysql.testOnReturn}")
    private Boolean testOnReturn = true;
    @Value("${mysql.validationQuery}")
    private String validationQuery;

    @Value("${aoi.jwt.pkey}")
    private String jwtPublicKey;
    @Value("${aoi.jwt.privateKey}")
    private String jwtPrivateKey;


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

    @Bean(name = "userLoadingCache")
    @DependsOn({"userService"})
    public LoadingCache<String, User> loginUserJwtCache(@Autowired UserService userService) {

        return CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(15, TimeUnit.DAYS).build(new CacheLoader<String, User>() {

            @Override
            public User load(String key) throws Exception {
                Long userId = userService.getTokenUserId(key);
                if (userId == null) {
                    return null;
                }
                return userService.getUserById(userId);
            }
        });
    }

    @Bean
    public RSAPublicKey rsaPublicKey() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        X509EncodedKeySpec pKeySpec = new X509EncodedKeySpec(decoder.decodeBuffer(jwtPublicKey));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPublicKey rsaPublicKey = (RSAPublicKey) kf.generatePublic(pKeySpec);
        return rsaPublicKey;
    }

    @Bean
    public RSAPrivateKey rsaPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] privatekeyDecode = decoder.decodeBuffer(jwtPrivateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privatekeyDecode);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) kf.generatePrivate(keySpec);
        return rsaPrivateKey;
    }


}
