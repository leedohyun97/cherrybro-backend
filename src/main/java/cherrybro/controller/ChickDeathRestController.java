package cherrybro.controller;


import java.nio.charset.Charset;
import java.util.List;

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


import cherrybro.dto.ChickDeathDto;
import cherrybro.response.Response;
import cherrybro.response.ResponseMessage;
import cherrybro.response.ResponseStatusCode;
import cherrybro.service.ChickDeathService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@CrossOrigin//리액트 API 호출 허용
@RestController//컨트롤러
@RequiredArgsConstructor
@RequestMapping("/chickDeath")
public class ChickDeathRestController {
	
	private final ChickDeathService chickDeathService;
	
	/* 도태폐기 등록 */
	@Operation(summary = "도태폐기 등록")
	@PostMapping
	public ResponseEntity<Response<ChickDeathDto>> createChickDeath(@RequestBody ChickDeathDto chickDeathDto) {
		try {
			//응답 객체 생성
			Response<ChickDeathDto> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//저장
			ChickDeathDto saveChickDeathDto = chickDeathService.saveChickDeath(chickDeathDto);
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.CREATED_CHICK_DEATH_SUCCESS);
			response.setMessage(ResponseMessage.CREATED_CHICK_DEATH_SUCCESS);
			response.setData(saveChickDeathDto);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
		    Response<ChickDeathDto> response = new Response<>();
		    response.setStatus(ResponseStatusCode.CREATED_CHICK_DEATH_FAIL);
		    response.setMessage(ResponseMessage.CREATED_CHICK_DEATH_FAIL);
		    response.setData(null);
		    
			//반환할 응답Entity 생성 및 반환
		    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* 도태폐기 수정 */
	@Operation(summary = "도태폐기 수정")
	@PutMapping("/{chickDeathNo}")
	public ResponseEntity<Response<ChickDeathDto>> updateChickDeath(@PathVariable("chickDeathNo") Long chickDeathNo, @RequestBody ChickDeathDto chickDeathDto) {
		try {
			
			//패스와 Dto의 번호가 일치하지 않을 시 에러
			if (!chickDeathNo.equals(chickDeathDto.getChickDeathNo())) {
			    throw new IllegalArgumentException("요청 경로와 본문 번호가 일치하지 않습니다.");
			}
			
			chickDeathDto.setChickDeathNo(chickDeathNo);
			
			//응답 객체 생성
			Response<ChickDeathDto> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//수정
			ChickDeathDto updateChickDeathDto = chickDeathService.updateChickDeath(chickDeathDto);
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.UPDATE_CHICK_DEATH_SUCCESS);
			response.setMessage(ResponseMessage.UPDATE_CHICK_DEATH_SUCCESS);
			response.setData(updateChickDeathDto);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
		    Response<ChickDeathDto> response = new Response<>();
		    response.setStatus(ResponseStatusCode.UPDATE_CHICK_DEATH_FAIL);
		    response.setMessage(ResponseMessage.UPDATE_CHICK_DEATH_FAIL);
		    response.setData(null);
		    
			//반환할 응답Entity 생성 및 반환
		    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* 도태폐기 삭제 */
	@Operation(summary = "도태폐기 삭제")
	@DeleteMapping("/{chickDeathNo}")
	public ResponseEntity<Response<ChickDeathDto>> deleteChickDeath(@PathVariable("chickDeathNo") Long chickDeathNo) {
		try {
			//삭제 실행
			ChickDeathDto deletedChickDeath = chickDeathService.deleteChickDeathById(chickDeathNo);

			//응답 객체 생성
			Response<ChickDeathDto> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.DELETE_CHICK_DEATH_SUCCESS);
			response.setMessage(ResponseMessage.DELETE_CHICK_DEATH_SUCCESS);
			response.setData(deletedChickDeath);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<ChickDeathDto> response = new Response<>();
			response.setStatus(ResponseStatusCode.DELETE_CHICK_DEATH_FAIL);
			response.setMessage(ResponseMessage.DELETE_CHICK_DEATH_FAIL);
			response.setData(null);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* 도태폐기 조회 */
	@Operation(summary = "도태폐기 조회")
	@GetMapping("/{chickDeathNo}")
	public ResponseEntity<Response<ChickDeathDto>> getChickDeath(@PathVariable("chickDeathNo") Long chickDeathNo) {
		try {
			//도태폐기 번호로 도태폐기 조회
			ChickDeathDto chickDeathDto = chickDeathService.findChickDeathById(chickDeathNo);
			
			//응답 객체 생성
			Response<ChickDeathDto> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.READ_CHICK_DEATH_SUCCESS);
			response.setMessage(ResponseMessage.READ_CHICK_DEATH_SUCCESS);
			response.setData(chickDeathDto);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<ChickDeathDto> response = new Response<>();
			response.setStatus(ResponseStatusCode.READ_CHICK_DEATH_FAIL);
			response.setMessage(ResponseMessage.READ_CHICK_DEATH_FAIL);
			response.setData(null);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/* 도태폐기 리스트 조회 */
	@Operation(summary = "도태폐기 목록 조회")
	@GetMapping("/list")
	public ResponseEntity<Response<List<ChickDeathDto>>> getChickDeathsByFarmSection(@RequestParam("farmSectionNo") Long farmSectionNo) {
		try {
			
			//농장동 번호로 도태폐기 조회
			List<ChickDeathDto> chickDeathDtoList = chickDeathService.findChickDeathByFarmSectionNo(farmSectionNo);;
			
			//응답 객체 생성
			Response<List<ChickDeathDto>> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.READ_CHICK_DEATH_LIST_SUCCESS);
			response.setMessage(ResponseMessage.READ_CHICK_DEATH_LIST_SUCCESS);
			response.setData(chickDeathDtoList);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<List<ChickDeathDto>> response = new Response<>();
			response.setStatus(ResponseStatusCode.READ_CHICK_DEATH_LIST_FAIL);
			response.setMessage(ResponseMessage.READ_CHICK_DEATH_LIST_FAIL);
			response.setData(null);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/* 도태폐기 리스트 조회 */
	@Operation(summary = "모든 도태폐기 목록 조회(관리자용)")
	@GetMapping("/list/all")
	public ResponseEntity<Response<List<ChickDeathDto>>> getAllChickDeath() {
		try {
			
			//농장동 번호로 도태폐기 조회
			List<ChickDeathDto> chickDeathDtoList = chickDeathService.findAllChickDeath();
			
			//응답 객체 생성
			Response<List<ChickDeathDto>> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.READ_CHICK_DEATH_LIST_SUCCESS);
			response.setMessage(ResponseMessage.READ_CHICK_DEATH_LIST_SUCCESS);
			response.setData(chickDeathDtoList);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<List<ChickDeathDto>> response = new Response<>();
			response.setStatus(ResponseStatusCode.READ_CHICK_DEATH_LIST_FAIL);
			response.setMessage(ResponseMessage.READ_CHICK_DEATH_LIST_FAIL);
			response.setData(null);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* 폐사 수 누적합 조회 */
	@Operation(summary = "폐사 수 누적합 조회")
	@GetMapping("/list/total")
	public ResponseEntity<Response<Integer>> getTotalChickDeathNumberByFarmSectionNo(@RequestParam("farmSectionNo") Long farmSectionNo) {
		try {
			
			Integer totalDeath = chickDeathService.getTotalChickDeathNumberByFarmSectionNo(farmSectionNo);
			
			//응답 객체 생성
			Response<Integer> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.READ_CHICK_DEATH_TOTAL_SUCCESS);
			response.setMessage(ResponseMessage.READ_CHICK_DEATH_TOTAL_SUCCESS);
			response.setData(totalDeath);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<Integer> response = new Response<>();
			response.setStatus(ResponseStatusCode.READ_CHICK_DEATH_TOTAL_FAIL);
			response.setMessage(ResponseMessage.READ_CHICK_DEATH_TOTAL_FAIL);
			response.setData(0);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

}
