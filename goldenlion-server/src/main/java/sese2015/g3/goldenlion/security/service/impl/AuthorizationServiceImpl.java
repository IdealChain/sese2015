package sese2015.g3.goldenlion.security.service.impl;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sese2015.g3.goldenlion.security.config.SaltedSHA256PasswordEncoder;
import sese2015.g3.goldenlion.security.domain.Role;
import sese2015.g3.goldenlion.security.domain.User;
import sese2015.g3.goldenlion.security.domain.UserRole;
import sese2015.g3.goldenlion.security.repository.UserRepository;
import sese2015.g3.goldenlion.security.rest.domain.request.RegistrationRequest;
import sese2015.g3.goldenlion.security.service.AuthorizationService;
import sese2015.g3.goldenlion.security.utils.RandomString;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    private Log log = LogFactory.getLog(getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SaltedSHA256PasswordEncoder saltedSHA256PasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        this.log.info("Try to find the user with username: " + username);
        User user = userRepository.findByEmail(username);
        if (user == null || user.getAuthorities().size() == 0) {
            this.log.info("Couldn't find the user with username: " + username);
            throw new UsernameNotFoundException("User not found");
        }
        this.log.info("User with username: " + username + " found");
        return user;
    }

    @Override
    public String createNewUser(RegistrationRequest toRegister) {
        if (userRepository.findByEmail(toRegister.getEmail()) != null) {
            throw new IllegalStateException("Email-adress allready registered!");
        }
        User newUser = new User();
        newUser.setFirstName(toRegister.getFirstname());
        newUser.setLastName(toRegister.getLastname());
        newUser.setEmail(toRegister.getEmail());
        this.log.info("New user created: " + ToStringBuilder.reflectionToString(newUser, ToStringStyle.JSON_STYLE));

        List<UserRole> userRoleList = new ArrayList<>();
        UserRole standardRole = new UserRole();
        userRoleList.add(standardRole);
        newUser.setUserRoles(userRoleList);
        standardRole.setUser(newUser);
        standardRole.setRole(Role.ADM);

        RandomString randomString = new RandomString(15);
        String password = randomString.nextString();
        this.log.info("Generated password: " + password);

        String encPassword = saltedSHA256PasswordEncoder.encode(password);
        newUser.setPassword(encPassword);

        userRepository.save(newUser);
        return password;
    }

    @PostConstruct
    private void initStandardUser() {
        User standardUser = new User();
        standardUser.setFirstName("test");
        standardUser.setLastName("tester");
        standardUser.setEmail("tester@goldenlion.tk");

        List<UserRole> userRoleList = new ArrayList<>();
        UserRole standardRole = new UserRole();
        userRoleList.add(standardRole);
        standardUser.setUserRoles(userRoleList);
        standardRole.setUser(standardUser);
        standardRole.setRole(Role.ADM);

        standardUser.setPassword(saltedSHA256PasswordEncoder.encode("test"));
        userRepository.save(standardUser);
    }
}
