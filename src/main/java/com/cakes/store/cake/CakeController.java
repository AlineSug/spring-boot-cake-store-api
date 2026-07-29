package com.cakes.store.cake;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cakes")
@AllArgsConstructor
@SecurityRequirement(name = "bearer-key")

public class CakeController {

    private final CakeService cakeService;

    @PostMapping
    public ResponseEntity<CakeDTO> registerCake(@RequestBody @Valid CakeDTO dto ,UriComponentsBuilder uriBuilder ){
        CakeDTO cakeDTO = cakeService.createCake(dto);
        URI endereco = uriBuilder.path("/pastels/{id}").buildAndExpand(cakeDTO.getId()).toUri();
        return ResponseEntity.ok().body(cakeDTO);
    }

    @GetMapping
    public ResponseEntity<Page<CakeDTO>> findAllCakes(@PageableDefault(size = 10)Pageable page){
        Page<CakeDTO> cakes = cakeService.getAllCakes(page);
        return ResponseEntity.ok(cakes);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CakeDTO> searchById(@PathVariable Long id){
        CakeDTO cakeDTO = cakeService.getCakeById(id);
        return ResponseEntity.ok(cakeDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CakeDTO> EditCake(@PathVariable Long id ,@RequestBody @NotNull CakeDTO dto){
        CakeDTO cakeUpdated = cakeService.updateCakeInfo(id, dto);
        return ResponseEntity.ok(cakeUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCake(@PathVariable @NotNull Long id){
        cakeService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
