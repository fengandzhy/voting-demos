package org.frank.vote.config;

import com.sun.istack.NotNull;
import org.frank.vote.filters.CustomerLoginFilter;
import org.frank.vote.handlers.CustomerAuthenticationFailureHandler;
import org.frank.vote.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;
    private CustomerAuthenticationFailureHandler customerAuthenticationFailureHandler;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(@NotNull AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(@NotNull WebSecurity web){
        web.ignoring().antMatchers("/js/**", "/style/**","/img/**");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean()
            throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    CustomerLoginFilter loginFilter() throws Exception {
        CustomerLoginFilter loginFilter = new CustomerLoginFilter();
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        loginFilter.setFilterProcessesUrl("/doLogin");
        loginFilter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/vote.html"));
        loginFilter.setAuthenticationFailureHandler(new CustomerAuthenticationFailureHandler());        
        return loginFilter;
    }

    

    protected void configure(@NotNull HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/vc.jpg").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login.html")
//                .loginProcessingUrl("/doLogin")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/welcome")
//                .failureUrl("/fail")                
                .permitAll()
                .and().logout()
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST"))
                .deleteCookies()
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/login.html")
                .permitAll();
//                .and()
//                .csrf().disable();
        http.addFilterAt(loginFilter(),
                UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    public void setUserService(@NotNull UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCustomerAuthenticationFailureHandler(CustomerAuthenticationFailureHandler customerAuthenticationFailureHandler) {
        this.customerAuthenticationFailureHandler = customerAuthenticationFailureHandler;
    }
}
