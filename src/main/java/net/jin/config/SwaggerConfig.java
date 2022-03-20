/**
 * 
 */
package net.jin.config;

import org.springframework.context.annotation.*;

import io.swagger.annotations.*;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.*;
import springfox.documentation.spring.web.plugins.*;
import springfox.documentation.swagger2.annotations.*;

/**
 * @author njh
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("Jimmy API Gamma")
                .description("Jimmy's API Gamma List")
                .build();
    }

    //Bean 등록
    @Bean
    public Docket commonApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("API with Swagger")
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("net.jin.controller"))
                .paths(PathSelectors.ant("/**"))
                .build();
    }
}
