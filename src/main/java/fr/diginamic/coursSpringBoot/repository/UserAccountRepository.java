package fr.diginamic.coursSpringBoot.repository;

import fr.diginamic.coursSpringBoot.bo.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    public UserAccount findByUsername(String code);
}
