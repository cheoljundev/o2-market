package com.o2.site.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.context.annotation.Configuration;

@Configuration
// 여기에 추가
@MapperScans(value = {
        @MapperScan(basePackages = "com.o2.site.trade.dao", annotationClass = Mapper.class),
        @MapperScan(basePackages = "com.o2.site.half.dao", annotationClass = Mapper.class),
        @MapperScan(basePackages = "com.o2.site.club.dao", annotationClass = Mapper.class),
        @MapperScan(basePackages = "com.o2.site.member.dao", annotationClass = Mapper.class),
        @MapperScan(basePackages = "com.o2.site.upload.dao", annotationClass = Mapper.class)
})
public class MybatisConfig {
}
