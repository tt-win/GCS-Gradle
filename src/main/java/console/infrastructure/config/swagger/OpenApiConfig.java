package console.infrastructure.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                //.addTagsItem(new Tag().name("Path Search").description("API Path Search"))
                // 基本資訊設定
                .info(new Info().title("GCS API 文檔").version("1.0"))

                // 服務器配置
                //.servers(Arrays.asList(
                //        new Server().url("http://localhost:7001").url("/gcs-console").description("開發環境"),
                //        new Server().url("http://xx.xx.xx.xx:xxxx").url("/gcs-console") .description("SIT環境")))

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
                                     .name("測試管理")
                                     .description("測試相關 API"))
                .addTagsItem(new Tag()
                                     .name("錢包管理")
                                     .description("錢包相關 API"))
                .addTagsItem(new Tag()
                                     .name("遊戲管理")
                                     .description("遊戲相關 API"));
                // 外部文檔連結
                //.externalDocs(new ExternalDocumentation()
                //                      .description("API 使用指南")
                //                      .url("http://docs.example.com"));
    }

    /*@Bean
    public OperationCustomizer customizeOperation() {
        return (operation, handlerMethod) -> {
            // 獲取實際的 API 路徑
            String path = handlerMethod.getMethod()
                                       .getAnnotation(Operation.class)
                                       .summary();

            // 在描述中加入 API 路徑
            String currentDescription = operation.getDescription() != null ?
                    operation.getDescription() : "";
            operation.description("API Path: " + path + "\n" + currentDescription);

            return operation;
        };
    }*/

    // 自定義 Docket 配置
    /*@Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                             .group("public-api")
                             //.pathsToMatch("/api/public/**")
                             .pathsToMatch("/**")
                             .build();
    }*/

    /*@Bean
    public GroupedOpenApi helloApi() {
        return GroupedOpenApi.builder()
                             .group("hello-api")
                             .pathsToMatch("/hello*")  // 匹配所有以 hello 開頭的路徑
                             .build();
    }

    @Bean
    public GroupedOpenApi testApi() {
        return GroupedOpenApi.builder()
                             .group("test-api")
                             .pathsToMatch("/redis*")  // 匹配所有 /api 開頭的路徑
                             .build();
    }*/
}