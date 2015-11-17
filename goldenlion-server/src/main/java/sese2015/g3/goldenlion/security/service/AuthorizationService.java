package sese2015.g3.goldenlion.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import sese2015.g3.goldenlion.security.rest.domain.request.RegistrationRequest;

public interface AuthorizationService extends UserDetailsService {
    String createNewUser(RegistrationRequest toRegister);
}
