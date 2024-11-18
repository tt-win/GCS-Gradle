package console.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Hello請求物件")
public class HelloRequest {
    //@ApiModelProperty(value = "第一個參數", example = "testParam1", required = true, position = 1)
    //@Schema(description = "The email address of user.", example = "vincent@gmail.com")
    //@Schema(description = "第一個參數", example = "testParam1", required = true, minLength = 1, maxLength = 50)
    @Schema(
            title = "參數1",
            description = "第一個參數說明",
            example = "testParam1",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 1,
            maxLength = 50
    )
    private String param1;

    @Schema(
            title = "參數2",
            description = "第二個參數說明",
            example = "testParam2"
    )
    private String param2;
}
