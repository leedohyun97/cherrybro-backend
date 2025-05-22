package cherrybro.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import cherrybro.dto.FarmSectionDto;
import cherrybro.entitiy.FarmSection;
import cherrybro.repository.FarmSectionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FarmSectionServiceImpl implements FarmSectionService {
	
	private final FarmSectionRepository farmSectionRepository;
	
	/* 농장동 저장 */
	@Override
	public FarmSectionDto saveFarmSection(FarmSectionDto farmSectionDto) {
		try {
			
			//DTO -> Entity 변환
			FarmSection farmSection = FarmSection.toEntity(farmSectionDto);
			
			//Entity객체 저장(농장 저장)
			FarmSection saveFarmSection = farmSectionRepository.save(farmSection);
			
			//Entity -> DTO 변환
			return FarmSectionDto.toDto(saveFarmSection);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
	        throw new RuntimeException("농장 저장 중 오류 발생", e);
		}
	}
	
	/* 농장동 수정 */
	@Override
	public FarmSectionDto updateFarmSection(FarmSectionDto farmSectionDto) {
	    try {
	    	//수정할 농장동 조회
	        FarmSection farmSection = farmSectionRepository.findByFarmSectionNo(farmSectionDto.getFarmSectionNo())
	                .orElseThrow(() -> new RuntimeException("해당 농장동을 찾을 수 없습니다."));
	        
	        //농장동 이름 수정
	        farmSection.setFarmSectionName(farmSectionDto.getFarmSectionName());
	        
	        //수정 실행
	        FarmSection updatefarmSection = farmSectionRepository.save(farmSection);
	        
	        //DTO 변환 후 반환
	        return FarmSectionDto.toDto(updatefarmSection);
	        
	    } catch (Exception e) {
			//에러 로그 출력
	        e.printStackTrace();
			//에러 메시지 전달
	        throw new RuntimeException("농장동 수정 중 오류 발생", e);
	    }
	}
	
	/* 농장동 삭제 */
	@Override
	public FarmSectionDto deleteFarmSectionById(Long farmSectionNo) {
	    try {
	    	//삭제할 농장동 조회
	        FarmSection farmSection = farmSectionRepository.findByFarmSectionNo(farmSectionNo)
	                .orElseThrow(() -> new RuntimeException("해당 농장동을 찾을 수 없습니다."));
	        
	        //삭제 실행
	        farmSectionRepository.delete(farmSection);
	        
	        //삭제한 농장동 DTO 변환 후 반환
	        return FarmSectionDto.toDto(farmSection);
	        
	    } catch (Exception e) {
			//에러 로그 출력
	        e.printStackTrace();
			//에러 메시지 전달
	        throw new RuntimeException("농장동 삭제 중 오류 발생", e);
	    }
	}

	
	/* 농장동 조회 */
	@Override
	public FarmSectionDto findFarmSectionById(Long farmSectionNo) {
		try {
			//농장 고유 번호로 농장 조회
			FarmSection findFarmSection = farmSectionRepository.findByFarmSectionNo(farmSectionNo)
					//NULL값 발생 시 예외 발생
					.orElseThrow(() -> new RuntimeException("해당 농장동을 찾을 수 없습니다."));
			
			//Entity -> DTO 변환 후 반환
			return FarmSectionDto.toDto(findFarmSection);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
			throw new RuntimeException("농장동 조회 중 오류 발생", e);
		}
	}
	
	/* 농장동 리스트 조회 */
	@Override
	public List<FarmSectionDto> findFarmSections() {
		try {
			
			//농장동 리스트 조회
			List<FarmSection> farmSections = farmSectionRepository.findAll();
			
			//Stream을 사용해 Entity -> DTO로 변환
			return farmSections.stream()
						.map(FarmSectionDto::toDto)
						.toList();
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
			throw new RuntimeException("농장동 리스트 조회 중 오류 발생", e);
		}
	}
	
	/* 농장 고유 번호로 농장동 리스트 조회 */
	@Override
	public List<FarmSectionDto> findFarmSectionsByFarmNo(Long farmNo) {
		try {
			//농장 고유 번호로 농장동 리스트 조회
			List<FarmSection> farmSections = farmSectionRepository.findAllByFarm_FarmNo(farmNo);
			
			//Stream을 사용해 Entity -> DTO 변환 후 반환
			return farmSections.stream()
					.map(FarmSectionDto::toDto)
					.toList();
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
			throw new RuntimeException("농장 고유 번호로 농장동 리스트 조회 중 오류 발생", e);
		}
	}
	
	//농장동 페이징
	@Override
	public Page<FarmSectionDto> getAllFarmSectionPage(Pageable pageable) {
		try {
			
			Page<FarmSectionDto> farmSectionDto = farmSectionRepository.findAll(pageable).map(FarmSectionDto::toDto);

			return farmSectionDto;
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
			throw new RuntimeException("농장동 리스트(페이징) 조회 중 오류 발생", e);
		}
	}
	
}
