package jp.te4a.app2.facility2.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Data
public class UserForm {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String role; // ここを追加
}
