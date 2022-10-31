package by.academy.repository.jdbctemplate;

import by.academy.domain.TrainingJournal;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.academy.repository.TrainingJournalTableColumn.*;
import static by.academy.repository.colums.UserTableColumn.MYDELETED;


@Component
public class TrainingJournalRowMapper implements RowMapper<TrainingJournal> {
    @Override
    public TrainingJournal mapRow(ResultSet rs, int i) throws SQLException {
        TrainingJournal trainingJournal = new TrainingJournal();

        trainingJournal.setId(rs.getLong(TRAINING_JOURNAL_ID));
        trainingJournal.setTrainingData(rs.getTimestamp(TRAINING_DATA));
        trainingJournal.setUserId(rs.getLong(USER_ID));
        trainingJournal.setExerciseId(rs.getLong(EXERCISE_ID));
        trainingJournal.setSets(rs.getLong(SETS));
        trainingJournal.setReps(rs.getLong(REPS));
        trainingJournal.setWeight(rs.getLong(WEIGHT));
        trainingJournal.setIsDeleted(rs.getBoolean(MYDELETED));
        trainingJournal.setIsDeleted(rs.getBoolean(MYDELETED));
        trainingJournal.setTotalWeight(rs.getInt(TOTAL_WEIGHT));

        return trainingJournal;
    }
}
