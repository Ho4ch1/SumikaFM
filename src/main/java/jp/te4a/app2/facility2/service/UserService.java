package jp.te4a.app2.facility2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
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

    //削除処理
    public void delete(Integer id) { 
        userRepository.deleteById(id); 
    }

    public UserBean findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

        //全件取得処理
    public List<UserForm> findAll() {
        List<UserBean> beanList = userRepository.findAll();
        List<UserForm> formList = new ArrayList<>();
        for (UserBean userBean : beanList) {
            UserForm userForm = new UserForm();
            BeanUtils.copyProperties(userBean, userForm);
            formList.add(userForm);
        }
        return formList;
    }

    //１件取得処理
    public UserForm findOne(Integer id) {
        Optional<UserBean> opt = userRepository.findById(id);
        UserBean user = opt.orElseThrow();
        UserForm userForm = new UserForm();
        BeanUtils.copyProperties(user, userForm);
        return userForm;
    }

    public List<UserBean> search(Integer id, String username, String role) {
        List<UserBean> result = userRepository.findAll(); // 全件取得からフィルター
    
        return result.stream()
            .filter(bean -> id == null || bean.getId().equals(id))
            .filter(bean -> username == null || bean.getUsername().contains(username))
            .filter(bean -> role == null || bean.getRole().contains(role))
            .toList();
    }

    public List<Integer> getAllIds() {
        return userRepository.findAllIds();
    }

    public List<String> getAllUsernames() {
        return userRepository.findAllUsernames();
    }

    public List<String> getAllRoles() {
        return userRepository.findAllRoles();
    }

}
