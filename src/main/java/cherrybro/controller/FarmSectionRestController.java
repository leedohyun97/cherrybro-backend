package cherrybro.controller;


import java.nio.charset.Charset;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import cherrybro.dto.FarmSectionDto;
import cherrybro.response.Response;
import cherrybro.response.ResponseMessage;
import cherrybro.response.ResponseStatusCode;
import cherrybro.service.FarmSectionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@CrossOrigin//리액트 API 호출 허용
@RestController//컨트롤러
@RequiredArgsConstructor
@RequestMapping("/farmSection")
public class FarmSectionRestController {
	
	private final FarmSectionService farmSectionService;
	
	/* 농장동 등록 */
	@Operation(summary = "농장동 등록")
	@PostMapping
	public ResponseEntity<Response<FarmSectionDto>> createFarmSection(@RequestBody FarmSectionDto farmSectionDto) {
		try {
			//응답 객체 생성
			Response<FarmSectionDto> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//저장
			FarmSectionDto saveFarmSectionDto = farmSectionService.saveFarmSection(farmSectionDto);
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.CREATED_FARM_SECTION_SUCCESS);
			response.setMessage(ResponseMessage.CREATED_FARM_SECTION_SUCCESS);
			response.setData(saveFarmSectionDto);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
		    Response<FarmSectionDto> response = new Response<>();
		    response.setStatus(ResponseStatusCode.CREATED_FARM_SECTION_FAIL);
		    response.setMessage(ResponseMessage.CREATED_FARM_SECTION_FAIL);
		    response.setData(null);
		    
			//반환할 응답Entity 생성 및 반환
		    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* 농장동 수정 */
	@Operation(summary = "농장동 수정")
	@PutMapping("/{farmSectionNo}")
	public ResponseEntity<Response<FarmSectionDto>> updateFarmSection(@PathVariable("farmSectionNo") Long farmSectionNo, @RequestBody FarmSectionDto farmSectionDto) {
		try {
			
			//패스와 Dto의 번호가 일치하지 않을 시 에러
			if (!farmSectionNo.equals(farmSectionDto.getFarmSectionNo())) {
			    throw new IllegalArgumentException("요청 경로와 본문 번호가 일치하지 않습니다.");
			}
			
			//farmSectionNo를 farmSectionDto에 설정
			farmSectionDto.setFarmSectionNo(farmSectionNo);
			
			//응답 객체 생성
			Response<FarmSectionDto> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//수정
			FarmSectionDto updateFarmSectionDto = farmSectionService.updateFarmSection(farmSectionDto);
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.UPDATE_FARM_SECTION_SUCCESS);
			response.setMessage(ResponseMessage.UPDATE_FARM_SECTION_SUCCESS);
			response.setData(updateFarmSectionDto);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
		    Response<FarmSectionDto> response = new Response<>();
		    response.setStatus(ResponseStatusCode.UPDATE_FARM_SECTION_FAIL);
		    response.setMessage(ResponseMessage.UPDATE_FARM_SECTION_FAIL);
		    response.setData(null);
		    
			//반환할 응답Entity 생성 및 반환
		    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* 농장동 삭제 */
	@Operation(summary = "농장동 삭제")
	@DeleteMapping("/{farmSectionNo}")
	public ResponseEntity<Response<FarmSectionDto>> deleteFarmSection(@PathVariable("farmSectionNo") Long farmSectionNo) {
		try {
			//삭제 실행
			FarmSectionDto deletedFarmSection = farmSectionService.deleteFarmSectionById(farmSectionNo);

			//응답 객체 생성
			Response<FarmSectionDto> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.DELETE_FARM_SECTION_SUCCESS);
			response.setMessage(ResponseMessage.DELETE_FARM_SECTION_SUCCESS);
			response.setData(deletedFarmSection);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<FarmSectionDto> response = new Response<>();
			response.setStatus(ResponseStatusCode.DELETE_FARM_SECTION_FAIL);
			response.setMessage(ResponseMessage.DELETE_FARM_SECTION_FAIL);
			response.setData(null);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* 농장동 조회 */
	@Operation(summary = "농장동 조회")
	@GetMapping("/{farmSectionNo}")
	public ResponseEntity<Response<FarmSectionDto>> getFarmSection(@PathVariable("farmSectionNo") Long farmSectionNo) {
		try {
			//농장동 번호로 농장동 조회
			FarmSectionDto farmSectionDto = farmSectionService.findFarmSectionById(farmSectionNo);
			
			//응답 객체 생성
			Response<FarmSectionDto> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.READ_FARM_SECTION_SUCCESS);
			response.setMessage(ResponseMessage.READ_FARM_SECTION_SUCCESS);
			response.setData(farmSectionDto);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<FarmSectionDto> response = new Response<>();
			response.setStatus(ResponseStatusCode.READ_FARM_SECTION_FAIL);
			response.setMessage(ResponseMessage.READ_FARM_SECTION_FAIL);
			response.setData(null);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/* 농장동 리스트 조회 */
	@Operation(summary = "농장동 목록 조회")
	@GetMapping("/list/all")
	public ResponseEntity<Response<List<FarmSectionDto>>> getAllFarmSection() {
		try {
			//농장동 리스트 조회
			List<FarmSectionDto> farmSectionDtoList = farmSectionService.findFarmSections();
			
			//응답 객체 생성
			Response<List<FarmSectionDto>> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.READ_FARM_SECTION_LIST_SUCCESS);
			response.setMessage(ResponseMessage.READ_FARM_SECTION_LIST_SUCCESS);
			response.setData(farmSectionDtoList);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<List<FarmSectionDto>> response = new Response<>();
			response.setStatus(ResponseStatusCode.READ_FARM_SECTION_LIST_FAIL);
			response.setMessage(ResponseMessage.READ_FARM_SECTION_LIST_FAIL);
			response.setData(null);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* 농장 번호로 농장동 리스트 조회 */
	@Operation(summary = "농장 번호로 농장동 목록 조회")
	@GetMapping("/list/by-farm")
	public ResponseEntity<Response<List<FarmSectionDto>>> getAllFarmSectionsByFarmNo(@RequestParam("farmNo") Long farmNo) {
		try {
			
			//농장 번호로 농장동 리스트 조회
			List<FarmSectionDto> farmSectionDtoDtoList = farmSectionService.findFarmSectionsByFarmNo(farmNo);
			
			//응답 객체 생성
			Response<List<FarmSectionDto>> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.READ_FARM_SECTION_LIST_SUCCESS);
			response.setMessage(ResponseMessage.READ_FARM_SECTION_LIST_SUCCESS);
			response.setData(farmSectionDtoDtoList);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);

		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<List<FarmSectionDto>> response = new Response<>();
			response.setStatus(ResponseStatusCode.READ_FARM_SECTION_LIST_FAIL);
			response.setMessage(ResponseMessage.READ_FARM_SECTION_LIST_FAIL);
			response.setData(null);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/* 농장동 리스트(페이징) 조회 */
	@Operation(summary = "농장동 목록(페이징) 조회")
	@GetMapping("/list/page")
	public ResponseEntity<Response<Page<FarmSectionDto>>> getAllFarmSectionPage(Pageable pageable) {
		try {
			
			//농장동 리스트 조회
			Page<FarmSectionDto> farmSectionDtoPage = farmSectionService.getAllFarmSectionPage(pageable);
			
			//응답 객체 생성
			Response<Page<FarmSectionDto>> response = new Response<>();
			
			//인코딩 타입 설정
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8")));
			
			//응답 객체 설정
			response.setStatus(ResponseStatusCode.READ_FARM_SECTION_LIST_SUCCESS);
			response.setMessage(ResponseMessage.READ_FARM_SECTION_LIST_SUCCESS);
			response.setData(farmSectionDtoPage);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			
			//에러 응답 객체 반환
			Response<Page<FarmSectionDto>> response = new Response<>();
			response.setStatus(ResponseStatusCode.READ_FARM_SECTION_LIST_FAIL);
			response.setMessage(ResponseMessage.READ_FARM_SECTION_LIST_FAIL);
			response.setData(null);
			
			//반환할 응답Entity 생성 및 반환
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
