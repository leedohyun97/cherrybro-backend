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
	
}
