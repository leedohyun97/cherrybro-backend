package cherrybro.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import cherrybro.dto.UsersDto;
import cherrybro.entitiy.Users;
import cherrybro.repository.UsersRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
	
	private final UsersRepository usersRepository;
	
	private final PasswordEncoder passwordEncoder;
	
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
			user.setUsersPassword(usersDto.getUsersPassword());
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
					.orElseThrow(() -> new RuntimeException("이."));
			
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
	
	
	
}
