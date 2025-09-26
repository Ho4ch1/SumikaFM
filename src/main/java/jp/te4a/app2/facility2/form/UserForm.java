package jp.te4a.app2.facility2.form;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class UserForm {
    private Integer id; 
    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 6, message = "パスワードは6文字以上です")
    private String password;

    @NotBlank
    private String role; // ここを追加
}
