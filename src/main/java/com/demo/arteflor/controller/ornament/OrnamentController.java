package com.demo.arteflor.controller.ornament;

import com.demo.arteflor.dto.ornament.OrnamentDto;
import com.demo.arteflor.model.ornament.Ornament;
import com.demo.arteflor.service.ornament.OrnamentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/ornament")
@ControllerAdvice
public class OrnamentController {
    private final OrnamentService ornamentService;

    public OrnamentController(OrnamentService ornamentService) {
        this.ornamentService = ornamentService;
    }

    @PostMapping("/addOrnament")
    public ResponseEntity<Ornament> addOrnament(@RequestBody @Valid OrnamentDto ornamentDto){
        return ResponseEntity.ok(ornamentService.addOrnament(ornamentDto));
    }

    @GetMapping("/getAllOrnaments")
    public ResponseEntity<List<OrnamentDto>> getAllOrnaments(){
        return ResponseEntity.ok(ornamentService.getAllOrnaments());
    }
    @GetMapping("/getOrnamentByName")
    public ResponseEntity<List<OrnamentDto>> getOrnamentByName(@RequestParam String name){
        return ResponseEntity.of(ornamentService.findByName(name));
    }
    @GetMapping("/ornament-category-search")
    public ResponseEntity<List<OrnamentDto>> getOrnamentByCategory(@RequestParam String categoryTitle){
        return ResponseEntity.ok(ornamentService.findByCategoryTitle(categoryTitle));
    }
    @GetMapping("/ornament-type-search")
    public ResponseEntity<List<OrnamentDto>> getOrnamentByType(@RequestParam String typeTitle){
        return ResponseEntity.ok(ornamentService.findByTypeTitle(typeTitle));
    }
    @GetMapping("/ornament-model-search")
    public ResponseEntity<List<OrnamentDto>> getOrnamentByModel(@RequestParam String model){
        return ResponseEntity.of(ornamentService.findByName(model));
    }
}
