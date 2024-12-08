package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http


                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/login", "/users/register", "/css/**", "/js/**", "/tasks/add").permitAll() // Разрешаем доступ к странице логина и регистрации
                        .anyRequest().authenticated()  // Для остальных страниц требуется аутентификация
                )

                .formLogin(form -> form
                        .loginPage("/users/login")  // Указываем страницу для логина
                        .defaultSuccessUrl("/users/all", true)  // Перенаправление на главную страницу после успешного входа
                        .permitAll()
                )


                .logout(logout -> logout.permitAll());
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/tasks/add"));

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}




//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/users/login", "/users/register", "/css/**", "/js/**").permitAll() // Страницы логина и регистрации разрешены всем
//                        .requestMatchers("/tasks/add").authenticated() // Добавление задач только для аутентифицированных пользователей
//                        .anyRequest().authenticated() // Остальные страницы также требуют аутентификации
//                )
//                .formLogin(form -> form
//                        .loginPage("/users/login") // Указываем страницу для логина
//                        .defaultSuccessUrl("/tasks/all", true) // Перенаправление на список задач после успешного входа
//                        .permitAll()
//                )
//                .logout(logout -> logout.permitAll());
//
//        return http.build();
//    }

//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//
//
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/users/login", "/users/register", "/css/**", "/js/**").permitAll() // Разрешаем доступ к странице логина и регистрации
//                        .anyRequest().authenticated()  // Для остальных страниц требуется аутентификация
//                )
//                .formLogin(form -> form
//                        .loginPage("/users/login")  // Указываем страницу для логина
//                        .defaultSuccessUrl("/users/all", true)  // Перенаправление на главную страницу после успешного входа
//                        .permitAll()
//                )
//
//                .logout(logout -> logout.permitAll());
//
//        return http.build();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
