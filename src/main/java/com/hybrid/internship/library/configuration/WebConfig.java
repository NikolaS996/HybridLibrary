package com.hybrid.internship.library.configuration;

import com.hybrid.internship.library.converter.BookConverter;
import com.hybrid.internship.library.converter.BookCopyConverter;
import com.hybrid.internship.library.converter.BookRentalConverter;
import com.hybrid.internship.library.converter.UserConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addFormatters(FormatterRegistry registry){
        registry.addConverter(new UserConverter());
        registry.addConverter(new BookConverter());
        registry.addConverter(new BookCopyConverter());
        registry.addConverter(new BookRentalConverter());
    }
}
