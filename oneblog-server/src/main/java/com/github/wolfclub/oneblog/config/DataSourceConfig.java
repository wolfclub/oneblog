package com.github.wolfclub.oneblog.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
@Configuration
public class DataSourceConfig {
  public Logger logger = LoggerFactory.getLogger(this.getClass());

  @Value("${spring.datasource.url}")
  private String url;
  @Value("${spring.datasource.username}")
  private String username;
  @Value("${spring.datasource.password}")
  private String password;
  @Value("${spring.datasource.driver-class-name}")
  private String driverClass;

  @Value("${spring.datasource.initialSize}")
  private int initialSize;
  @Value("${spring.datasource.minIdle}")
  private int minIdle;
  @Value("${spring.datasource.maxActive}")
  private int maxActive;
  @Value("${spring.datasource.maxWait}")
  private int maxWait;
  @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
  private int timeBetweenEvictionRunsMillis;
  @Value("${spring.datasource.minEvictableIdleTimeMillis}")
  private int minEvictableIdleTimeMillis;
  @Value("${spring.datasource.validationQuery}")
  private String validationQuery;
  @Value("${spring.datasource.testWhileIdle}")
  private boolean testWhileIdle;
  @Value("${spring.datasource.testOnBorrow}")
  private boolean testOnBorrow;
  @Value("${spring.datasource.testOnReturn}")
  private boolean testOnReturn;
  @Value("${spring.datasource.poolPreparedStatements}")
  private boolean poolPreparedStatements;
  @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
  private int maxPoolPreparedStatementPerConnectionSize;
  @Value("${spring.datasource.filters}")
  private String filters;
  @Value("{spring.datasource.connectionProperties}")
  private String connectionProperties;

  @Bean(value = "dataSource")
  public DataSource dataSource() {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setDriverClassName(driverClass);
    dataSource.setUrl(url);
    dataSource.setUsername(username);
    dataSource.setPassword(password);

    // configuration
    dataSource.setInitialSize(initialSize);
    dataSource.setMinIdle(minIdle);
    dataSource.setMaxActive(maxActive);
    dataSource.setMaxWait(maxWait);
    dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
    dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
    dataSource.setValidationQuery(validationQuery);
    dataSource.setTestWhileIdle(testWhileIdle);
    dataSource.setTestOnBorrow(testOnBorrow);
    dataSource.setTestOnReturn(testOnReturn);
    dataSource.setPoolPreparedStatements(poolPreparedStatements);
    dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
    try {
      dataSource.setFilters(filters);
    } catch (SQLException e) {
      logger.error("druid configuration initialization filter", e);
    }
    dataSource.setConnectionProperties(connectionProperties);
    return dataSource;
  }

  @Bean
  public DataSourceTransactionManager transactionManager() {
    return new DataSourceTransactionManager(dataSource());
  }

}
