package com.douban.eggshell;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//扫描mapper 所有
@MapperScan("com.douban.eggshell.mapper") //与dao层的@Mapper二选一写上即可(主要作用是扫包)
public class EggshellApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(EggshellApplication.class, args);
	}
}
