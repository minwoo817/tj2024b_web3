package example.day04과제.service;

import example.day04과제.model.dto.FixDto;
import example.day04과제.model.entity.FixEntity;
import example.day04과제.model.repository.FixRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FixService {
    private final FixRepository fixRepository;

    public FixDto fixSave(FixDto fixDto){
        FixEntity fixEntity = fixDto.fixEntity();
        FixEntity saveEntity = fixRepository.save(fixEntity);
        if(saveEntity.getId() > 0){
            return saveEntity.fixDto();
        }else{
            return null;
        }
    }

    public List<FixDto> fixFindAll(){
        List<FixEntity> fixEntityList = fixRepository.findAll();
        List<FixDto> fixDtoList = new ArrayList<>();
        for(int i = 0; i < fixEntityList.size(); i++){
            FixDto fixDto = fixEntityList.get(i).fixDto();
            fixDtoList.add(fixDto);
        }
        return fixDtoList;
    }

    public FixDto fixFindById(int id){
        Optional<FixEntity> optional = fixRepository.findById(id);
        if(optional.isPresent()){
            FixEntity fixEntity = optional.get();
            FixDto fixDto = fixEntity.fixDto();
            return fixDto;
        }
        return null;
    }

    public FixDto fixUpdate(FixDto fixDto){
        Optional<FixEntity> optional = fixRepository.findById(fixDto.getId());
        if(optional.isPresent()){
            FixEntity fixEntity = optional.get();
            fixEntity.setName(fixDto.getName());
            fixEntity.setDescription(fixDto.getDescription());
            fixEntity.setQuantity(fixDto.getQuantity());
            return fixEntity.fixDto();
        }
        return null;
    }

    public boolean fixDelete(int id){
        boolean result = fixRepository.existsById(id);
        if(result == true) {
            fixRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<FixDto> fixFindByPage(int page, int size){
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<FixEntity> fixEntityPage = fixRepository.findAll(pageRequest);
        List<FixDto> fixDtoList = new ArrayList<>();
        for(int i = 0; i < fixEntityPage.getContent().size(); i++){
            FixDto fixDto = fixEntityPage.getContent().get(i).fixDto();
            fixDtoList.add(fixDto);
        }
        return fixDtoList;
    }
}
