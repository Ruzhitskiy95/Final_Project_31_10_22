package by.academy.repository.jdbctemplate;

import by.academy.domain.TotalWeightOnDate;
import by.academy.repository.TotalWeightOnDateColumn;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TotalWeightOnDateMapper implements RowMapper<TotalWeightOnDate> {

    @Override
    public TotalWeightOnDate mapRow(ResultSet rs, int i) throws SQLException {

        TotalWeightOnDate totalWeightOnDate = new TotalWeightOnDate();

        totalWeightOnDate.setUserName(rs.getString(TotalWeightOnDateColumn.USER_NAME));
        totalWeightOnDate.setSurName(rs.getString(TotalWeightOnDateColumn.SUR_NAME));
        totalWeightOnDate.setTrainingData(rs.getTimestamp(TotalWeightOnDateColumn.TRAINING_DATA));
        totalWeightOnDate.setTotalWeight(rs.getLong(TotalWeightOnDateColumn.TOTAL_WEIGHT));

        return totalWeightOnDate;
    }
}
