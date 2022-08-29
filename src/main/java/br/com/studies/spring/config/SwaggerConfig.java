package br.com.studies.spring.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .securityContexts(Arrays.asList(securityContext()))
        .securitySchemes(Arrays.asList(apiKey())).select()
        .apis(RequestHandlerSelectors.basePackage("br.com.studies.spring"))
        .paths(PathSelectors.regex("/api.*"))
        .build().apiInfo(apiInfoMetaData());
  }

  private ApiKey apiKey() {
    return new ApiKey("JWT", "Authorization", "header");
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder().securityReferences(defaultAuth()).build();
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
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
