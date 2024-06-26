package ca.sheridancollege.falzonm.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	 private UserDetailsServiceImpl userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	

	
	 @Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
		 MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(introspector);
			return http.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(mvc.pattern("/secure/user/**")).hasRole("GUEST")
					 .requestMatchers(mvc.pattern("/secure/admin/**")).hasRole("USER")
					 .requestMatchers(mvc.pattern("/")).permitAll()
					 .requestMatchers(mvc.pattern("/js/**")).permitAll()
					 .requestMatchers(mvc.pattern("/css/**")).permitAll()
					 .requestMatchers(mvc.pattern("/images/**")).permitAll()
					 .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.POST,"/register")).permitAll()
					 .requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET,"/register")).permitAll()
					 .requestMatchers(mvc.pattern("/permission-denied")).permitAll()
					 .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
					 .requestMatchers(mvc.pattern("/**")).denyAll()
					 ).csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).disable())
							 .headers(headers -> headers.frameOptions(FrameOptionsConfig::disable))
							 .formLogin(form -> form.loginPage("/login")
									 .successHandler(authenticationSuccessHandler())
									 .permitAll())
							 .exceptionHandling(exception -> exception.accessDeniedPage("/permission-denied"))
							 .logout(logout -> logout.permitAll())
							 .build();
	 }
	 
	 @Bean
	 public AuthenticationSuccessHandler authenticationSuccessHandler() {
	     return (request, response, authentication) -> {
	         // Get the roles of the authenticated user
	         Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

	         // Check if the user has the USER role
	         if (authorities.stream().anyMatch(r -> r.getAuthority().equals("ROLE_USER"))) {
	             response.sendRedirect("/secure/admin/index");
	         }
	         // Check if the user has the GUEST role
	         else if (authorities.stream().anyMatch(r -> r.getAuthority().equals("ROLE_GUEST"))) {
	             response.sendRedirect("/secure/user/index");
	         }
	         // Handle other roles or scenarios if needed
	         else {
	             response.sendRedirect("/");
	         }
	     
	 };
	
	 }
}
