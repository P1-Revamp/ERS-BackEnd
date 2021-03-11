package com.revature.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.session.SessionManagementFilter;

import com.revature.security.CorsFilter;
import com.revature.security.auth.ErsUserDetailsService;

//@EnableGlobalMethodSecurity(prePostEnabled=true)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final ErsUserDetailsService ersUserDetailsService;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public SecurityConfig(ErsUserDetailsService ersUserDetailsService, PasswordEncoder passwordEncoder) {
		super();
		this.ersUserDetailsService = ersUserDetailsService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Bean
	CorsFilter corsFilter() {
	    return new CorsFilter();
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
		(
                (HttpSecurity)
                        (
                                (HttpSecurity)
                                        (
                                                (ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)
                                                        http
                                                                .headers().addHeaderWriter(
                                                                new StaticHeadersWriter("Access-Control-Allow-Origin", "*")).and()
                                                                .addFilterBefore(corsFilter(), SessionManagementFilter.class)
                                                                .csrf().disable()
                                                                .authorizeRequests()
                                                                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                                                                .anyRequest()

                                        ).authenticated().and()
//                        ).formLogin().and()
        )).httpBasic();
		
		
//
//            http
//                    .headers().addHeaderWriter(
//                    new StaticHeadersWriter("Access-Control-Allow-Origin", "*")).and()
//                    .addFilterBefore(corsFilter(), SessionManagementFilter.class)
//                    .csrf().disable()
//                    .authorizeRequests()
//                    .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
//                    .anyRequest()
//                	.authenticated().and()
//					.httpBasic();
		
//		http
//			.addFilterBefore(corsFilter(), SessionManagementFilter.class) //adds your custom CorsFilter
////			.cors().and()
////			.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
//			.csrf().disable()
//			.authorizeRequests().anyRequest().authenticated().and()
//			.formLogin().and()
////			.logout().logout
//			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")).clearAuthentication(true).invalidateHttpSession(true).deleteCookies("JSESSIONID");
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(ersUserDetailsService);
		return provider;
	}

}
