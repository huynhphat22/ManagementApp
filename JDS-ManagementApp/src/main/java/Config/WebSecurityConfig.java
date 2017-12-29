package Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf().disable().
		authorizeRequests().antMatchers("/login").permitAll();
		httpSecurity.authorizeRequests().antMatchers("/").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MANAGER')");
		
		httpSecurity.authorizeRequests().antMatchers("/login").permitAll();
		httpSecurity.authorizeRequests().antMatchers("/register").permitAll();
		
		httpSecurity.authorizeRequests().antMatchers("/api/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MANAGER')");
		
		httpSecurity.authorizeRequests().antMatchers("/admin/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')");


		// Cấu hình cho Login Form.
		httpSecurity.authorizeRequests()
		 		.and().formLogin()//
				// Submit URL của trang login
				.loginProcessingUrl("/j_spring_security_check") // Submit URL
				.loginPage("/login")//
				.defaultSuccessUrl("/default")//
				.failureUrl("/login?error=true")//
				.usernameParameter("email")//
				.passwordParameter("password")

				// Cấu hình cho Logout Page.
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout=true");
	}

}
