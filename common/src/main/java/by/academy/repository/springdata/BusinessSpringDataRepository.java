package by.academy.repository.springdata;

import by.academy.domain.dto.TopExerciseCountDTO;
import by.academy.domain.dto.TopTotalWeightResultDTO;
import by.academy.domain.dto.TrainingResultDTO;
import by.academy.domain.entity.HibernateUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessSpringDataRepository extends
              JpaRepository<HibernateUser, Long>

{

    @Query(value = "select u.user_name as userName, u.sur_name as surName, " +
            "tj.training_data as trainingData, el.exercise_name as exerciseName," +
            " tj.sets as sets, tj.reps as reps, tj.weight as weight, tj.total_weight as totalWeight " +
            "from training_records_schema.users as u inner join training_records_schema.training_journal as tj on " +
                    "u.id = tj.user_id " +
                    "inner join training_records_schema.exercise_list as el on el.id = tj.exercise_id " +
            "where u.sur_name = (:sur_name) ",nativeQuery = true)
    List<TrainingResultDTO> trainingSessionResult(@Param("sur_name") String surName);


//    @Query(value = "SELECT c.year AS yearComment, COUNT(c.*) AS totalComment "
//            + "FROM comment AS c GROUP BY c.year ORDER BY c.year DESC", nativeQuery = true)
//    List<ICommentCount> countTotalCommentsByYearNative();

//    @Query(value = "select * from training_records_schema.training_journal order by total_weight desc limit 5",nativeQuery = true)
//    List<HibernateTrainingJournal> trainingSessionResult();

@Query(value = "select u.user_name as userName, u.sur_name as surName, sum(tj.total_weight) as twSum " +
        "from training_records_schema.users as u " +
        "inner join training_records_schema.training_journal as tj " +
        "on u.id = tj.user_id " +
        "group by u.user_name, u.sur_name order by twSum desc limit 3", nativeQuery = true)
List<TopTotalWeightResultDTO> topTotalWeightResultDTO();

    @Query(value = "select tj.exercise_id as exerciseId, el.exercise_name as exerciseName, count(tj.exercise_id) as exCount " +
            "from training_records_schema.exercise_list as el " +
            "inner join training_records_schema.training_journal as tj " +
            "on el.id = tj.exercise_id " +
            "group by tj.exercise_id, el.exercise_name order by exCount desc limit 5", nativeQuery = true)
    List<TopExerciseCountDTO> topExerciseCountDTO();
}