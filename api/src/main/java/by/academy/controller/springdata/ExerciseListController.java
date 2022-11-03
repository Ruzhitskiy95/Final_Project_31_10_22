package by.academy.controller.springdata;

import by.academy.repository.springdata.ExerciseListSpringDataRepository;
import by.academy.repository.springdata.RolesSpringDataRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/exerciselist")
public class ExerciseListController {

    private final ExerciseListSpringDataRepository repository;

    @Tag(name = "Endpoint for exerciseList", description = "Cache for exerciseList")
    @Operation(summary = "Cache for exerciseList", description = "Cache for exerciseList")
    @GetMapping
    public ResponseEntity<Object> findExerciseListWithCache() {

        System.out.println("-------------Start exercise_list controller ---------------");

        return new ResponseEntity<>(
                Collections.singletonMap("result", repository.findAllCustom()),
                HttpStatus.OK
        );
    }
}
