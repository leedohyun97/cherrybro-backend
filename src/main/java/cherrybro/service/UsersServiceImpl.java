package cherrybro.service;

import java.util.UUID;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import cherrybro.dto.UsersDto;
import cherrybro.entitiy.Users;
import cherrybro.repository.UsersRepository;
import cherrybro.util.CustomMailSender;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
	
	private final UsersRepository usersRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final CustomMailSender customMailSender;
	
	/* 회원가입 */
	@Override
	public UsersDto saveUser(UsersDto usersDto) {
		try {
			
			//DTO -> Entity 변환
			Users user = Users.toEntity(usersDto);
			
			user.setUsersPassword(passwordEncoder.encode(user.getUsersPassword()));
			
			//Entity객체 저장(회원가입)
			Users saveUser = usersRepository.save(user);
			
			//Entity -> DTO 변환
			return UsersDto.toDto(saveUser);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
	        throw new RuntimeException("회원 저장 중 오류 발생", e);
		}
	}
	
	
	/* 회원 수정 */
	@Override
	public UsersDto updateUser(UsersDto usersDto) {
		try {
			//수정할 사용자 조회
			Users user = usersRepository.findByUsersNo(usersDto.getUsersNo())
					.orElseThrow(() -> new RuntimeException("해당 사용자를 찾을 수 없습니다."));

			//수정할 필드 적용
			user.setUsersName(usersDto.getUsersName());
			user.setUsersEmail(usersDto.getUsersEmail());
			user.setUsersPhone(usersDto.getUsersPhone());
			user.setUsersPassword(passwordEncoder.encode(usersDto.getUsersPassword()));
			user.setUsersRole(usersDto.getUsersRole());

			//수정된 사용자 저장
			Users updateUser = usersRepository.save(user);

			//Entity -> DTO 변환
			return UsersDto.toDto(updateUser);

		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
			throw new RuntimeException("회원 수정 중 오류 발생", e);
		}
	}

	
	/* 회원 삭제 */
	@Override
	public UsersDto deleteUser(Long usersNo) {
		try {
			//삭제할 사용자 조회
			Users user = usersRepository.findByUsersNo(usersNo)
					.orElseThrow(() -> new RuntimeException("해당 사용자를 찾을 수 없습니다."));

			//삭제
			usersRepository.delete(user);

			//삭제된 사용자 정보를 DTO로 반환
			return UsersDto.toDto(user);

		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
			throw new RuntimeException("회원 삭제 중 오류 발생", e);
		}
	}

	
	/* 회원 조회 */
	@Override
	public UsersDto findUserById(Long usersNo) {
		try {
			//사용자 고유 번호로 회원 조회
			Users findUser = usersRepository.findByUsersNo(usersNo)
					//NULL값 발생 시 예외 발생
					.orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
			
			//Entity -> DTO 변환 후 반환
			return UsersDto.toDto(findUser);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
			throw new RuntimeException("회원 조회 중 오류 발생", e);
		}
	}
	
	/* 아이디 중복 검사 */
	@Override
	public Boolean checkUserIdDuplicate(String usersId) {
		try {
			//사용자 ID로 회원 조회
			Boolean isDuplicate = usersRepository.findByUsersId(usersId).isPresent();
			
			//중복일 시 true 반환
			return isDuplicate;
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
			throw new RuntimeException("회원 조회 중 오류 발생", e);
		}
	}

	
	/***** 아이디 마스킹 처리 *****/
	public String maskUserId(String userId) {
		//아이디 전체 길이 계산
	    int length = userId.length();
	    
	    //아이디 앞 2글자 추출 (항상 노출)
	    String prefix = userId.substring(0, 2);
	    
	    //아이디가 7자 이상이면 뒤 2글자도 노출, 아니면 노출하지 않음
	    String suffix = (length > 6) ? userId.substring(length - 2) : "";
	    
	    //가운데 부분은 전부 *로 마스킹 처리
	    String mask = "*".repeat(length - prefix.length() - suffix.length());
	    
	    //앞 + 마스킹 + 뒤 조합 후 반환
	    return prefix + mask + suffix;
	}
	
	/***** 이메일, 이름으로 아이디 찾기 *****/
	@Override
	public String findUsersIdByUserNameAndEmail(String usersName, String usersEmail) {
		try {
			//이름 + 이메일로 사용자 조회
			Users findUsersByUsersNameAndUsersEmail = usersRepository.findByUsersNameAndUsersEmail(usersName, usersEmail)
					.orElseThrow(() -> new RuntimeException("존재하지 않는 아이디입니다."));
			
			//사용자 아이디 선언
			String userId = findUsersByUsersNameAndUsersEmail.getUsersId();
			
			//아이디 마스킹 후 반환
			return maskUserId(userId);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
			throw new RuntimeException("아이디 찾기 중 오류 발생", e);
		}
	}
	
	
	/***** 이메일, ID로 비밀번호 찾기 *****/
	@Override
	public void findUsersPasswordByUsersIdAndEmail(String usersId, String usersEmail) {
		try {
			//사용자 ID로 사용자 조회 (없으면 예외 발생)
			Users findUsersByUsersId = usersRepository.findByUsersId(usersId)
					.orElseThrow(() -> new RuntimeException("존재하지 않는 아이디입니다."));
			
			//이메일 변수 선언
			String findUsersEmail = findUsersByUsersId.getUsersEmail();
			
			//DB에 등록된 이메일과 입력 이메일 비교 (equals로 정확하게 비교)
			if(!findUsersEmail.equals(usersEmail)) {
				throw new RuntimeException("아이디와 이메일을 다시 확인해주세요.");
			}
			
			//임시 비밀번호 생성 (UUID 앞 10자리 + 특수문자)
			UUID uid = UUID.randomUUID();
			String tempPassword = uid.toString().substring(0, 10) + "%@&";
			
			//임시 비밀번호를 사용자 이메일로 전송
			customMailSender.sendFindPasswordMail(findUsersByUsersId, tempPassword);
			
			//생성한 임시 비밀번호를 암호화
			tempPassword = passwordEncoder.encode(tempPassword);
			
			//사용자 엔티티의 비밀번호 변경
			findUsersByUsersId.changePassword(tempPassword);
			
			//변경된 비밀번호를 DB에 저장
			usersRepository.save(findUsersByUsersId);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
			throw new RuntimeException("비밀번호 찾기 중 오류 발생", e);
		}		
	}
	
	
	
	
}
