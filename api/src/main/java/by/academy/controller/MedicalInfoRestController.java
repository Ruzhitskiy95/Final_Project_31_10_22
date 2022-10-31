package by.academy.controller;

import by.academy.repository.hibernate.HibernateMedicalInfoInterface;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Collection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/medical")
public class MedicalInfoRestController {

    private final HibernateMedicalInfoInterface hibernateMedicalInfoInterface;

    @GetMapping
    public ResponseEntity<Object> findAllMedicalInfo(){
        return new ResponseEntity<>(Collections.singletonMap("result",
                hibernateMedicalInfoInterface.findAll()), HttpStatus.OK);
    }
}
