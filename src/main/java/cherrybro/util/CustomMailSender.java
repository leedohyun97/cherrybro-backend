package cherrybro.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import cherrybro.entitiy.Users;
import lombok.RequiredArgsConstructor;


@Service //스프링이 이 클래스를 Service 계층의 Bean으로 등록하도록 지정
@RequiredArgsConstructor //final 필드를 자동 주입하기 위한 Lombok 어노테이션
public class CustomMailSender {
	
    //스프링에서 자동으로 생성해주는 이메일 전송 도구
    private final JavaMailSender mailSender;
    
    public void sendFindPasswordMail(Users user, String tempPassword) {
    	
    	// 간단한 텍스트 이메일을 생성하기 위한 메시지 객체 생성
    	SimpleMailMessage message = new SimpleMailMessage();
    	
    	// 수신자 이메일 설정 (회원의 이메일)
    	message.setTo(user.getUsersEmail());
    	
    	// 이메일 제목 설정
    	message.setSubject("[체리부로] 임시 비밀번호 안내");
    	
    	// 이메일 본문 설정
    	message.setText(
    			user.getUsersName() + "님, \n\n" +
    			"요청하신 임시 비밀번호는 다음과 같습니다. \n" +
    			tempPassword + "\n\n" +
    			"로그인 후 반드시 비밀번호를 재설정해주세요."
    			);
    	
    	// 이메일 발송
    	mailSender.send(message);
    }
}
