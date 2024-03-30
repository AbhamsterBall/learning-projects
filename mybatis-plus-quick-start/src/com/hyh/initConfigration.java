package com.hyh;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class initConfigration {

    @Bean("empNo")
    public Integer getEmpNo() {
        return 1;
    }

}
