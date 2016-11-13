package jwd.wafepa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import jwd.wafepa.support.TestData;

@SpringBootApplication
public class WafepaApplication 
	extends SpringBootServletInitializer {
	
	@SuppressWarnings("unused")
	@Autowired
	private TestData testData;

	public static void main(String[] args) {
		 SpringApplication.run(WafepaApplication.class, args);
	}

}
