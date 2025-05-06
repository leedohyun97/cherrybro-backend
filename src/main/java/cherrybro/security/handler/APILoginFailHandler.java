package cherrybro.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//로그인 실패 시 실행되는 핸들러 클래스 (API 전용 응답 처리)
public class APILoginFailHandler implements AuthenticationFailureHandler {

    //로그인 실패 시 호출되는 메서드
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        
        //요청 헤더 전체 출력 (디버깅용 로그)
        request.getHeaderNames().asIterator().forEachRemaining(header -> {
            System.out.println(">>>>> APILoginFailHandler Header: " + header + " = " + request.getHeader(header));
        });

        //Gson 객체 생성 → Map을 JSON 문자열로 변환하기 위해 사용
        Gson gson = new Gson();

        //실패 응답용 JSON 생성: {"error": "ERROR_LOGIN"}
        String jsonStr = gson.toJson(Map.of("error", "ERROR_LOGIN"));

        //응답 타입을 JSON으로 지정
        response.setContentType("application/json");

        //JSON 문자열을 응답으로 전송
        PrintWriter printWriter = response.getWriter();
        printWriter.println(jsonStr);
        printWriter.close();
    }
}
