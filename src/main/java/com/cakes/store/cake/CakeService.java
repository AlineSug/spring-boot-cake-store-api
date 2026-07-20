package com.cakes.store.cake;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CakeService {

    private final CakeRepository cakeRepository;
    private  final cakeMapper cakeMapper;

    public CakeDTO createCake(CakeDTO dto) {
        Cake cake =  cakeMapper.toEntity(dto);
        cakeRepository.save(cake);
        return cakeMapper.toDTO(cake);
    }

    public Page<CakeDTO> getAllCakes(Pageable page) {
        return cakeRepository
                .findAll(page)
                .map(cakeMapper::toDTO);
    }


    public CakeDTO getCakeById(Long id) {
        Cake cake = cakeRepository.findById(id).orElseThrow(()-> new EntityNotFoundException());
        return cakeMapper.toDTO(cake);
    }

    public CakeDTO updateCakeInfo(Long id, CakeDTO dto){
        Cake cake = cakeMapper.toEntity(dto);
        cake.setId(id);
        cake = cakeRepository.save(cake);
        return cakeMapper.toDTO(cake);
    }

    public void delete(Long id){
        cakeRepository.deleteById(id);
    }
}
