package Tradium;

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
        http.authorizeRequests().antMatchers("/index").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/register").permitAll();

        http.authorizeRequests().antMatchers("/about").permitAll();
        http.authorizeRequests().antMatchers("/user/**").permitAll();
        http.authorizeRequests().antMatchers("/consult").permitAll();
        http.authorizeRequests().antMatchers("/css/**").permitAll();
        http.authorizeRequests().antMatchers("/js/**").permitAll();
        http.authorizeRequests().antMatchers("/includes/**").permitAll();
        http.authorizeRequests().antMatchers("/images/**").permitAll();
        http.authorizeRequests().antMatchers("/fonts/**").permitAll();
        http.authorizeRequests().antMatchers("/vendor/**").permitAll();
        http.authorizeRequests().antMatchers("/product/*").permitAll();
        // Private pages (all other pages)
        http.authorizeRequests().antMatchers("/product/*/buy").authenticated();
        http.authorizeRequests().antMatchers("/product/*/offer").authenticated();
        
        http.authorizeRequests().antMatchers("/api/product/*").permitAll();
        http.authorizeRequests().antMatchers("/api/seller/*").permitAll();
        http.authorizeRequests().antMatchers("/api/user/new").permitAll();
        http.authorizeRequests().antMatchers("/api/featured").permitAll();
        http.authorizeRequests().antMatchers("/api/seller/*/valorations").permitAll();
        
        
        http.authorizeRequests().antMatchers("/api/product/*/delete").authenticated();
        http.authorizeRequests().antMatchers("/api/search/*").authenticated();
        http.authorizeRequests().antMatchers("/featured").authenticated();
        http.authorizeRequests().antMatchers("/api/product/new").authenticated();
        http.authorizeRequests().antMatchers("/api/chats").authenticated();
        http.authorizeRequests().antMatchers("/api/chats/*").authenticated();
        http.authorizeRequests().antMatchers("/api/chats/*/new").authenticated();
        http.authorizeRequests().antMatchers("/api/product/*/offer/*").authenticated();
        http.authorizeRequests().antMatchers("/api/product/*/buy").authenticated();
        
        http.authorizeRequests().anyRequest().authenticated();
        
        // Login form
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/index");
        http.formLogin().failureUrl("/login");

        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/index");
        
        http.csrf().disable();
        http.httpBasic();
    
        
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

