package by.academy.controller.springdata;

import by.academy.domain.dto.TopExerciseCountDTO;
import by.academy.domain.dto.TopTotalWeightResultDTO;
import by.academy.domain.dto.TrainingResultDTO;
import by.academy.repository.springdata.BusinessSpringDataRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/business")
public class BusinessController {

        private final BusinessSpringDataRepository businessSpringDataRepository;

        @Tag(name = "Endpoint for business", description = "Business operation")
        @Operation(summary = "Training session result", description = "Training session result")
        @GetMapping("/trainingSessionResult")
        public ResponseEntity<List<TrainingResultDTO>> trainingSessionEndPoint( @RequestParam("sur_name") @Parameter(
                description = "User surname") String surName) {


                return  new ResponseEntity<List<TrainingResultDTO>>(businessSpringDataRepository.trainingSessionResult(surName), HttpStatus.OK);

        }

        @Tag(name = "Endpoint for business", description = "Business operation")
        @Operation(summary = "Top 3 Total Weight result", description = "Top 3 Total Weight result")
        @GetMapping("/topTotalWeight")
        public ResponseEntity<List<TopTotalWeightResultDTO>> topTotalWeight() {


                return  new ResponseEntity<List<TopTotalWeightResultDTO>>(businessSpringDataRepository.topTotalWeightResultDTO(), HttpStatus.OK);

        }

        @Tag(name = "Endpoint for business", description = "Business operation")
        @Operation(summary = "The most popular exercises", description = "The most popular exercises")
        @GetMapping("/topExercises")
        public ResponseEntity<List<TopExerciseCountDTO>> topExercises() {

                return  new ResponseEntity<List<TopExerciseCountDTO>>(businessSpringDataRepository.topExerciseCountDTO(), HttpStatus.OK);

        }
}
