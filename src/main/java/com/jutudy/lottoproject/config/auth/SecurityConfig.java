package com.jutudy.lottoproject.config.auth;

import com.jutudy.lottoproject.config.auth.service.CustomOAuth2UserService;
import com.jutudy.lottoproject.user.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/assets/**", "/images/**", "/h2-console/**", "/profile").permitAll()
                    .antMatchers("/userlotto/**").hasRole(Role.GUEST.name())
                    .antMatchers("/userlottos/**").hasRole(Role.GUEST.name())
                    .antMatchers("/random-lotto/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService);
    }
}
