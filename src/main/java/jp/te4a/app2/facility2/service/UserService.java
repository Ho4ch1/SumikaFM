package jp.te4a.app2.facility2.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jp.te4a.app2.facility2.bean.FacilityBean;
import jp.te4a.app2.facility2.bean.UserBean;
import jp.te4a.app2.facility2.form.FacilityForm;
import jp.te4a.app2.facility2.form.UserForm;
import jp.te4a.app2.facility2.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserForm create(UserForm userForm) {
        //facilityForm.setId(facilityRepository.getFacilityId());
        UserBean userBean = new UserBean();
        BeanUtils.copyProperties(userForm, userBean);
        userRepository.save(userBean);
        return userForm;
    }

    public UserForm save(UserForm userForm) {
        UserBean userBean = new UserBean();
        BeanUtils.copyProperties(userForm, userBean);
        userRepository.save(userBean);
        return userForm;
       }
       

    //更新処理
    public UserForm update(UserForm userForm) {
        UserBean userBean = new UserBean();
        BeanUtils.copyProperties(userForm, userBean);
        userRepository.save(userBean);
        return userForm;
    }

    //削除処理
    public void delete(Integer id) { 
        userRepository.deleteById(id); 
    }

    //全件取得処理
    public List<UserForm> findAll() {
        List<UserBean> beanList = userRepository.findAll();
        List<UserForm> formList = new ArrayList<>();
        for (UserBean userBean : beanList) {
            UserForm userForm = new userForm();
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
     }
    public List<Integer> getAllIds() {
        return userRepository.findAllIds();
    }
/*
    public List<String> getAllProducts() {
        return userRepository.findAllProducts();
    }

    public List<String> getAllManufacturers() {
        return userRepository.findAllManufacturers();
    }

    public List<String> getAllLocations() {
        return userRepository.findAllLocations();
    }

    public List<Integer> getAllServiceLifes() {
        return userRepository.findAllServiceLifes();
    }
*/

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
