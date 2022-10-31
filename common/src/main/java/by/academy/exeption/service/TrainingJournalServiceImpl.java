package by.academy.exeption.service;

import by.academy.domain.TotalWeightOnDate;
import by.academy.domain.TrainingJournal;
import by.academy.domain.TrainingSessionsUsersResult;
import by.academy.repository.TrainingJournalInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class TrainingJournalServiceImpl implements TrainingJournalService {

    private final TrainingJournalInterface trainingJouralInterface;

    @Override
    public List<TrainingJournal> findAll() {
        return trainingJouralInterface.findAll();
    }

    @Override
    public List<TrainingJournal> findAll(int limit, int offset) {
        return trainingJouralInterface.findAll();
    }

    @Override
    public TrainingJournal findById(Long id) {
        return trainingJouralInterface.findById(id);
    }

    @Override
    public Optional<TrainingJournal> findOne(Long id) {
        return trainingJouralInterface.findOne(id);
    }

    @Override
    public TrainingJournal create(TrainingJournal object) {
        return trainingJouralInterface.create(object);
    }

    @Override
    public Long delete(Long id) {
        return trainingJouralInterface.delete(id);
    }

    @Override
    public TrainingJournal update(TrainingJournal object) {
        return trainingJouralInterface.update(object);
    }

    @Override
    public List<TrainingSessionsUsersResult> trainingSessionsResult() {
        return trainingJouralInterface.trainingSessionsResult();
    }

    @Override
    public List<TotalWeightOnDate> totalWeightOnDate (String S, Timestamp T) {
        return trainingJouralInterface.totalWeightOnDate(S,T);
    }
}