package com.example.boottest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Bean
    public Docket createRestApi() {
        //Authorization
        ParameterBuilder pb = new ParameterBuilder();
        List<Parameter> token = new ArrayList<>();
        pb.name(tokenHeader).description("令牌").modelRef(new ModelRef("String"))
                .parameterType("header").required(false)
                .build();
        token.add(pb.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.boottest.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(token);
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("test-system")
                .description("powered by test-system, come from file description")
                .termsOfServiceUrl("http://127.0.0.1:8080")
                .version("1.0")
                .build();
    }

    private List<ApiKey> securitySchemes(){
        return new ArrayList(
                new ApiKey("Authorization", "Authorization", "header")
        );
    }
}