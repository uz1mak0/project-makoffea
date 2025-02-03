package com.business.account.Repository;

import com.business.account.Entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<UserLogin, Long> {
}
