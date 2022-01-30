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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher


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

	//TODO - vylepšit práva + redirect po přihlášení
	override fun configure(http: HttpSecurity) {
		http
			.csrf()
			.disable()
			.authorizeRequests()
			.antMatchers("/login").anonymous()
			.antMatchers("/user").authenticated()
			.anyRequest()
			.permitAll()
			.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/user", true)
			.usernameParameter("username")
			.passwordParameter("password")
			.and()
			.logout()
			.logoutSuccessUrl("/")
			.logoutRequestMatcher(AntPathRequestMatcher("/user/logout"))
			.permitAll()
			.and()
			.httpBasic()
	}
}
