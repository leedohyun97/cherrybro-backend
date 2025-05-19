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


import cherrybro.dto.ChickEntryDto;
import cherrybro.response.Response;
import cherrybro.response.ResponseMessage;
import cherrybro.response.ResponseStatusCode;
import cherrybro.service.ChickEntryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@CrossOrigin//리액트 API 호출 허용
@RestController//컨트롤러
@RequiredArgsConstructor
@RequestMapping("/chickEntry")
public class ChickEntryRestController {
	
	private final ChickEntryService chickEntryService;
	
	/* 입추수수 등록 */
	@Operation(summary = "입추수수 등록")
	@PostMapping
	public ResponseEntity<Response<ChickEntryDto>> createChickEntry(@RequestBody ChickEntryDto chickEntryDto) {
		try {
			//응답 객체 생성
			Response<ChickEntryDto> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//저장
			ChickEntryDto saveChickEntryDto = chickEntryService.saveChickEntry(chickEntryDto);
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.CREATED_CHICK_ENTRY_SUCCESS);
			response.setMessage(ResponseMessage.CREATED_CHICK_ENTRY_SUCCESS);
			response.setData(saveChickEntryDto);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
		    Response<ChickEntryDto> response = new Response<>();
		    response.setStatus(ResponseStatusCode.CREATED_CHICK_ENTRY_FAIL);
		    response.setMessage(ResponseMessage.CREATED_CHICK_ENTRY_FAIL);
		    response.setData(null);
		    
			//반환할 응답Entity 생성 및 반환
		    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* 입추수수 수정 */
	@Operation(summary = "입추수수 수정")
	@PutMapping("/{chickEntryNo}")
	public ResponseEntity<Response<ChickEntryDto>> updateChickEntry(@PathVariable("chickEntryNo") Long chickEntryNo, @RequestBody ChickEntryDto chickEntryDto) {
		try {
			
			//패스와 Dto의 번호가 일치하지 않을 시 에러
			if (!chickEntryNo.equals(chickEntryDto.getChickEntryNo())) {
			    throw new IllegalArgumentException("요청 경로와 본문 번호가 일치하지 않습니다.");
			}
			
			chickEntryDto.setChickEntryNo(chickEntryNo);
			//응답 객체 생성
			Response<ChickEntryDto> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//수정
			ChickEntryDto updateChickEntryDto = chickEntryService.updateChickEntry(chickEntryDto);
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.UPDATE_CHICK_ENTRY_SUCCESS);
			response.setMessage(ResponseMessage.UPDATE_CHICK_ENTRY_SUCCESS);
			response.setData(updateChickEntryDto);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
		    Response<ChickEntryDto> response = new Response<>();
		    response.setStatus(ResponseStatusCode.UPDATE_CHICK_ENTRY_FAIL);
		    response.setMessage(ResponseMessage.UPDATE_CHICK_ENTRY_FAIL);
		    response.setData(null);
		    
			//반환할 응답Entity 생성 및 반환
		    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* 입추수수 삭제 */
	@Operation(summary = "입추수수 삭제")
	@DeleteMapping("/{chickEntryNo}")
	public ResponseEntity<Response<ChickEntryDto>> deleteChickEntry(@PathVariable("chickEntryNo") Long chickEntryNo) {
		try {
			//삭제 실행
			ChickEntryDto deletedChickEntry = chickEntryService.deleteChickEntryById(chickEntryNo);

			//응답 객체 생성
			Response<ChickEntryDto> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.DELETE_CHICK_ENTRY_SUCCESS);
			response.setMessage(ResponseMessage.DELETE_CHICK_ENTRY_SUCCESS);
			response.setData(deletedChickEntry);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<ChickEntryDto> response = new Response<>();
			response.setStatus(ResponseStatusCode.DELETE_CHICK_ENTRY_FAIL);
			response.setMessage(ResponseMessage.DELETE_CHICK_ENTRY_FAIL);
			response.setData(null);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* 입추수수 조회 */
	@Operation(summary = "입추수수 조회")
	@GetMapping("/{chickEntryNo}")
	public ResponseEntity<Response<ChickEntryDto>> getChickEntry(@PathVariable("chickEntryNo") Long chickEntryNo) {
		try {
			
			//입추수수 번호로 입추수수 조회
			ChickEntryDto chickEntryDto = chickEntryService.findChickEntryById(chickEntryNo);
			
			//응답 객체 생성
			Response<ChickEntryDto> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.READ_CHICK_ENTRY_SUCCESS);
			response.setMessage(ResponseMessage.READ_CHICK_ENTRY_SUCCESS);
			response.setData(chickEntryDto);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<ChickEntryDto> response = new Response<>();
			response.setStatus(ResponseStatusCode.READ_CHICK_ENTRY_FAIL);
			response.setMessage(ResponseMessage.READ_CHICK_ENTRY_FAIL);
			response.setData(null);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/* 입추수수 리스트 조회 */
	@Operation(summary = "입추수수 목록 조회")
	@GetMapping("/list")
	public ResponseEntity<Response<List<ChickEntryDto>>> getChickEntriesByFarmSection(@RequestParam("farmSectionNo") Long farmSectionNo) {
		try {
			
			//농장동 번호로 입추수수 조회
			List<ChickEntryDto> chickEntryDtoList = chickEntryService.findChickEntriesByFarmSectionNo(farmSectionNo);
			
			//응답 객체 생성
			Response<List<ChickEntryDto>> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.READ_CHICK_ENTRY_LIST_SUCCESS);
			response.setMessage(ResponseMessage.READ_CHICK_ENTRY_LIST_SUCCESS);
			response.setData(chickEntryDtoList);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<List<ChickEntryDto>> response = new Response<>();
			response.setStatus(ResponseStatusCode.READ_CHICK_ENTRY_LIST_FAIL);
			response.setMessage(ResponseMessage.READ_CHICK_ENTRY_LIST_FAIL);
			response.setData(null);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* 입추수수 리스트 조회 */
	@Operation(summary = "모든 입추수수 목록 조회(관리자용)")
	@GetMapping("/list/all")
	public ResponseEntity<Response<List<ChickEntryDto>>> getChickEntriesByFarmSection() {
		try {
			
			//농장동 번호로 입추수수 조회
			List<ChickEntryDto> chickEntryDtoList = chickEntryService.findAllChickEntries();
			
			//응답 객체 생성
			Response<List<ChickEntryDto>> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.READ_CHICK_ENTRY_LIST_SUCCESS);
			response.setMessage(ResponseMessage.READ_CHICK_ENTRY_LIST_SUCCESS);
			response.setData(chickEntryDtoList);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<List<ChickEntryDto>> response = new Response<>();
			response.setStatus(ResponseStatusCode.READ_CHICK_ENTRY_LIST_FAIL);
			response.setMessage(ResponseMessage.READ_CHICK_ENTRY_LIST_FAIL);
			response.setData(null);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* 입추 수 누적합 조회 */
	@Operation(summary = "입추 수 누적합 조회")
	@GetMapping("/list/total")
	public ResponseEntity<Response<Integer>> getTotalChickEntryNumberByFarmSectionNo(@RequestParam("farmSectionNo") Long farmSectionNo) {
		try {
			
			Integer totalEntry = chickEntryService.getTotalChickEntryNumberByFarmSectionNo(farmSectionNo);
			
			//응답 객체 생성
			Response<Integer> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.READ_CHICK_ENTRY_TOTAL_SUCCESS);
			response.setMessage(ResponseMessage.READ_CHICK_ENTRY_TOTAL_SUCCESS);
			response.setData(totalEntry);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<Integer> response = new Response<>();
			response.setStatus(ResponseStatusCode.READ_CHICK_ENTRY_TOTAL_FAIL);
			response.setMessage(ResponseMessage.READ_CHICK_ENTRY_TOTAL_FAIL);
			response.setData(0);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
