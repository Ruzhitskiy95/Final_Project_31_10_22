package by.academy.repository.springdata;

import by.academy.domain.entity.HibernateTrainingJournal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

public interface TrainingJournalSpringDataRepository extends
        CrudRepository<HibernateTrainingJournal, Long>,
        JpaRepository<HibernateTrainingJournal, Long>,
        PagingAndSortingRepository<HibernateTrainingJournal, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "insert into training_records_schema.training_journal "+
            "(training_data, user_id, exercise_id, sets, reps, weight) values (:training_data, " +
            ":user_id, " +
            ":exercise_id, :sets, :reps, :weight)", nativeQuery = true)

    void createTrainingJournalSuccess(@Param("training_data") Timestamp training_data,
                                      @Param("user_id") Long user_id,
                                      @Param("exercise_id") Long exercise_id,
                                      @Param("sets") Long sets,
                                      @Param("reps") Long reps,
                                      @Param("weight") Long weight);
}
