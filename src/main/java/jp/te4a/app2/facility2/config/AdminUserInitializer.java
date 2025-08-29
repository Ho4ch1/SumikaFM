package jp.te4a.app2.facility2.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import jp.te4a.app2.facility2.bean.UserBean;
import jp.te4a.app2.facility2.repository.UserRepository;

@Configuration
public class AdminUserInitializer {

    @Bean
    public CommandLineRunner initAdminUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            userRepository.findByUsername("admin").ifPresentOrElse(
                user -> {
                },
                () -> {
                    UserBean admin = new UserBean();
                    admin.setUsername("a");
                    admin.setPassword(passwordEncoder.encode("a")); // 初期パスワード
                    admin.setRole("ROLE_ADMIN");
                    userRepository.save(admin);
                }
            );
        };
    }
}
