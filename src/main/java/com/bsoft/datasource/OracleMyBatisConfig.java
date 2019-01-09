package com.bsoft.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@MapperScan(basePackages = "com.bsoft.oracle", sqlSessionTemplateRef = "oracleSqlSessionTemplate")
public class OracleMyBatisConfig {

	@Bean(name = "oracleDataSource")
	@Qualifier("oracleDataSource")
	@ConfigurationProperties(prefix = "oracle.datasource")
	public DataSource oracleDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "oracleJdbcTemplate")
	public JdbcTemplate oracleJdbcTemplate(@Qualifier("oracleDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Qualifier("oracleTransactionManager")
	@Bean(name = "oracleTransactionManager")
	public DataSourceTransactionManager oracleTransactionManager(@Qualifier("oracleDataSource") DataSource oracleDataSource) {
		DataSourceTransactionManager oracleTransactionManager = new DataSourceTransactionManager();
		oracleTransactionManager.setDataSource(oracleDataSource);
		return oracleTransactionManager;
	}

	@Bean(name = "oracleSqlSessionFactory")
	@Qualifier("oracleSqlSessionFactory")
	public SqlSessionFactory oracleSqlSessionFactory(@Qualifier("oracleDataSource") DataSource oracleDataSource) throws Exception {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		SqlSessionFactoryBean oracleSqlSessionFactory = new SqlSessionFactoryBean();
		oracleSqlSessionFactory.setDataSource(oracleDataSource);
		try {
			oracleSqlSessionFactory.setMapperLocations(resolver.getResources("classpath:mybatis/oraclemapper/*.xml"));
			return oracleSqlSessionFactory.getObject();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Bean(name = "oracleSqlSessionTemplate")
	public SqlSessionTemplate oracleSqlSessionTemplate(@Qualifier("oracleSqlSessionFactory") SqlSessionFactory oracleSqlSessionFactory) {
		return new SqlSessionTemplate(oracleSqlSessionFactory);
	}
}
