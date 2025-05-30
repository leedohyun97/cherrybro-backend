package cherrybro.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.google.gson.Gson;

import cherrybro.auth.PrincipalDetails;
import cherrybro.util.JWTUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//로그인 성공 시 실행되는 핸들러 클래스
public class APILoginSuccessHandler implements AuthenticationSuccessHandler {

    //로그인 성공 시 호출되는 메서드
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        
        // 1. 인증된 사용자 정보 가져오기 (UserDetails 구현체)
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();

        // 2. 사용자 정보(claims) 가져오기
        Map<String, Object> claims = new HashMap<>();
        claims = principal.getClaims();  // usersNo, usersId, usersRole 포함

        // 3. Access Token (1시간) 및 Refresh Token (1일) 발급
        String accessToken = JWTUtil.generateToken(claims, 60);         // 60분짜리 access token
        String refreshToken = JWTUtil.generateToken(claims, 60 * 24);   // 24시간짜리 refresh token

        // 4. 토큰을 JSON 문자열로 포장하고 Base64로 인코딩
        String jsonValue = String.format("{\"accessToken\": \"%s\", \"refreshToken\": \"%s\"}", accessToken, refreshToken);
        String encodeValue = Base64.getEncoder().encodeToString(jsonValue.getBytes());

        // 5. Base64 인코딩한 JSON을 쿠키에 저장
        Cookie cookie = new Cookie("users", encodeValue);
        cookie.setHttpOnly(false);       // JavaScript에서도 접근 가능하게 설정
        cookie.setSecure(false);         // HTTPS만 허용하려면 true
        cookie.setPath("/");             // 모든 경로에서 쿠키 접근 가능
        cookie.setMaxAge(60 * 60 * 24);  // 1시간 유지

        // 6. 응답에 쿠키 추가
        response.addCookie(cookie);

        // 7. 클라이언트로 내려줄 JSON 응답에 토큰도 포함
        claims.put("accessToken", accessToken);
        claims.put("refreshToken", refreshToken);

        // 8. Gson으로 Map을 JSON 문자열로 변환
        Gson gson = new Gson();
        String jsonStr = gson.toJson(claims);

        // 9. 응답 헤더 설정 및 출력
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.println(jsonStr); // JSON 응답 출력
        printWriter.close();

        // 10. 응답 헤더에 Authorization 포함 (Bearer 토큰 방식)
        response.setHeader("Authorization", "Bearer " + accessToken);

        // 11. 응답이 이미 커밋되었다면 추가 작업 중단
        if (response.isCommitted()) {
            return;
        }
    }
}
