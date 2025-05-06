package cherrybro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cherrybro.dto.FarmDto;
import cherrybro.entitiy.Farm;
import cherrybro.repository.FarmRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {
	
	private final FarmRepository farmRepository;
	
	/* 농장 저장 */
	@Override
	public FarmDto saveFarm(FarmDto farmDto) {
		try {
			
			//DTO -> Entity 변환
			Farm farm = Farm.toEntity(farmDto);
			
			//Entity객체 저장(농장 저장)
			Farm saveFarm = farmRepository.save(farm);
			
			//Entity -> DTO 변환
			return FarmDto.toDto(saveFarm);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
	        throw new RuntimeException("농장 저장 중 오류 발생", e);
		}
	}
	
	/* 농장 수정 */
	@Override
	public FarmDto updateFarm(FarmDto farmDto) {
		try {
			
			//수정할 농장 조회
			Farm farm = farmRepository.findByFarmNo(farmDto.getFarmNo())
					.orElseThrow(() -> new RuntimeException("해당 농장을 찾을 수 없습니다."));
			
			//농장 이름 수정
			farm.setFarmName(farmDto.getFarmName());
			
			Farm updateFarm = farmRepository.save(farm);
			
			return FarmDto.toDto(updateFarm);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
	        throw new RuntimeException("농장 수정 중 오류 발생", e);
		}
	}
	
	@Override
	public FarmDto deleteFarmById(Long farmNo) {
		try {
			//삭제할 농장 조회
			Farm farm = farmRepository.findByFarmNo(farmNo)
					.orElseThrow(() -> new RuntimeException("해당 농장을 찾을 수 없습니다."));

			//삭제 수행
			farmRepository.delete(farm);

			//삭제 정보 DTO 변환 후 반환
			return FarmDto.toDto(farm);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("농장 삭제 중 오류 발생", e);
		}
	}

	/* 농장 조회 */
	@Override
	public FarmDto findFarmById(Long farmNo) {
		try {
			//농장 고유 번호로 농장 조회
			Farm findFarm = farmRepository.findByFarmNo(farmNo)
					//NULL값 발생 시 예외 발생
					.orElseThrow(() -> new RuntimeException("해당 농장을 찾을 수 없습니다."));
			
			//Entity -> DTO 변환 후 반환
			return FarmDto.toDto(findFarm);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
			throw new RuntimeException("농장 조회 중 오류 발생", e);
		}
	}
	
	/* 농장 리스트 조회 */
	@Override
	public List<FarmDto> findFarms() {
		try {
			
			//농장 리스트 조회
			List<Farm> farmList = farmRepository.findAll();
			
			//Stream을 사용해 Entity -> DTO로 변환
			return farmList.stream()
						.map(FarmDto::toDto)
						.toList();
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
			throw new RuntimeException("농장 리스트 조회 중 오류 발생", e);
		}
	}

	@Override
	public FarmDto findFarmByUsersNo(Long usersNo) {
		try {
			//사용자 고유 번호로 농장 조회
			Farm findFarm = farmRepository.findByUsersNo(usersNo)
					//NULL값 발생 시 예외 발생
					.orElseThrow(() -> new RuntimeException("해당 농장을 찾을 수 없습니다."));
			
			//Entity -> DTO 변환 후 반환
			return FarmDto.toDto(findFarm);
			
		} catch (Exception e) {
			//에러 로그 출력
			e.printStackTrace();
			//에러 메시지 전달
			throw new RuntimeException("농장 조회 중 오류 발생", e);
		}
	}
	
}
