package br.milliecarol.projeto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    //transforma filterChain em um componente gerenciado pelo spring - pra configurar a segurança
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // desativa CSRF(cross-site rfequest forgery) para requisições via Postman?
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // libera todas as rotas da API sem precisar de autenificação
            );
        return http.build();
    }
}
