package main.lab5.DAO;

import main.lab5.model.CountMedicine;
import main.lab5.model.Medicine;
import main.lab5.model.Pharmacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CountMedicineDAO implements DAO<CountMedicine, Integer> {

    /**
     * SQL queries for count_medicines table.
     */
    enum CountMedicineSQL {
        GET("SELECT * FROM count_medicines  WHERE count_medicines.id = (?)"),
        INSERT("INSERT INTO count_medicines (id, medicine_id, count, pharmacy_id) VALUES ((?), (?), (?), (?)) RETURNING id"),
        DELETE("DELETE FROM count_medicines WHERE id = (?) RETURNING id"),
        UPDATE("UPDATE count_medicines SET count = (?) WHERE id = (?) RETURNING id"),

        GET_MED("SELECT * FROM medicines WHERE id = (?)");

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
        statement.setInt(2, countMedicine.getMedicine().getId());
        statement.setInt(3, countMedicine.getCount());
        statement.setInt(4, countMedicine.getPharmacy().getId());
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
            result.setMedicine(new MedicineDAO(connection).read(resultSet.getInt("medicine_id")));
            result.setCount(resultSet.getInt("count"));
            result.setPharmacy(new PharmacyDAO(connection).read(resultSet.getInt("pharmacy_id")));
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

    @Override
    public CountMedicine resultSetToObj(ResultSet rs) throws SQLException {
        CountMedicine count_medicine = new CountMedicine();

        if (rs.next()) {
            count_medicine.setId(rs.getInt("id"));
            count_medicine.setCount(rs.getInt("count"));
            count_medicine.setMedicine(new MedicineDAO(connection).read(rs.getInt("medicine_id")));
        }
    return count_medicine;
    }

}
