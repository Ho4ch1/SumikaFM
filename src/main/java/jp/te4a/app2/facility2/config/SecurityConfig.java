package jp.te4a.app2.facility2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jp.te4a.app2.facility2.service.LoginUserDetailsService;
import jp.te4a.app2.facility2.security.CustomLoginSuccessHandler;

@Configuration
public class SecurityConfig {

    // PasswordEncoder はフィールド注入
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            LoginUserDetailsService loginUserDetailsService,
            CustomLoginSuccessHandler loginSuccessHandler
    ) throws Exception {

        // 認証マネージャを HttpSecurity 内で設定
        http.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(loginUserDetailsService)
            .passwordEncoder(passwordEncoder());

        // ログイン、ログアウト、認可設定
        http.formLogin(login -> login
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(loginSuccessHandler)
                .failureUrl("/login?error")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
        )
        .logout(logout -> logout
                .logoutSuccessUrl("/login")
        )
        .authorizeHttpRequests(authz -> authz
                .requestMatchers("/webjars/**", "/css/**", "/js/**", "/image/**").permitAll()
                .requestMatchers("/login", "/auth", "/auth/create").permitAll()
                .anyRequest().authenticated()
        );

        return http.build();
    }
}
