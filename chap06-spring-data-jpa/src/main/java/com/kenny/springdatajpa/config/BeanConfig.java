package com.kenny.springdatajpa.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// modelmapper 사용 위해 bean 등록, 의존성 주입(menu service에서)
@Configuration
public class BeanConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        /* setter 메소드 미사용 시 ModelMapper가 private 필드에 접근 가능하도록 하는 설정 */
        // setter 메소드가 아닌 필드를 통한 접근을 modelMapper 에게 허용함

        modelMapper.getConfiguration()
                .setFieldAccessLevel(
                        org.modelmapper.config.Configuration.AccessLevel.PRIVATE
                )
                .setFieldMatchingEnabled(true);
        return modelMapper;
    }
}

