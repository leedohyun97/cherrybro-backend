package cherrybro.service;

import org.springframework.stereotype.Service;
import cherrybro.dto.UsersDto;
import cherrybro.entitiy.Users;
import cherrybro.repository.UsersRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
	
	private final UsersRepository usersRepository;
	
	/* 회원가입 */
	@Override
	public UsersDto saveUser(UsersDto usersDto) {
		try {
			
			//DTO -> Entity 변환
			Users user = Users.toEntity(usersDto);
			
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
	public UsersDto updateUser(UsersDto dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* 회원 삭제 */
	@Override
	public UsersDto deleteUser(Long usersNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* 회원 조회 */
	@Override
	public UsersDto findUserById(Long usersNo) {
		try {
			//사용자 고유 번호로 회원 조회
			Users findUser = usersRepository.findByUsersNo(usersNo)
					//NULL값 발생 시 예외 발생
					.orElseThrow(() -> new RuntimeException("해당 사용자를 찾을 수 없습니다."));
			
			//Entity -> DTO 변환 후 반환
			return UsersDto.toDto(findUser);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
			throw new RuntimeException("회원 조회 중 오류 발생", e);
		}
	}
	
	
}
