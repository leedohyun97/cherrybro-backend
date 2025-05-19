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


import cherrybro.dto.ChickDisposalDto;
import cherrybro.response.Response;
import cherrybro.response.ResponseMessage;
import cherrybro.response.ResponseStatusCode;
import cherrybro.service.ChickDisposalService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@CrossOrigin//리액트 API 호출 허용
@RestController//컨트롤러
@RequiredArgsConstructor
@RequestMapping("/chickDisposal")
public class ChickDisposalRestController {
	
	private final ChickDisposalService chickDisposalService;
	
	/* 도사 등록 */
	@Operation(summary = "도사 등록")
	@PostMapping
	public ResponseEntity<Response<ChickDisposalDto>> createChickDisposal(@RequestBody ChickDisposalDto chickDisposalDto) {
		try {
			//응답 객체 생성
			Response<ChickDisposalDto> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//저장
			ChickDisposalDto saveChickDisposalDto = chickDisposalService.saveChickDisposal(chickDisposalDto);
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.CREATED_CHICK_DISPOSAL_SUCCESS);
			response.setMessage(ResponseMessage.CREATED_CHICK_DISPOSAL_SUCCESS);
			response.setData(saveChickDisposalDto);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
		    Response<ChickDisposalDto> response = new Response<>();
		    response.setStatus(ResponseStatusCode.CREATED_CHICK_DISPOSAL_FAIL);
		    response.setMessage(ResponseMessage.CREATED_CHICK_DISPOSAL_FAIL);
		    response.setData(null);
		    
			//반환할 응답Entity 생성 및 반환
		    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* 도사 수정 */
	@Operation(summary = "도사 수정")
	@PutMapping("/{chickDisposalNo}")
	public ResponseEntity<Response<ChickDisposalDto>> updateChickDisposal(@PathVariable("chickDisposalNo") Long chickDisposalNo, @RequestBody ChickDisposalDto chickDisposalDto) {
		try {
			
			//패스와 Dto의 번호가 일치하지 않을 시 에러
			if (!chickDisposalNo.equals(chickDisposalDto.getChickDisposalNo())) {
			    throw new IllegalArgumentException("요청 경로와 본문 번호가 일치하지 않습니다.");
			}
			
			chickDisposalDto.setChickDisposalNo(chickDisposalNo);
			
			//응답 객체 생성
			Response<ChickDisposalDto> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//수정
			ChickDisposalDto updateChickDisposalDto = chickDisposalService.updateChickDisposal(chickDisposalDto);
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.UPDATE_CHICK_DISPOSAL_SUCCESS);
			response.setMessage(ResponseMessage.UPDATE_CHICK_DISPOSAL_SUCCESS);
			response.setData(updateChickDisposalDto);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
		    Response<ChickDisposalDto> response = new Response<>();
		    response.setStatus(ResponseStatusCode.UPDATE_CHICK_DISPOSAL_FAIL);
		    response.setMessage(ResponseMessage.UPDATE_CHICK_DISPOSAL_FAIL);
		    response.setData(null);
		    
			//반환할 응답Entity 생성 및 반환
		    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* 도사 삭제 */
	@Operation(summary = "도사 삭제")
	@DeleteMapping("/{chickDisposalNo}")
	public ResponseEntity<Response<ChickDisposalDto>> deleteChickDisposal(@PathVariable("chickDisposalNo") Long chickDisposalNo) {
		try {
			//삭제 실행
			ChickDisposalDto deletedChickDisposal = chickDisposalService.deleteChickDisposalById(chickDisposalNo);

			//응답 객체 생성
			Response<ChickDisposalDto> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.DELETE_CHICK_DISPOSAL_SUCCESS);
			response.setMessage(ResponseMessage.DELETE_CHICK_DISPOSAL_SUCCESS);
			response.setData(deletedChickDisposal);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<ChickDisposalDto> response = new Response<>();
			response.setStatus(ResponseStatusCode.DELETE_CHICK_DISPOSAL_FAIL);
			response.setMessage(ResponseMessage.DELETE_CHICK_DISPOSAL_FAIL);
			response.setData(null);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* 도사 조회 */
	@Operation(summary = "도사 조회")
	@GetMapping("/{chickDisposalNo}")
	public ResponseEntity<Response<ChickDisposalDto>> getChickDisposal(@PathVariable("chickDisposalNo") Long chickDisposalNo) {
		try {
			//도사 번호로 도사 조회
			ChickDisposalDto chickDisposalDto = chickDisposalService.findChickDisposalById(chickDisposalNo);
			
			//응답 객체 생성
			Response<ChickDisposalDto> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.READ_CHICK_DISPOSAL_SUCCESS);
			response.setMessage(ResponseMessage.READ_CHICK_DISPOSAL_SUCCESS);
			response.setData(chickDisposalDto);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<ChickDisposalDto> response = new Response<>();
			response.setStatus(ResponseStatusCode.READ_CHICK_DISPOSAL_FAIL);
			response.setMessage(ResponseMessage.READ_CHICK_DISPOSAL_FAIL);
			response.setData(null);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/* 도사 리스트 조회 */
	@Operation(summary = "도사 목록 조회")
	@GetMapping("/list")
	public ResponseEntity<Response<List<ChickDisposalDto>>> getChickDisposalsByFarmSection(@RequestParam("farmSectionNo") Long farmSectionNo) {
		try {
			
			//농장동 번호로 도사 조회
			List<ChickDisposalDto> chickDisposalDtoList = chickDisposalService.findChickDisposalByFarmSectionNo(farmSectionNo);;
			
			//응답 객체 생성
			Response<List<ChickDisposalDto>> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.READ_CHICK_DISPOSAL_LIST_SUCCESS);
			response.setMessage(ResponseMessage.READ_CHICK_DISPOSAL_LIST_SUCCESS);
			response.setData(chickDisposalDtoList);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<List<ChickDisposalDto>> response = new Response<>();
			response.setStatus(ResponseStatusCode.READ_CHICK_DISPOSAL_LIST_FAIL);
			response.setMessage(ResponseMessage.READ_CHICK_DISPOSAL_LIST_FAIL);
			response.setData(null);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/* 도사 리스트 조회 */
	@Operation(summary = "모든 도사 목록 조회(관리자용)")
	@GetMapping("/list/all")
	public ResponseEntity<Response<List<ChickDisposalDto>>> getAllChickDisposal() {
		try {
			
			//농장동 번호로 도사 조회
			List<ChickDisposalDto> chickDisposalDtoList = chickDisposalService.findAllChickDisposal();
			
			//응답 객체 생성
			Response<List<ChickDisposalDto>> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.READ_CHICK_DISPOSAL_LIST_SUCCESS);
			response.setMessage(ResponseMessage.READ_CHICK_DISPOSAL_LIST_SUCCESS);
			response.setData(chickDisposalDtoList);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<List<ChickDisposalDto>> response = new Response<>();
			response.setStatus(ResponseStatusCode.READ_CHICK_DISPOSAL_LIST_FAIL);
			response.setMessage(ResponseMessage.READ_CHICK_DISPOSAL_LIST_FAIL);
			response.setData(null);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* 도사 수 누적합 조회 */
	@Operation(summary = "도사 수 누적합 조회")
	@GetMapping("/list/total")
	public ResponseEntity<Response<Integer>> getTotalChickDisposalNumberByFarmSectionNo(@RequestParam("farmSectionNo") Long farmSectionNo) {
		try {
			
			Integer totalDisposal = chickDisposalService.getTotalChickDisposalNumberByFarmSectionNo(farmSectionNo);
			
			//응답 객체 생성
			Response<Integer> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.READ_CHICK_DISPOSAL_TOTAL_SUCCESS);
			response.setMessage(ResponseMessage.READ_CHICK_DISPOSAL_TOTAL_SUCCESS);
			response.setData(totalDisposal);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<Integer> response = new Response<>();
			response.setStatus(ResponseStatusCode.READ_CHICK_DISPOSAL_TOTAL_FAIL);
			response.setMessage(ResponseMessage.READ_CHICK_DISPOSAL_TOTAL_FAIL);
			response.setData(0);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

}
