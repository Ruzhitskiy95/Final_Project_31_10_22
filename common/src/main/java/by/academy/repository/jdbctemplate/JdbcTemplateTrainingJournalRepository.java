package by.academy.repository.jdbctemplate;

import by.academy.domain.TotalWeightOnDate;
import by.academy.domain.TrainingJournal;
import by.academy.domain.TrainingSessionsUsersResult;
import by.academy.repository.TrainingJournalInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor

public class JdbcTemplateTrainingJournalRepository implements TrainingJournalInterface {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final TrainingJournalRowMapper trainingJournalRowMapper;

    private final TrainingSessionsResultMapper trainingSesionsResultMapper;

    private final TotalWeightOnDateMapper totalWeightOnDateMapper ;


    @Override
    public TrainingJournal findById(Long id) {

        return jdbcTemplate.queryForObject("select * from training_records_schema.training_journal where id = " + id, trainingJournalRowMapper);
    }

    @Override
    public Optional<TrainingJournal> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public List<TrainingJournal> findAll() {
        return findAll(DEFAULT_FIND_ALL_LIMIT,DEFAULT_FIND_ALL_OFFSET);

    }

    @Override
    public List<TrainingJournal> findAll(int limit, int offset) {
        return  jdbcTemplate.query("select * from training_records_schema.training_journal" +
                " order by id limit "  + limit + " offset " + offset, trainingJournalRowMapper);
    }

    @Override
    public TrainingJournal create(TrainingJournal object) {

        final String insertQuery =
                "insert into training_records_schema.training_journal (training_data, user_id, exercise_id, sets, reps, weight, is_deleted) " +
                        " values (:trainingData, :userId, :exerciseId, :sets, :reps, :weight, :isDeleted);";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("trainingData", object.getTrainingData());
        mapSqlParameterSource.addValue("userId", object.getUserId());
        mapSqlParameterSource.addValue("exerciseId", object.getExerciseId());
        mapSqlParameterSource.addValue("sets", object.getSets());
        mapSqlParameterSource.addValue("reps", object.getReps());
        mapSqlParameterSource.addValue("weight", object.getWeight());
        mapSqlParameterSource.addValue("isDeleted", object.getIsDeleted());

        namedParameterJdbcTemplate.update(insertQuery, mapSqlParameterSource);

        Long lastInsertId = namedParameterJdbcTemplate.query("SELECT currval('training_records_schema.training_journal_id_seq') as last_id",
                resultSet -> {
                    resultSet.next();
                    return resultSet.getLong("last_id");
                });

        return findById(lastInsertId);
    }

    @Override
    public TrainingJournal update(TrainingJournal object) {
        final String updateQuery =

                "update training_records_schema.training_journal  set  training_data = :trainingData, user_id = :userId, exercise_id = :exerciseId, sets = :sets, reps = :reps, weight = :weight, is_deleted = :isDeleted) ";


        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("trainingData", object.getTrainingData());
        mapSqlParameterSource.addValue("userId", object.getUserId());
        mapSqlParameterSource.addValue("exerciseId", object.getExerciseId());
        mapSqlParameterSource.addValue("sets", object.getSets());
        mapSqlParameterSource.addValue("reps", object.getReps());
        mapSqlParameterSource.addValue("weight", object.getWeight());
        mapSqlParameterSource.addValue("isDeleted", object.getIsDeleted());

        namedParameterJdbcTemplate.update(updateQuery, mapSqlParameterSource);

        return findById(object.getId());
    }

    @Override
    public Long delete(Long id) {
        jdbcTemplate.update("delete from training_records_schema.training_journal" +
                " where id = " + id);
        return id;
    }

    @Override
    public List<TrainingSessionsUsersResult> trainingSessionsResult() {
        return jdbcTemplate.query("select user_name, sur_name, training_data, exercise_name, sets, reps, weight  " +
                "from training_records_schema.users inner join training_records_schema.training_journal on " +
                "users.id = training_journal.user_id " +
                "inner join training_records_schema.exercise_list el on el.id = training_journal.exercise_id",
                trainingSesionsResultMapper);
    }

    @Override
    public List<TotalWeightOnDate> totalWeightOnDate(String S, Timestamp T) {
        return jdbcTemplate.query("select user_name, sur_name, training_data, sum(total_weight) as sum_total_weight " +
                "from training_records_schema.users inner join training_records_schema.training_journal on " +
                "users.id = training_journal.user_id " +
                "group by user_name,users.sur_name, training_data having " +
                "user_name like '" + S + "' and training_data = '" + T + "'", totalWeightOnDateMapper);
    }
}
