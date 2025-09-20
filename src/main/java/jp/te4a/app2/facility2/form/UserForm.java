package jp.te4a.app2.facility2.form;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class UserForm {
    private Integer id; 
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String role; // ここを追加
}
