package br.com.luciano.npj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.luciano.npj.security.AppUserDetailsService;

@EnableWebSecurity
@ComponentScan(basePackageClasses = AppUserDetailsService.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/layout/**")
			.antMatchers("/images/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/acoes/**").hasRole("CAD_ACAO")
				.antMatchers("/alunos/**").hasRole("CAD_ALUNO")
				.antMatchers("/assistidos/**").hasAnyRole("CAD_ASSISTIDO", "PES_ASSISTIDOS")
				.antMatchers("/cargas/**").hasRole("CAD_CARGA")
				.antMatchers("/disciplinas/**").hasRole("CAD_DISCIPLINA")
				.antMatchers("/documentos/**").hasRole("CAD_DOCUMENTO")
				.antMatchers("/expedientes/**").hasRole("CAD_EXPEDIENTE")
				.antMatchers("/funcionarios/**").hasAnyRole("CAD_FUNCIONARIO", "PES_FUNCIONARIOS")
				.antMatchers("/grupos/**").hasRole("CAD_GRUPO")
				.antMatchers("/pessoas/**").hasRole("CAD_PESSOA")
				.antMatchers("/processos/**").hasAnyRole("CAD_PROCESSO", "PES_PROCESSOS")
				.antMatchers("/reus/**").hasRole("CAD_REU")
				.antMatchers("/turmas/**").hasRole("CAD_TURMA")
				.antMatchers("/usuarios/**").hasAnyRole("CAD_USUARIO", "EDI_USUARIO")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.and()
			.exceptionHandling()
				.accessDeniedPage("/403")
				.and()
			.sessionManagement()
				.invalidSessionUrl("/login");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
