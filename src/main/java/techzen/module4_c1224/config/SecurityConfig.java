package techzen.module4_c1224.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // Khóa bí mật được sử dụng để ký JWT
    @Value("${jwt.signerKey}")
    private String signerKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // Cấu hình quyền truy cập cho các yêu cầu HTTP
        httpSecurity.authorizeHttpRequests(request -> {
            request
                    // Cho phép truy cập không hạn chế đối với endpoint "/student"
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/employees", "employees/**").hasAuthority("ROLE_ADMIN") // chỉ cho phép người dùng có quyền ADMIN truy cập

                    .anyRequest().authenticated(); // nhưng request còn lại phải được xác thực
        });

        // Vô hiệu hóa bảo mật CSRF (Cross-Site Request Forgery)
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity.oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())));

        // Xây dựng và trả về đối tượng SecurityFilterChain
        return httpSecurity.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        // Tạo khóa bí mật với thuật toán HS512
        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");

        // Cấu hình và tạo JwtDecoder với khóa bí mật và thuật toán mã hóa
        return NimbusJwtDecoder
                .withSecretKey(secretKeySpec)  // Đặt khóa bí mật
                .macAlgorithm(MacAlgorithm.HS512)  // Chọn thuật toán HS512
                .build();  // Xây dựng JwtDecoder
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        // Tạo JwtGrantedAuthoritiesConverter và đặt tiền tố quyền hạn
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        // Tạo JwtAuthenticationConverter và thiết lập JwtGrantedAuthoritiesConverter
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        // Trả về JwtAuthenticationConverter cấu hình sẵn
        return jwtAuthenticationConverter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Tạo và trả về PasswordEncoder với thuật toán mã hóa BCrypt
        return new BCryptPasswordEncoder(10);
    }
}
