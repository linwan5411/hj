package cn.jeefast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = "cn.jeefast")
@SpringBootApplication
public class JeeFastSystemApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(JeeFastSystemApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(JeeFastSystemApplication.class);
	}
}
