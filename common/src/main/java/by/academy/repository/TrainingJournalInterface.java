package by.academy.repository;

import by.academy.domain.TotalWeightOnDate;
import by.academy.domain.TrainingJournal;
import by.academy.domain.TrainingSessionsUsersResult;

import java.sql.Timestamp;
import java.util.List;

public interface TrainingJournalInterface extends CRUDRepository<Long, TrainingJournal>{
    List<TrainingSessionsUsersResult> trainingSessionsResult();

    List<TotalWeightOnDate> totalWeightOnDate(String S, Timestamp T);
}
