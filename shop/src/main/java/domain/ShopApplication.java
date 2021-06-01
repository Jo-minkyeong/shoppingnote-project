package domain;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}
	
	//mybatis 초기화
//	@Bean
//	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
//		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//		sessionFactory.setDataSource(dataSource);
//		
//		Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:*Mapper.xml");
//		sessionFactory.setMapperLocations(res);
//		
//		return sessionFactory.getObject();
//	}

}
