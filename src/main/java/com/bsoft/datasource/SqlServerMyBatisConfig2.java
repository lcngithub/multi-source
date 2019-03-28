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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

// basePackages 最好分开配置 如果放在同一个文件夹可能会报错
@Configuration
@MapperScan(basePackages = "com.bsoft.sqlServer2", sqlSessionTemplateRef = "test3SqlSessionTemplate")
public class SqlServerMyBatisConfig2 {

    // 配置数据源
    @Bean(name = "test3DataSource")
    @ConfigurationProperties(prefix = "sqlServer.datasource2")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "test3TransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("test3DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);

    }

    @Bean(name = "test3SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("test3DataSource") DataSource dataSource)
            throws Exception {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        try {
            bean.setMapperLocations(resolver.getResources("classpath:mybatis/sqlServermapper2/*.xml"));
            return bean.getObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "test3SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("test3SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
