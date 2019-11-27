package main.lab5.DAO;

import main.lab5.model.Medicine;

import javax.validation.constraints.NotNull;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class MedicineDAO implements DAO<Medicine, Integer> {

    /**
     * SQL queries for medicines table.
     */
    enum MedicineSQL {
        GET("SELECT * FROM medicines  WHERE medicines.id = (?)"),
        INSERT("INSERT INTO medicines (id, name, form, overdue_day, price) VALUES ((?), (?), (?), (?), (?)) RETURNING id"),
        DELETE("DELETE FROM medicines WHERE id = (?) RETURNING id"),
        UPDATE("UPDATE medicines SET price = (?) WHERE form = (?) RETURNING id"),

        DELETE_OVERDUE_MEDICINES("DELETE FROM medicines WHERE overdue_day<current_date");

        String QUERY;

        MedicineSQL(String QUERY) {
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
    public MedicineDAO(@NotNull final Connection connection) {
        this.connection = connection;
    }

    /**
     * Create Medicine in DB
     *
     * @param medicine for create
     * @return false if Medicine already exist, true if creating success
     */
    @Override
    public boolean create(Medicine medicine) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement(MedicineSQL.INSERT.QUERY)) {
            statement.setInt(1, medicine.getId());
            statement.setString(2, medicine.getName());
            statement.setString(3, medicine.getForm());
            statement.setDate(4, Date.valueOf(medicine.getOverdueDay()));
            statement.setDouble(5, medicine.getPrice());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Select User by login.
     *
     * @param  id for select.
     * @return return valid entity if she exist. If entity does not exist return empty User with id = -1.
     */
    @Override
    public Medicine read(Integer id) {
        final Medicine result = new Medicine();
        result.setId(-1);

        try (PreparedStatement statement = connection.prepareStatement(MedicineSQL.GET.QUERY)) {
            statement.setInt(1, id);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result.setId(Integer.parseInt(resultSet.getString("id")));
                result.setOverdueDay(LocalDate.parse(resultSet.getDate("overdue_day").toString()));
                result.setPrice(resultSet.getDouble("price"));
                result.setName(resultSet.getString("name"));
                result.setForm(resultSet.getString("form"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Update Medicine`s price by id
     *
     * @param medicine new medicine state
     * @return true if success, false if fail
     */
    @Override
    public boolean update(Medicine medicine) {
        boolean result = false;

        try(PreparedStatement statement = connection.prepareStatement(MedicineSQL.UPDATE.QUERY)) {
            statement.setDouble(1, medicine.getPrice());
            statement.setString(2, medicine.getForm());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Delete Person by id
     *
     * @param medicine for delete
     * @return true if medicine was deleted, false if medicine not exist
     */
    @Override
    public boolean delete(Medicine medicine) {
        boolean result = false;

        try(PreparedStatement statement = connection.prepareStatement(MedicineSQL.DELETE.QUERY)) {
            statement.setInt(1, medicine.getId());
            result = statement.executeQuery().next();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void deleteWhereOverdue() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(MedicineSQL.DELETE_OVERDUE_MEDICINES.QUERY);
        statement.execute();
        statement.close();
    }

    /**
     * Convert ResultSet into Medicine
     *
     * @param rs ResultSet to convert
     * @return Medicine object
     * @throws SQLException
     */
    public Medicine resultSetToObj(ResultSet rs) throws SQLException {
        Medicine medicine = new Medicine();

            medicine.setId(rs.getInt("id"));
            medicine.setName(rs.getString("name"));
            medicine.setOverdueDay(LocalDate.parse(rs.getDate("overdue_day").toString()));
            medicine.setPrice(rs.getDouble("price"));
            medicine.setForm(rs.getString("form"));
        return medicine;
    }

}