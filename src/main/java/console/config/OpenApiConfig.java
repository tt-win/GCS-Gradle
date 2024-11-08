package console.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // 基本資訊設定
                .info(new Info()
                              .title("GCS API 文檔")
                              .version("1.0")
                              .description("GCS API 文檔說明")
                              .termsOfService("http://swagger.io/terms/")
                              .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                              .contact(new Contact().name("API Support").email("support@example.com").url("http://example.com")))
                // 服務器配置
                .servers(Arrays.asList(
                        new Server().url("http://localhost:7001").description("開發環境"),
                        new Server().url("http://xx.xx.xx.xx:xxxx").description("SIT環境")))

                // 安全機制配置
                .security(Arrays.asList(
                        new SecurityRequirement().addList("bearerAuth")))
                .components(new Components()
                                    .addSecuritySchemes("bearerAuth",
                                                        new SecurityScheme()
                                                                .name("bearerAuth")
                                                                .type(SecurityScheme.Type.HTTP)
                                                                .scheme("bearer")
                                                                .bearerFormat("JWT")
                                                                .description("請輸入 JWT Token")))
                // 標籤配置（API 分組）
                .addTagsItem(new Tag()
                                     .name("哈囉管理")
                                     .description("哈囉相關 API"))
                .addTagsItem(new Tag()
                                     .name("遊戲管理")
                                     .description("遊戲相關 API"))
                // 外部文檔連結
                .externalDocs(new ExternalDocumentation()
                                      .description("API 使用指南")
                                      .url("http://docs.example.com"));
    }

    // 自定義 Docket 配置
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                             .group("public-api")
                             .pathsToMatch("/api/public/**")
                             .build();
    }

    @Bean
    public GroupedOpenApi helloApi() {
        return GroupedOpenApi.builder()
                             .group("hello-api")
                             .pathsToMatch("/hello*")  // 匹配所有以 hello 開頭的路徑
                             .build();
    }
}