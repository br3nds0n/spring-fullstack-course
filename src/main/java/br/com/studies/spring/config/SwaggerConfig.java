package br.com.studies.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage("br.com.studies.spring"))
        .paths(PathSelectors.regex("/api.*"))
        .build().apiInfo(apiInfoMetaData());
  }

  private ApiInfo apiInfoMetaData() {

    return new ApiInfoBuilder().title("📦 curso fullstack")
        .description("documentação da api rest de estoque.")
        .contact(new Contact("Brendson", "https://www.linkedin.com/in/brendson/", "brendson.dev@gmail.com"))
        .license("MIT license")
        .licenseUrl("https://opensource.org/licenses/MIT")
        .version("1.0.0")
        .build();
  }
}
