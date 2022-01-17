package com.gl.mybatisapirest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public static final String USER = "app-user";
    private static final String USER_DESCRIPTION = "app-user";
    private static final String CONTACT_DESCRIPTION = "api rest with mybatis";
    private static final String CONTACT_TITLE = "USER Rest Api";
    private static final Contact CONTACT = new Contact("Brenda Sánchez", null, "brendasanchez9310@gmail.com");

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gl.mybatisapirest"))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag(USER, USER_DESCRIPTION));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(CONTACT_TITLE)
                .contact(CONTACT)
                .description(CONTACT_DESCRIPTION)
                .build();
    }
}
