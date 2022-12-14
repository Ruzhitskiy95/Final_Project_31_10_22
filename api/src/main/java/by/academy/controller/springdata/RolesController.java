package by.academy.controller.springdata;

import by.academy.repository.springdata.RolesSpringDataRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/roles")
public class RolesController {

    private final RolesSpringDataRepository repository;

    @Tag(name = "Endpoint for roles", description = "Cache for roles")
    @Operation(summary = "Cache for roles", description = "Cache for roles")
    @GetMapping
    public ResponseEntity<Object> findAllRolesWithCache() {

        System.out.println("-------------Start roles controller ---------------");

        return new ResponseEntity<>(
                Collections.singletonMap("result", repository.findAllCustom()),
                HttpStatus.OK
        );
    }


}
