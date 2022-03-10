package kz.iitu.kettik.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@AllArgsConstructor
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

//    private final DataSource _db;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }



//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        String fetchUsersQuery = "select email, password, enable " +
//                "as enabled from users " +
//                "where email = ?";
//        String fetchRolesQuery = "select email, role " +
//                "from users " +
//                "where email = ?";
//
//        auth.jdbcAuthentication()
//                .dataSource(_db)
//                .usersByUsernameQuery(fetchUsersQuery)
//                .authoritiesByUsernameQuery(fetchRolesQuery);
//    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login");

        httpSecurity
                .authorizeRequests()
                .antMatchers("/**")
                .permitAll();

        httpSecurity
                .cors()
                .and()
                .csrf()
                .disable();

        httpSecurity.authorizeRequests()
                .antMatchers("/private/**")
                .authenticated()
                .antMatchers("/public/**")
                .permitAll()
                .and()
                .httpBasic();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
