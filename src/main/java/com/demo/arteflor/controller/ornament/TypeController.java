package com.demo.arteflor.controller.ornament;

import com.demo.arteflor.dto.ornament.TypeDto;
import com.demo.arteflor.model.ornament.Type;
import com.demo.arteflor.service.ornament.TypeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/type")
@ControllerAdvice
public class TypeController {

    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @PostMapping("/addType")
    public ResponseEntity<Type> addType(@RequestBody @Valid TypeDto typeDto){
        return ResponseEntity.ok(typeService.addType(typeDto));
    }
    @GetMapping("/getAllTypes")
    public ResponseEntity<List<TypeDto>> getAllTypes(){
        return ResponseEntity.ok(typeService.getAllTypes());
    }
}
