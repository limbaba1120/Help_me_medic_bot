package com.capstonedesign.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  private static final String API_NAME = "help_me_medic_bot API";
  private static final String API_DESCRIPTION = "help_me_medic_bot Rest API 명세서";

  @Bean
  public ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title(API_NAME)
        .description(API_DESCRIPTION)
        .contact(new Contact(
            "[help_me_medic_bot]",
            "https://github.com/2023-KDH-Capstone-Design/help-me-medic-bot",
            "awfjol2008@gmail.com"
        ))
        .build();
  }

  @Bean
  public Docket swagger() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.capstonedesign.backend.controller"))
        .paths(PathSelectors.any())
        .build();
  }
}
