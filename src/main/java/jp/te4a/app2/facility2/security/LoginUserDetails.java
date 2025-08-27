package jp.te4a.app2.facility2.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import jp.te4a.app2.facility2.bean.UserBean;
import lombok.Getter;

@Getter
public class LoginUserDetails extends User {

    private final UserBean user;

    public LoginUserDetails(UserBean userBean, Collection<? extends GrantedAuthority> authorities) {
        super(userBean.getUsername(), userBean.getPassword(), authorities);
        this.user = userBean;
    }
}
