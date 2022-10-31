package by.academy.repository.jdbctemplate;

import by.academy.domain.TrainingSessionsUsersResult;
import by.academy.repository.TrainingSessionsUsersResultColumn;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.academy.repository.TrainingSessionsUsersResultColumn.*;


@Component
public class TrainingSessionsResultMapper implements RowMapper<TrainingSessionsUsersResult> {
    @Override
    public TrainingSessionsUsersResult mapRow(ResultSet rs, int i) throws SQLException {

        TrainingSessionsUsersResult trainingSessionsUsersResult = new TrainingSessionsUsersResult();

        trainingSessionsUsersResult.setUserName(rs.getString(USER_NAME));
        trainingSessionsUsersResult.setSurName(rs.getString(SUR_NAME));
        trainingSessionsUsersResult.setTrainingData(rs.getTimestamp(TrainingSessionsUsersResultColumn.TRAINING_DATA));
        trainingSessionsUsersResult.setExerciseName(rs.getString(EXERCISE_NAME));
        trainingSessionsUsersResult.setSets(rs.getLong(TrainingSessionsUsersResultColumn.SETS));
        trainingSessionsUsersResult.setReps(rs.getLong(TrainingSessionsUsersResultColumn.REPS));
        trainingSessionsUsersResult.setWeight(rs.getLong(TrainingSessionsUsersResultColumn.WEIGHT));

        return trainingSessionsUsersResult;
    }
}