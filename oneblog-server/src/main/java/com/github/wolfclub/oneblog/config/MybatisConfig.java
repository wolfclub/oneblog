package com.github.wolfclub.oneblog.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
@Configuration
@EnableTransactionManagement
//@MapperScan(basePackages = {"com.github.wolfclub.**.mapper"})
@AutoConfigureAfter(DataSourceConfig.class)
public class MybatisConfig {

	@Bean(name = "sqlSessionFactory")
	@ConditionalOnMissingBean
	public SqlSessionFactory mybatisSqlSessionFactory(@Qualifier("dataSource") DataSource datasource) throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(datasource);
		sessionFactoryBean.setTypeAliasesPackage("com.github.wolfclub.oneblog.po");

		sessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor()});

		sessionFactoryBean
						.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/*Mapper.xml"));
		return sessionFactoryBean.getObject();
	}

	/**
	 * 分页插件
	 * @return
	 */
	public Interceptor pageInterceptor(){
		//分页插件
		PageInterceptor pageInterceptor = new PageInterceptor();
		Properties properties = new Properties();
		//分页合理化参数 为true时超出范围的页号以边界查询
		properties.setProperty("reasonable", "true");
		//支持通过 Mapper 接口参数来传递分页参数
		properties.setProperty("supportMethodsArguments", "true");
		//当该参数设置为 true 时，如果 pageSize=0 或者 RowBounds.limit = 0 就会查询出全部的结果
		properties.setProperty("pageSizeZero", "true");

		// pagehelper5 文档中没有看到此参数
		// properties.setProperty("returnPageInfo", "check");
		// params默认值 pageNum=pageNum;pageSize=pageSize;count=countSql;reasonable=reasonable;pageSizeZero=pageSizeZer
		properties.setProperty("params", "count=countSql");
		pageInterceptor.setProperties(properties);
		return pageInterceptor;
	}


	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sessionFactory) {
		return new SqlSessionTemplate(sessionFactory);
	}

}
