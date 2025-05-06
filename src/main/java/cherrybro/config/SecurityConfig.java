package cherrybro.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import cherrybro.auth.PrincipalDetailsService;
import cherrybro.security.filter.JWTCheckFilter;
import cherrybro.security.handler.APILoginFailHandler;
import cherrybro.security.handler.APILoginSuccessHandler;
import lombok.RequiredArgsConstructor;

@Configuration //Spring 설정 클래스임을 명시
@EnableWebSecurity //Spring Security 활성화 (기본 보안 설정 커스터마이징 가능)
@EnableMethodSecurity //@PreAuthorize, @Secured 등 메서드 수준 보안 활성화
@RequiredArgsConstructor //final 필드를 자동 생성자 주입
public class SecurityConfig {

    // 사용자 인증을 처리하는 UserDetailsService 구현체
    private final PrincipalDetailsService principalDetailsService;

    // Spring Security의 핵심 보안 설정 필터 체인 정의
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    	
    	//개발 단계에서 CSRF 보호를 비활성화
		httpSecurity.csrf((config) -> {config.disable();});
		
		// 세션 관리 설정: Stateless 설정, 서버는 세션을 생성하지 않음
		httpSecurity.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    			
        // CORS 설정 적용
        httpSecurity.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        // 사용자 인증 시 사용자 정보를 불러올 서비스 등록
        httpSecurity.userDetailsService(principalDetailsService);

        /* ——————————————— Form 로그인 설정 ——————————————— */
        //httpSecurity.formLogin();
        httpSecurity.formLogin(form -> form
                .loginPage("/login") // 사용자 정의 로그인 페이지 경로
                .successHandler(new APILoginSuccessHandler()) // 로그인 성공 시 처리 핸들러
                .failureHandler(new APILoginFailHandler()) // 로그인 실패 시 처리 핸들러
        );

        // JWT 필터를 기본 로그인 필터 앞에 삽입 (인증 필터 우선 적용)
        httpSecurity.addFilterBefore(new JWTCheckFilter(), UsernamePasswordAuthenticationFilter.class);

        /* ——————————————— 로그아웃 설정 ——————————————— */
        httpSecurity.logout(logout -> logout
                .logoutUrl("/logout") // 로그아웃 URL
                .deleteCookies("users") // 로그아웃 시 삭제할 쿠키 이름
                .addLogoutHandler(new SecurityContextLogoutHandler()) // SecurityContext 초기화
        );

        /* ——————————————— 요청별 접근 권한 설정 ——————————————— */
        httpSecurity.authorizeHttpRequests(auth -> auth
//        	    .requestMatchers(HttpMethod.POST, "/users").permitAll() // 회원가입 허용
//        	    .requestMatchers("/login", "/logout").permitAll() //로그인, 로그아웃 허용
//        	    .requestMatchers("/farm-section", "/chickEntry", "/chickDeath").permitAll() //로그인, 로그아웃 허용
//        	    .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
//        	    .anyRequest().authenticated() // 나머지는 인증 필요
        	    .anyRequest().permitAll()
        	);

        return httpSecurity.build(); // 필터 체인 반환
    }

    /* ———————————————————— CORS 정책 설정 ———————————————————— */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // 모든 도메인에서 요청 허용
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        // 허용할 HTTP 메서드 지정
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
        // 허용할 헤더 지정
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        // 쿠키와 인증정보 포함 허용
        configuration.setAllowCredentials(true);

        // URL 패턴 기반 CORS 설정 적용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 경로에 적용

        return source;
    }
}
