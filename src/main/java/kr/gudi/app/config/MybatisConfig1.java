package kr.gudi.app.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@MapperScan(value = "kr.gudi.app.db1.mapper", sqlSessionFactoryRef = "db1SqlSessionFactory")
public class MybatisConfig1 {
	
	@Bean(name = "db1DataSource")
	@Primary
	@ConfigurationProperties(prefix="spring.db1.datasource")
	public DataSource db1DataSource() {
		return DataSourceBuilder.create().build();
	}
	
	
	@Bean(name = "db1SqlSessionFactory")
	@Primary
	public SqlSessionFactory db1SqlSessionFactory(@Qualifier("db1DataSource")DataSource db1DataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(db1DataSource);
//		sessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/sql/*.xml"));
		return sessionFactoryBean.getObject();
	}
	
	
	
//	@Bean
//	public SqlSession sqlSession(SqlSessionFactory sqlSessionFactory) throws Exception {		
//		return new SqlSessionTemplate(sqlSessionFactory);
//	}
}
