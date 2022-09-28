package com.permisitelu.api.config;

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
public class SwaggerConfiguration {
    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder()
                .title("API Services")
                .description("PERMISI.TEL-U RESTful API")
                .version("v1.0")
                .contact(new Contact("Muhammad Rafly Rafsanjani", "https://mrr.works", "muh.raflyrafsanjani@gmail.com"))
                .build();
    }

    @Bean
    public Docket swaggerDocs() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(swaggerInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
