package jp.te4a.app2.facility2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import jp.te4a.app2.facility2.bean.UserBean;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserBean, Integer> {
    Optional<UserBean> findByUsername(String username);
}

