package com.business.account.Repository;

import com.business.account.Entity.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<UserRegistration, Long> {
}
