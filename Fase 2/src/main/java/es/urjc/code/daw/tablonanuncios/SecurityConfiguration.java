package es.urjc.code.daw.tablonanuncios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity 
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
    	// Public pages
        http.authorizeRequests().antMatchers("/Index").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
       
        

        // Private pages (all other pages)
        http.authorizeRequests().anyRequest().authenticated();

        // Login form
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/index", true);
        http.formLogin().failureUrl("/login");

        // Logout
        //http.logout().logoutUrl("/tablon");
        http.logout().logoutSuccessUrl("/login");
        
        // Disable CSRF at the moment
        http.csrf().disable();
    }
    
    @Autowired
    public UserRepositoryAuthenticationProvider authenticationProvider;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	

    	// User
        auth.inMemoryAuthentication().withUser("user").password("pass").roles("USER");
        
        auth.inMemoryAuthentication().withUser("admin").password("adminpass")
        .roles("USER", "ADMIN");
        
        auth.authenticationProvider(authenticationProvider);
    }

}
