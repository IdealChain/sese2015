package sese2015.g3.goldenlion.security.rest.resource;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sese2015.g3.goldenlion.security.rest.domain.request.LoginRequest;
import sese2015.g3.goldenlion.security.rest.domain.request.RegistrationRequest;
import sese2015.g3.goldenlion.security.rest.domain.response.LoginResponse;
import sese2015.g3.goldenlion.security.rest.domain.response.RegistrationResponse;
import sese2015.g3.goldenlion.security.service.AuthorizationService;
import sese2015.g3.goldenlion.security.utils.TokenUtils;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class AuthorizationResource {
    private Log log = LogFactory.getLog(getClass());

    @Autowired
    private UserDetailsService userService;
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authManager;


    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public LoginResponse login(@RequestBody LoginRequest toAuthenticate) {
        this.log.info("User " + toAuthenticate.getUsername() + " hits the Backend!");
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(toAuthenticate.getUsername(), toAuthenticate.getPassword());
        Authentication authentication = this.authManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        this.log.info("Authentication successful!");

        Map<String, Boolean> roles = new HashMap<String, Boolean>();
        UserDetails userDetails = this.userService.loadUserByUsername(toAuthenticate.getUsername());
        if (userDetails.getAuthorities() != null) {
            for (GrantedAuthority authority : userDetails.getAuthorities()) {
                roles.put(authority.toString(), Boolean.TRUE);
                this.log.info("Added role! " + authority.toString() + " to user " + userDetails.getUsername());
            }
        }
        return new LoginResponse(TokenUtils.createToken(userDetails, roles));
    }

    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RegistrationResponse register(@RequestBody RegistrationRequest toRegister) {
        this.log.info("Someone wants to register! " + ToStringBuilder.reflectionToString(toRegister, ToStringStyle.JSON_STYLE));
        String generatedPassword = this.authorizationService.createNewUser(toRegister);
        return new RegistrationResponse(generatedPassword);
    }
}

