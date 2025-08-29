package jp.te4a.app2.facility2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jp.te4a.app2.facility2.bean.UserBean;
import jp.te4a.app2.facility2.form.UserForm;
import jp.te4a.app2.facility2.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void create(UserForm form) {
        //フォームから username を取り出す
        String username = form.getUsername();
        // ここで重複チェック
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("このユーザー名は既に使われています");
        }
        UserBean user = new UserBean();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setRole(form.getRole());
        userRepository.save(user);
    }

    public UserBean findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
