package com.neo.shared.config;

import com.neo.shared.handler.CustomAuthSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.net.URI;

/**
 * Class that handles basic security configurations.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${client.post-logout-uri}")
    private String postLogoutUri;

    @Autowired
    private CustomAuthSuccessHandler customAuthSuccessHandler;

    /**
     * Configure the spring-security with default login page as /login and logout url as '/applogout'. All the other
     * endpoints of the application are secured.
     *
     * @param http
     * @throws Exception
     */
    protected void configure(HttpSecurity http) throws Exception {

        http.headers()
                .frameOptions()
                .disable()
                .and()
                .authorizeRequests()
                .antMatchers("/css/*", "/images/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login()
                .loginPage("/oauth2/authorization/wso2").successHandler(customAuthSuccessHandler)
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessHandler(oidcLogoutSuccessHandler());
    }

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    /**
     * Handles OIDC Logout redirection.
     *
     * @return LogoutSuccessHandler
     */
    private LogoutSuccessHandler oidcLogoutSuccessHandler() {

        OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler = new OidcClientInitiatedLogoutSuccessHandler(
                this.clientRegistrationRepository);
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri(URI.create(postLogoutUri));
        return oidcLogoutSuccessHandler;
    }
}
