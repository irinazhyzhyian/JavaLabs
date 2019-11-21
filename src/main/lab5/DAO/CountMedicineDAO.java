package main.lab5.DAO;

import main.lab5.model.CountMedicine;
import main.lab5.model.Medicine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountMedicineDAO implements DAO<CountMedicine, Integer> {

    /**
     * SQL queries for count_medicines table.
     */
    enum CountMedicineSQL {
        GET("SELECT * FROM count_medicines  WHERE count_medicines.count >= (?)"),
        INSERT("INSERT INTO count_medicines (id, medicine_id, count) VALUES ((?), (?), (?))"),
        DELETE("DELETE FROM count_medicines WHERE id = (?) RETURNING id"),
        UPDATE("UPDATE count_medicines SET count = (?) WHERE id = (?) RETURNING id");

        String QUERY;

        CountMedicineSQL(String QUERY) {
            this.QUERY = QUERY;
        }
    }

    /**
     * Connection to database
     */
      private final Connection connection;

    /**
     *  Init DB connection
     *
     * @param connection of DB
     */
    public CountMedicineDAO(final Connection connection) {
        this.connection= connection;
    }

    /**
     * Create CountMedicine in DB
     *
     * @param countMedicine foe create
     * @return false if item already exist, true if creating success
     * @throws SQLException
     */
    @Override
    public boolean create(CountMedicine countMedicine) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(CountMedicineSQL.INSERT.QUERY);
        statement.setInt(1, countMedicine.getId());
        statement.setObject(2, countMedicine.getMedicine());
        statement.setInt(3, countMedicine.getCount());
        return statement.executeQuery().next();
    }

    /**
     * Select CountMedicine by id
     *
     * @param count for select
     * @return return valid entity if she exist. If entity does not exist return empty object with id = -1.
     * @throws SQLException
     */
    @Override
    public CountMedicine read(Integer count) throws SQLException {
        CountMedicine result = new CountMedicine();
        result.setId(-1);
        PreparedStatement statement = connection.prepareStatement(CountMedicineSQL.GET.QUERY);
        statement.setInt(1,count);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
            result.setId(resultSet.getInt("id"));
            result.setMedicine(resultSet.getObject("medicine", Medicine.class));
            result.setCount(resultSet.getInt("count"));
        }
        return result;
    }

    /**
     * Update CountMedicine by id
     *
     * @param countMedicine new state
     * @return true if success, false if fail
     * @throws SQLException
     */
    @Override
    public boolean update(CountMedicine countMedicine) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(CountMedicineSQL.UPDATE.QUERY);
        statement.setInt(1, countMedicine.getCount());
        statement.setInt(2, countMedicine.getId());

        return statement.executeQuery().next();
    }

    /**
     * Delete CountMedicine dy id
     *
     * @param countMedicine for delete
     * @return true if success, false if item not exist
     * @throws SQLException
     */
    @Override
    public boolean delete(CountMedicine countMedicine) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(CountMedicineSQL.DELETE.QUERY);
        statement.setInt(1, countMedicine.getId());

        return statement.executeQuery().next();
    }
}
