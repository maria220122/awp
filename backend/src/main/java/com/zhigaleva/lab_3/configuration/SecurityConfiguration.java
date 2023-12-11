package com.zhigaleva.lab_3.configuration;

import com.zhigaleva.lab_3.dto.Authorities;
import com.zhigaleva.lab_3.entity.Authority;
import com.zhigaleva.lab_3.entity.User;
import com.zhigaleva.lab_3.repository.UserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Setter
public class SecurityConfiguration {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserRepository userRepository;

    @Bean
    public JdbcUserDetailsManager user(PasswordEncoder encoder) {
        if (userRepository.findByUsername("admin").isEmpty()) {
            createDefaultAdmin(encoder);
        }

        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers( "/docs/**", "users/register/**")
                            .permitAll()
                        .requestMatchers("/swagger-ui/**", "/swagger-resources/*", "/v3/api-docs/**")
                            .permitAll()
                        .requestMatchers("/users/list").hasAuthority(Authorities.ADMIN.name())
                        .anyRequest()
                            .authenticated())
                .httpBasic(Customizer.withDefaults())
                .formLogin(login ->
                        login.successForwardUrl("/users/current"))
                .logout(LogoutConfigurer::permitAll)
                .rememberMe(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private void createDefaultAdmin(PasswordEncoder encoder) {
        Authority authority = Authority.builder()
                .authority(Authorities.ADMIN.name())
                .username("admin")
                .build();

        User user = User.builder()
                .name("Админ")
                .username("admin")
                .password(encoder.encode("admin"))
                .authority(authority)
                .enabled(true)
                .build();
        userRepository.save(user);
    }
}
