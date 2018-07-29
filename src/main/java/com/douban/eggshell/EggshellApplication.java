package com.douban.eggshell;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
//扫描mapper 所有
@MapperScan("com.douban.eggshell.mapper") //与dao层的@Mapper二选一写上即可(主要作用是扫包)

@ServletComponentScan   //为了打成war包 新增的注解
/*public class EggshellApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(EggshellApplication.class, args);
	}
}*/

public class EggshellApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EggshellApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(EggshellApplication.class, args);
	}
}
