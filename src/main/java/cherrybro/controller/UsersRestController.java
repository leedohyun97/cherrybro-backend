package cherrybro.controller;


import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cherrybro.auth.PrincipalDetails;
import cherrybro.dto.UsersDto;
import cherrybro.dto.UsersJoinDto;
import cherrybro.response.Response;
import cherrybro.response.ResponseMessage;
import cherrybro.response.ResponseStatusCode;
import cherrybro.service.UsersJoinService;
import cherrybro.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.core.Authentication;
import lombok.RequiredArgsConstructor;

@CrossOrigin//리액트 API 호출 허용
@RestController//컨트롤러
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersRestController {
	
	private final UsersService usersService;
	
	private final UsersJoinService usersJoinService;
	
	/* 회원 가입 */
	@Operation(summary = "회원 가입")
	@PostMapping
	public ResponseEntity<Response<UsersDto>> createUser(@RequestBody UsersJoinDto usersJoinDto) {
		try {
			//응답 객체 생성
			Response<UsersDto> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//저장
			UsersDto saveUsersDto = usersJoinService.saveUser(usersJoinDto);
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.CREATED_USER_SUCCESS);
			response.setMessage(ResponseMessage.CREATED_USER_SUCCESS);
			response.setData(saveUsersDto);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
		    Response<UsersDto> response = new Response<>();
		    response.setStatus(ResponseStatusCode.CREATED_USER_FAIL);
		    response.setMessage(ResponseMessage.CREATED_USER_FAIL);
		    response.setData(null);
		    
			//반환할 응답Entity 생성 및 반환
		    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* 회원 수정 */
	@Operation(summary = "회원 수정")
	@PutMapping("/{usersNo}")
	public ResponseEntity<Response<UsersDto>> updateUser(@PathVariable("usersNo") Long usersNo, @RequestBody UsersDto usersDto) {
		try {
			
			//패스와 Dto의 번호가 일치하지 않을 시 에러
			if (!usersNo.equals(usersDto.getUsersNo())) {
			    throw new IllegalArgumentException("요청 경로와 본문 번호가 일치하지 않습니다.");
			}
			
			//farmNo를 farmDto에 설정
			usersDto.setUsersNo(usersNo);
			
			//응답 객체 생성
			Response<UsersDto> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//수정
			UsersDto updateUsersDto = usersService.updateUser(usersDto);
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.UPDATE_USER_SUCCESS);
			response.setMessage(ResponseMessage.UPDATE_USER_SUCCESS);
			response.setData(updateUsersDto);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
		    Response<UsersDto> response = new Response<>();
		    response.setStatus(ResponseStatusCode.UPDATE_USER_FAIL);
		    response.setMessage(ResponseMessage.UPDATE_USER_FAIL);
		    response.setData(null);
		    
			//반환할 응답Entity 생성 및 반환
		    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* 회원 삭제 */
	@Operation(summary = "회원 삭제")
	@DeleteMapping("/{usersNo}")
	public ResponseEntity<Response<UsersDto>> deleteUser(@PathVariable("usersNo") Long usersNo) {
		try {
			//삭제 실행
			UsersDto deletedUser = usersService.deleteUser(usersNo);

			//응답 객체 생성
			Response<UsersDto> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.DELETE_USER_SUCCESS);
			response.setMessage(ResponseMessage.DELETE_USER_SUCCESS);
			response.setData(deletedUser);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<UsersDto> response = new Response<>();
			response.setStatus(ResponseStatusCode.DELETE_USER_FAIL);
			response.setMessage(ResponseMessage.DELETE_USER_FAIL);
			response.setData(null);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* 회원 조회 */
	@Operation(summary = "회원 조회(토큰)")
	@SecurityRequirement(name = "BearerAuth")//API 엔드포인트가 인증을 요구한다는 것을 문서화(Swagger에서 JWT인증을 명시
	@GetMapping
	public ResponseEntity<Response<UsersDto>> getUser(Authentication authentication) {
		try {
			
			PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
			
			Long usersNo = principalDetails.getUsersNo();
			
			//사용자 번호로 사용자 조회
			UsersDto usersDto = usersService.findUserById(usersNo);
			
			//응답 객체 생성
			Response<UsersDto> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.READ_USER_SUCCESS);
			response.setMessage(ResponseMessage.READ_USER_SUCCESS);
			response.setData(usersDto);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<UsersDto> response = new Response<>();
			response.setStatus(ResponseStatusCode.READ_USER_FAIL);
			response.setMessage(ResponseMessage.READ_USER_FAIL);
			response.setData(null);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* 아이디 중복 체크 */
	@Operation(summary = "사용자 아이디 중복 체크")
	@GetMapping("/check-id")
	public ResponseEntity<Response<Boolean>> checkUserIdDuplicate(@RequestParam("usersId") String usersId) {
		try {
			
			//사용자 아이디로 아이디 중복 조회
			Boolean isDuplicate = usersService.checkUserIdDuplicate(usersId);
			
			//응답 객체 생성
			Response<Boolean> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.READ_USER_SUCCESS);
			response.setMessage(ResponseMessage.READ_USER_SUCCESS);
			response.setData(isDuplicate);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<Boolean> response = new Response<>();
			response.setStatus(ResponseStatusCode.READ_USER_FAIL);
			response.setMessage(ResponseMessage.READ_USER_FAIL);
			response.setData(null);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

}
