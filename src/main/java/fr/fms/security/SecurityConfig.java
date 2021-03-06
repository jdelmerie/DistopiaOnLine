package fr.fms.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	String usersByUsernameQuery = "select email, password, enable from users where email = ?";
	String authoritiesByUsernameQuery = "SELECT u.email, r.name from users AS u \r\n"
			+ "INNER JOIN users_roles ur ON u.id = ur.users_id \r\n" + "INNER JOIN role r ON ur.roles_id = r.id \r\n"
			+ "where email = ?";

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncoder = passwordEncoder();
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(usersByUsernameQuery)
				.authoritiesByUsernameQuery(authoritiesByUsernameQuery).rolePrefix("ROLE_")
				.passwordEncoder(passwordEncoder);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login").defaultSuccessUrl("/").failureUrl("/login?error=true");
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
		http.exceptionHandling().accessDeniedPage("/403");
	}
}
