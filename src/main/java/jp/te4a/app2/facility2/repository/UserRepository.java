package jp.te4a.app2.facility2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jp.te4a.app2.facility2.bean.UserBean;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserBean, Integer> {
    Optional<UserBean> findByUsername(String username);
    boolean existsByUsername(String username);

    @Query("SELECT DISTINCT f.id FROM UserBean f")
    List<Integer> findAllIds();

    @Query("SELECT DISTINCT f.username FROM UserBean f")
    List<String> findAllUsernames();

    @Query("SELECT DISTINCT f.role FROM UserBean f")
    List<String> findAllRoles();

    @Query("SELECT X FROM UserBean X ORDER BY X.username")
    List<UserBean> findAllOrderByTitle();
}

