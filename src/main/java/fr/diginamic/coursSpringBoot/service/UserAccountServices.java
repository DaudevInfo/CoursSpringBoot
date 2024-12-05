package fr.diginamic.coursSpringBoot.service;

import fr.diginamic.coursSpringBoot.bo.UserAccount;
import fr.diginamic.coursSpringBoot.repository.UserAccountRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServices {

    @Autowired
    UserAccountRepository userAccountRepository;

    @PostConstruct
    public void init() {
        UserAccount userBasic = new UserAccount("user", "pwd", "ROLE_USER");
        UserAccount userAdmin = new UserAccount("admin", "pwd", "ROLE_ADMIN");
        userAccountRepository.save(userBasic);
        userAccountRepository.save(userAdmin);
    }

}
