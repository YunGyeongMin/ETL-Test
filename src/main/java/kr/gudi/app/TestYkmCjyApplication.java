package kr.gudi.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import kr.gudi.app.db1.mapper.ETLMapper1;

@SpringBootApplication
public class TestYkmCjyApplication {
	
	@Autowired private ETLMapper1 etl1;
	@Autowired private ETLMapper1 etl2;
	
	public static void main(String[] args) {
		SpringApplication.run(TestYkmCjyApplication.class, args);
	}

}
