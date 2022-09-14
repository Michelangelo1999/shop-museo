package jana60.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	@Bean
	DatabaseUserDetailsService userDetailsService() {
		return new DatabaseUserDetailsService();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	SecurityFilterChain filteChain(HttpSecurity http) throws Exception {
		// quali ruoli hanno autorizzazione a quali aree
		http.authorizeRequests().antMatchers("/gestionale").hasAuthority("ADMIN").antMatchers("/accesso")
				.hasAuthority("ADMIN").antMatchers("/acquisto").hasAuthority("ADMIN").antMatchers("/assortimento")
				.hasAuthority("ADMIN").antMatchers("/card").hasAuthority("ADMIN").antMatchers("/fattura")
				.hasAuthority("ADMIN").antMatchers("/guide").hasAuthority("ADMIN").antMatchers("/magazzino")
				.hasAuthority("ADMIN").antMatchers("/prodotto").hasAuthority("ADMIN").antMatchers("/rifornimento")
				.hasAuthority("ADMIN").antMatchers("/visite").hasAuthority("ADMIN").antMatchers("/").permitAll().and()
				.formLogin().and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
		return http.build();
	}

}