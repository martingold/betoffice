package cz.uhk.ppro.betoffice.config

import cz.uhk.ppro.betoffice.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter(){

	@Autowired
	private val userService: UserService? = null

	@Autowired
	private val passwordEncoder: BCryptPasswordEncoder? = null

	@Bean
	fun authenticationProvider(): DaoAuthenticationProvider {
		val auth = DaoAuthenticationProvider()
		auth.setUserDetailsService(userService)
		auth.setPasswordEncoder(passwordEncoder)
		return auth
	}

	@Throws(Exception::class)
	override fun configure(auth: AuthenticationManagerBuilder) {
		auth.authenticationProvider(authenticationProvider())
	}

	//permit resources
	override fun configure(web: WebSecurity) {
			web.ignoring()
				.antMatchers(
					"/css/**", "/fonts/**",
					"/js/**");
	}

	override fun configure(http: HttpSecurity) {
		System.out.println(passwordEncoder!!.encode("tyna"));
		http
			.authorizeRequests()
			.antMatchers("/login").anonymous()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.usernameParameter("username")
			.passwordParameter("password")
			.and()
			.httpBasic()
	}
}
