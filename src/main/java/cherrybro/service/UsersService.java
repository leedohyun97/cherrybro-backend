package cherrybro.service;

import cherrybro.dto.UsersDto;
import cherrybro.entitiy.Users;

public interface UsersService {
	
	//회원가입
	UsersDto saveUser(UsersDto usersDto);
	
	//회원수정
	UsersDto updateUser(UsersDto usersDto);
	
	//회원탈퇴
	UsersDto deleteUser(Long usersNo);
	
	//회원정보
	UsersDto findUserById(Long usersNo);
	
	//아이디 중복 조회
	Boolean checkUserIdDuplicate(String usersId);
	
	//이메일, 이름으로 아이디 찾기
	String findUsersIdByUserNameAndEmail(String usersName, String usersEmail);
	
	//아이디, 이메일로 비밀번호 찾기
	void findUsersPasswordByUsersIdAndEmail(String usersId, String usersEmail);
	
}
