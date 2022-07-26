package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;

@Configuration
@EnableSwagger2
@EnableWebSecurity
public class Config  extends WebSecurityConfigurerAdapter {
    
    public  static  String SQL_QUERY_FOR_USERS="select username , password, enabled  from users where username=?";
    public  static  String SQL_QUERY_FOR_AUTHORITIES="select username, authority from authorities where username=?";
    
    
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.example.controller"))
                .paths(PathSelectors.ant("/**"))
                .build();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


                                                  /*--------with hardcoded users-----------*/
    /*@Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            InMemoryUserDetailsManager userDetailsManager=new InMemoryUserDetailsManager();
            UserDetails  user1= User.withUsername("ankan").password("ankan").authorities("ADMIN").build();
            userDetailsManager.createUser(user1);
            auth.userDetailsService(userDetailsManager);
        }*/
    @Autowired
    DataSource dataSource;
    
                                 

              /*-----------------with users stored in db as spring security default (JDBC AUTHENTICATION)------------------*/
  @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery(SQL_QUERY_FOR_USERS)
                .authoritiesByUsernameQuery(SQL_QUERY_FOR_AUTHORITIES);
    }
    
                           
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}