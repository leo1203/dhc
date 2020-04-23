package cn.com.dhc.epq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@MapperScan("cn.com.dhc.epq.mapper")
@SpringBootApplication
public class EpqApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(EpqApplication.class, args);
	}

	/**
	 * 跨域配置
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		 //前后端分离跨域
        registry.addMapping("/**")
                .allowedOrigins("*")  //配置IP地址
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
	}
	
	

}
