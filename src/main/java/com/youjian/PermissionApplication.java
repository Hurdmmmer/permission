package com.youjian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shen youjian
 * @date 12/19/2018 9:36 PM
 */
@SpringBootApplication
@MapperScan("com.youjian.dao")
public class PermissionApplication {
    public static void main(String[] args) {
        SpringApplication.run(PermissionApplication.class, args);
    }
}
