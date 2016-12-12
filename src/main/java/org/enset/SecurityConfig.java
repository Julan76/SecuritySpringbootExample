package org.enset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void globalConfig(AuthenticationManagerBuilder auth,DataSource dataSource) throws Exception {
        /*
        auth.inMemoryAuthentication().withUser("admin").password("123").roles("ADMIN","PROF");
        auth.inMemoryAuthentication().withUser("prof1").password("123").roles("PROF");
        auth.inMemoryAuthentication().withUser("et1").password("123").roles("ETUDIANT");
        auth.inMemoryAuthentication().withUser("sco1").password("123").roles("SCOLARITE");
*/
       auth.jdbcAuthentication()
               .dataSource(dataSource)
                .usersByUsernameQuery("Select username as principal, password as credentials, true from " +
                        " users where username= ?")
                .authoritiesByUsernameQuery("select user_username as principal, roles_role as role from " +
                        "users_roles where user_username=?")
                .rolePrefix("ROLE_");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
               .antMatchers("/css/**","/js/**","/images/**").permitAll()
                    .anyRequest()
                        .authenticated()
                            .and()
                .formLogin()
                    .loginPage("/login")
                        .permitAll()
                .defaultSuccessUrl("/index.html");


    }
}
