package com.serp.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;
     
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());//mới add
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
    	.antMatchers("/listRole","/quotation","/listorder","/addorder","/stockRequisition","/addcustomer","/estimate","/editEstimate","/processingEditor","/listProgram","/program","/listcustomer").authenticated()
		.antMatchers("/listCustomer").access("hasRole('TX')")
    	.antMatchers("/listRole").access("hasRole('AD')")
    	.antMatchers("/quotationList").access("hasRole('TX')")
    	.antMatchers("/quotation").access("hasRole('TX')")
		.antMatchers("/stockRequisition").access("hasRole('TX') or hasRole('NVCN') or hasRole('TPTCKT') or hasRole('AD')")
    	.antMatchers("/addorder").access("hasRole('TX')")
    	.antMatchers("/estimate").access("hasRole('TX') or hasRole('AD') or hasRole('TCN')")
    	.antMatchers("/editEstimate").access("hasRole('TX') or hasRole('AD') or hasRole('TCN')")
    	.antMatchers("/stockRequisition/examine").access("hasRole('TX') or hasRole('TPTCKT') or hasRole('AD')")
    	.antMatchers("/stockRequisition/details").access("hasRole('NVCN') or hasRole('AD')")
    	.antMatchers("/processingEditor").access("hasRole('TX') or hasRole('AD') or hasRole('TTTCN')")
    	.antMatchers("/listProgram").access("hasRole('NVCN')")
    	.antMatchers("/program").access("hasRole('TX') or hasRole('NVCN')")
        .and().formLogin().loginPage("/login")
        .usernameParameter("ssoId").passwordParameter("password")
        .and().rememberMe().rememberMeParameter("remember-me").tokenRepository(persistentTokenRepository()).tokenValiditySeconds(86400)
        .and().exceptionHandling().accessDeniedPage("/Access_Denied")
        .and().csrf().disable();
    }
    
    @Autowired
    DataSource dataSource;
    
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(dataSource);
        return tokenRepositoryImpl;
    }
}
