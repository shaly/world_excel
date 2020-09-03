package com.example.config;

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

/**
 * 基于Swagger生成API文档
 *
 * @author XuQing
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
//
//    @Value("${swagger.host}")
//    private String swaggerHost;

//    @Bean
//    public Docket createRestApi() {
//        List<Parameter> pars = new ArrayList<Parameter>();
//        return new Docket(DocumentationType.SWAGGER_2)
//                .host(swaggerHost)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.smcv.xyx.asc.controller"))
//                .build()
//                .globalOperationParameters(pars)
//                .apiInfo(apiInfo());
//    }
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo1());
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("[ecm-synchronize-service]-[定时任务]")
                .description("XXL-JOB 同步定时任务")
                .version("0.0.1")
                .build();
    }


    private ApiInfo apiInfo1(){
        return new ApiInfoBuilder()
                .title("[ecm-synchronize-service]-[执行器-用户认证同步]")
                .description("旭辉永升服务")
                .version("0.0.1")
                .contact(new Contact("xxl-Job","https://ecmmall.ysservice.com.cn/","xxx@xx.com"))
//                .license("The Apache License")
                .licenseUrl("https://ecmmall.ysservice.com.cn/")
                .build();
    }
}