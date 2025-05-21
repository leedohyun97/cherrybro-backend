package cherrybro.controller;


import java.nio.charset.Charset;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cherrybro.auth.PrincipalDetails;
import cherrybro.dto.FarmDto;
import cherrybro.response.Response;
import cherrybro.response.ResponseMessage;
import cherrybro.response.ResponseStatusCode;
import cherrybro.service.FarmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@CrossOrigin//리액트 API 호출 허용
@RestController//컨트롤러
@RequiredArgsConstructor
@RequestMapping("/farm")
public class FarmRestController {
	
	private final FarmService farmService;
	
	/* 농장 등록 */
	@Operation(summary = "농장 등록")
	@PostMapping
	public ResponseEntity<Response<FarmDto>> createFarm(@RequestBody FarmDto farmDto) {
		try {
			//응답 객체 생성
			Response<FarmDto> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//저장
			FarmDto saveFarmDto = farmService.saveFarm(farmDto);
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.CREATED_FARM_SUCCESS);
			response.setMessage(ResponseMessage.CREATED_FARM_SUCCESS);
			response.setData(saveFarmDto);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
		    Response<FarmDto> response = new Response<>();
		    response.setStatus(ResponseStatusCode.CREATED_FARM_FAIL);
		    response.setMessage(ResponseMessage.CREATED_FARM_FAIL);
		    response.setData(null);
		    
			//반환할 응답Entity 생성 및 반환
		    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* 농장 수정 */
	@Operation(summary = "농장 수정")
	@PutMapping("/{farmNo}")
	public ResponseEntity<Response<FarmDto>> updateFarm(@PathVariable("farmNo") Long farmNo, @RequestBody FarmDto farmDto) {
		try {
			
			//패스와 Dto의 번호가 일치하지 않을 시 에러
			if (!farmNo.equals(farmDto.getFarmNo())) {
			    throw new IllegalArgumentException("요청 경로와 본문 번호가 일치하지 않습니다.");
			}
			
			//farmNo를 farmDto에 설정
			farmDto.setFarmNo(farmNo);
			
			//응답 객체 생성
			Response<FarmDto> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//수정
			FarmDto updateFarmDto = farmService.updateFarm(farmDto);
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.UPDATE_FARM_SUCCESS);
			response.setMessage(ResponseMessage.UPDATE_FARM_SUCCESS);
			response.setData(updateFarmDto);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
		    Response<FarmDto> response = new Response<>();
		    response.setStatus(ResponseStatusCode.UPDATE_FARM_FAIL);
		    response.setMessage(ResponseMessage.UPDATE_FARM_FAIL);
		    response.setData(null);
		    
			//반환할 응답Entity 생성 및 반환
		    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* 농장 삭제 */
	@Operation(summary = "농장 삭제")
	@DeleteMapping("/{farmNo}")
	public ResponseEntity<Response<FarmDto>> deleteFarm(@PathVariable("farmNo") Long farmNo) {
		try {
			//삭제 실행
			FarmDto deletedFarm = farmService.deleteFarmById(farmNo);

			//응답 객체 생성
			Response<FarmDto> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.DELETE_FARM_SUCCESS);
			response.setMessage(ResponseMessage.DELETE_FARM_SUCCESS);
			response.setData(deletedFarm);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<FarmDto> response = new Response<>();
			response.setStatus(ResponseStatusCode.DELETE_FARM_FAIL);
			response.setMessage(ResponseMessage.DELETE_FARM_FAIL);
			response.setData(null);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* 농장 조회 */
	@Operation(summary = "농장 조회(농장주)")
	@SecurityRequirement(name = "BearerAuth") // 인증 토큰 필요 명시
	@PreAuthorize("hasRole('ROLE_FARMER')")
	@GetMapping("/my-farm")
	public ResponseEntity<Response<FarmDto>> getFarm(Authentication authentication) {
		try {
			//현재 로그인한 사용자의 인증 정보에서 사용자 번호(usersNo) 추출
			PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
			Long usersNo = principalDetails.getUsersNo();
			
			//사용자 번호로 사용자 조회
			FarmDto farmDto = farmService.findFarmByUsersNo(usersNo);
			
			//응답 객체 생성
			Response<FarmDto> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.READ_FARM_SUCCESS);
			response.setMessage(ResponseMessage.READ_FARM_SUCCESS);
			response.setData(farmDto);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<FarmDto> response = new Response<>();
			response.setStatus(ResponseStatusCode.READ_FARM_FAIL);
			response.setMessage(ResponseMessage.READ_FARM_FAIL);
			response.setData(null);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* 농장 조회 */
	@Operation(summary = "농장 조회(관리자)")
	@SecurityRequirement(name = "BearerAuth") // 인증 토큰 필요 명시
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/{usersNo}")
	public ResponseEntity<Response<FarmDto>> getFarm(Authentication authentication, @PathVariable("usersNo") Long usersNo) {
		try {
			
			//사용자 번호로 사용자 조회
			FarmDto farmDto = farmService.findFarmByUsersNo(usersNo);
			
			//응답 객체 생성
			Response<FarmDto> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.READ_FARM_SUCCESS);
			response.setMessage(ResponseMessage.READ_FARM_SUCCESS);
			response.setData(farmDto);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<FarmDto> response = new Response<>();
			response.setStatus(ResponseStatusCode.READ_FARM_FAIL);
			response.setMessage(ResponseMessage.READ_FARM_FAIL);
			response.setData(null);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/* 농장 리스트 조회 */
	@Operation(summary = "농장 목록 조회(관리자용)")
	@SecurityRequirement(name = "BearerAuth") // 인증 토큰 필요 명시
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/list")
	public ResponseEntity<Response<List<FarmDto>>> getAllFarm(Authentication authentication) {
		try {
			//사용자 번호로 사용자 조회
			List<FarmDto> farmDtoList = farmService.findFarms();
			
			//응답 객체 생성
			Response<List<FarmDto>> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.READ_FARM_SUCCESS);
			response.setMessage(ResponseMessage.READ_FARM_SUCCESS);
			response.setData(farmDtoList);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<List<FarmDto>> response = new Response<>();
			response.setStatus(ResponseStatusCode.READ_FARM_FAIL);
			response.setMessage(ResponseMessage.READ_FARM_FAIL);
			response.setData(null);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
