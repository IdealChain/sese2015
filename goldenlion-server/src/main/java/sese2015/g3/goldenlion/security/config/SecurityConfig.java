package sese2015.g3.goldenlion.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sese2015.g3.goldenlion.security.service.AuthorizationService;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private static final int HTTPS_PORT = 9090;

    @Autowired
    private UnauthorizedEntryPoint unauthorizedEntryPoint;
    @Autowired
    private AuthorizationService authorizationService;
//    @Autowired
//    private AuthenticationTokenProcessingFilter authenticationTokenProcessingFilter;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, SaltedSHA256PasswordEncoder passwordEncoder, AuthorizationService authorizationServiceImpl) throws Exception {
        auth.userDetailsService(authorizationServiceImpl).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAfter(new AuthenticationTokenProcessingFilter(authorizationService), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/api/register").permitAll()
                .antMatchers("/api/reservation").authenticated()
                .antMatchers("/api/invoice").authenticated()
                .antMatchers("/api/room").authenticated()
                .antMatchers("/api/protocol").authenticated()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/assets/images/**").permitAll()
                .antMatchers("/maps/**").permitAll()
                .antMatchers("/fonts/**").permitAll()
                .antMatchers("/scripts/**").permitAll()
                .antMatchers("/styles/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.GET, "/**").authenticated()
                .and().exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint);
    }

    // Secure Tomcat Connection is disabled because of the certificate. A signed certificate should
    // be used!
    //@Override
    //public void customize(ConfigurableEmbeddedServletContainer factory) {
    //    if(factory instanceof TomcatEmbeddedServletContainerFactory) {
    //        customizeTomcat((TomcatEmbeddedServletContainerFactory) factory);
    //    }
    //}

//    public void customizeTomcat(TomcatEmbeddedServletContainerFactory factory) {
//        factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
//            @Override
//            public void customize(Connector connector) {
//                connector.setPort(HTTPS_PORT);
//                connector.setSecure(true);
//                connector.setScheme("https");
//                connector.setAttribute("keyAlias", "tomcat");
//                connector.setAttribute("keystorePass", "password");
//                try {
//                    connector.setAttribute("keystoreFile", ResourceUtils
//                            .getFile(getClass().getClassLoader().getResource("security/.keystore"))
//                            .getAbsolutePath());
//                } catch (FileNotFoundException e) {
//                    throw new IllegalStateException("Cannot load keystore", e);
//                }
//                connector.setAttribute("clientAuth", "false");
//                connector.setAttribute("sslProtocol", "TLS");
//                connector.setAttribute("SSLEnabled", true);
//            }
//        });
//    }
}
