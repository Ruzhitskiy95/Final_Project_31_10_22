package by.academy.exeption.service;

import by.academy.domain.TotalWeightOnDate;
import by.academy.domain.TrainingJournal;
import by.academy.domain.TrainingSessionsUsersResult;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface TrainingJournalService {

    List<TrainingJournal> findAll();
    List<TrainingJournal> findAll(int limit, int offset);

    TrainingJournal findById(Long id);

    Optional<TrainingJournal> findOne(Long id);

    TrainingJournal create(TrainingJournal object);

    Long delete(Long id);

    TrainingJournal update(TrainingJournal object);

    List<TrainingSessionsUsersResult> trainingSessionsResult();

    List<TotalWeightOnDate> totalWeightOnDate(String S, Timestamp T);
}
