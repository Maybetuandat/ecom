// package com.example.demo.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import
// org.springframework.security.core.userdetails.InMemoryUserDetailsManager;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig extends WebSecurityConfigurerAdapter {

// @Override
// protected void configure(HttpSecurity http) throws Exception {
// http
// .authorizeRequests()
// .antMatchers("/admin/**").hasRole("ADMIN") // Chỉ admin mới được truy cập
// .antMatchers("/user/**").hasAnyRole("USER", "ADMIN") // User và Admin đều
// được truy cập
// .antMatchers("/public/**").permitAll() // Mọi người đều có thể truy cập
// .anyRequest().authenticated() // Các yêu cầu khác yêu cầu xác thực
// .and()
// .formLogin() // Sử dụng form login
// .permitAll()
// .and()
// .logout()
// .permitAll();
// }

// @Override
// @Bean
// public UserDetailsService userDetailsService() {
// InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

// // Tạo tài khoản user
// manager.createUser(User.withUsername("user")
// .password(passwordEncoder().encode("password"))
// .roles("USER")
// .build());

// // Tạo tài khoản admin
// manager.createUser(User.withUsername("admin")
// .password(passwordEncoder().encode("admin"))
// .roles("ADMIN")
// .build());

// return manager;
// }

// @Bean
// public PasswordEncoder passwordEncoder() {
// return new BCryptPasswordEncoder(); // Mã hóa mật khẩu
// }
// }
