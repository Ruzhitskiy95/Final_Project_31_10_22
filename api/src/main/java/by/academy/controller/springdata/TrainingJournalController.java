package by.academy.controller.springdata;

import by.academy.controller.requests.TrainingJournalCreateRequest;
import by.academy.domain.entity.HibernateTrainingJournal;
import by.academy.repository.springdata.TrainingJournalSpringDataRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/trainingJournal")
public class TrainingJournalController {

    private final TrainingJournalSpringDataRepository trainingJournalSpringDataRepository;

    @Tag(name = "Endpoint for training_journal", description = "CRUD operation for training journal")
    @Operation(summary = "Select all training sessions", description = "Select all training sessions")
    @GetMapping("/findAll")
    public ResponseEntity<Object> trainingJournalFindAllEndpoint() {

        return new ResponseEntity<>(Collections.singletonMap("result",
                trainingJournalSpringDataRepository.findAll()), HttpStatus.OK);
    }
    @Tag(name = "Endpoint for training_journal", description = "CRUD operation for training_journal")
    @Operation(summary = "Select training by id", description = "Select training by id")
    @GetMapping("/findById")
    public ResponseEntity<Object> trainingFindByIdEndpoint(
            @RequestParam("id") @Parameter(
                    description = "Training id") Long userId)
    {

        return new ResponseEntity<>(Collections.singletonMap("result",
                trainingJournalSpringDataRepository.findById(userId)), HttpStatus.OK);
    }

    @Tag(name = "Endpoint for training_journal", description = "CRUD operation for training_journal")
    @Operation(summary = "Create new training", description = "Create new training")
    @PostMapping("/createTrainingJournal")
    @Transactional
    public ResponseEntity<Object> createTrainingJournal(

            @RequestParam("training_data") @Parameter(
                    description = "Training Data") Timestamp trainingData,
            @RequestParam("user_id") @Parameter(
                    description = "User id") Long userId,
            @RequestParam("exercise_id") @Parameter(
                    description = "Exercise id") Long exerciseId,
            @RequestParam("sets") @Parameter(
                    description = "Sets") Long sets,
            @RequestParam("reps") @Parameter(
                    description = "Reps") Long reps,
            @RequestParam("weight") @Parameter(
                    description = "Weight") Long weight)
    {

//        Timestamp modificationDate = new Timestamp(new Date().getTime());
//                HibernateTrainingJournal hibernateTrainingJournal = trainingJournalSpringDataRepository.save(trainingJournal);

//        trainingJournalSpringDataRepository.findById(1L).get().getId());
        trainingJournalSpringDataRepository.createTrainingJournalSuccess(
                trainingData, userId, exerciseId, sets, reps, weight);

        Map<String, Object> model = new HashMap<>();
        model.put("training_data", trainingData);
        model.put("user_id", userId);
        model.put("exercise_id", exerciseId);
        model.put("sets", sets);
        model.put("reps", reps);
        model.put("weight", weight);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Tag(name = "Endpoint for training_journal", description = "CRUD operation for training_journal")
    @Operation(summary = "Update training", description = "Update training")
    @PostMapping("/updateTrainingJournal")
    @Transactional
    public ResponseEntity<Object> updateTrainingJournal(

            @RequestParam("training_data") @Parameter(
                    description = "Training Data") Timestamp trainingData,
            @RequestParam("user_id") @Parameter(
                    description = "User id") Long userId,
            @RequestParam("exercise_id") @Parameter(
                    description = "Exercise id") Long exerciseId,
            @RequestParam("sets") @Parameter(
                    description = "Sets") Long sets,
            @RequestParam("reps") @Parameter(
                    description = "Reps") Long reps,
            @RequestParam("weight") @Parameter(
                    description = "Weight") Long weight,
            @RequestParam("id") @Parameter(
                    description = "id") Long id
            )
    {

        trainingJournalSpringDataRepository.updateTrainingJournalSuccess(
                trainingData, userId, exerciseId, sets, reps, weight, id);

        Map<String, Object> model = new HashMap<>();
        model.put("training_data", trainingData);
        model.put("user_id", userId);
        model.put("exercise_id", exerciseId);
        model.put("sets", sets);
        model.put("reps", reps);
        model.put("weight", weight);
        model.put("id", id);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }
//          HARD DELETE
    @Tag(name = "Endpoint for training_journal", description = "CRUD operation for training_journal")
    @Operation(summary = "HARD Delete training by id", description = "HARD Delete training by id")
    @GetMapping("/HardDelete")
    public String  trainingJournalDeleteByIdEndpoint(
            @RequestParam("id") @Parameter(
                    description = "Training id") Long userId)
    {
        trainingJournalSpringDataRepository.deleteById(userId);
        return "Training has been deleted";
    }

    //          SOFT DELETE
    @Tag(name = "Endpoint for training_journal", description = "CRUD operation for training_journal")
    @Operation(summary = "SOFT Delete training by id", description = "SOFT Delete training by id")
    @PostMapping("/softDelete")
    @Transactional
    public ResponseEntity<Object> deleteTrainingJournal(

            @RequestParam("id") @Parameter(
                    description = "id") Long id
    )
    {

        trainingJournalSpringDataRepository.deleteTrainingJournal(id);

        Map<String, Object> model = new HashMap<>();

        model.put("id", id);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

}

