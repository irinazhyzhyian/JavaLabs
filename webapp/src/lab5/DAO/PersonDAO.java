package lab5.DAO;

import lab5.model.Person;

import javax.validation.constraints.NotNull;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class  PersonDAO implements DAO<Person, Integer> {

    /**
     * SQL queries for persons table.
     */
    enum PersonSQL {
        GET("SELECT * FROM persons  WHERE persons.id = (?)"),
        INSERT("INSERT INTO persons (id, first_name, last_name, birthday, salary) VALUES ((?), (?), (?), (?), (?)) RETURNING id"),
        DELETE("DELETE FROM persons WHERE id = (?) RETURNING id"),
        UPDATE("UPDATE persons SET salary = (?) WHERE id = (?) RETURNING id"),

        GET_ALL("SELECT * FROM persons");

        String QUERY;

        PersonSQL(String QUERY) {
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
    public PersonDAO(@NotNull final Connection connection) {
        this.connection = connection;
    }

    /**
     * Create Person in DB
     *
     * @param person for create
     * @return false if Person already exist, true if creating success
     */
    @Override
    public boolean create(Person person) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement(PersonSQL.INSERT.QUERY)) {
            statement.setInt(1, person.getId());
            statement.setString(2, person.getFirstName());
            statement.setString(3, person.getLastName());
            statement.setDate(4, Date.valueOf(person.getBirthday()));
            statement.setDouble(5, person.getSalary());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Select Person by last_name.
     *
     * @param id for select.
     * @return return valid entity if she exist. If entity does not exist return empty Person with id = -1.
     */
    @Override
    public Person read(Integer id) {
        Person result = new Person();
        result.setId(-1);

        try (PreparedStatement statement = connection.prepareStatement(PersonSQL.GET.QUERY)) {
            statement.setInt(1, id);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result.setId(Integer.parseInt(resultSet.getString("id")));
                result.setBirthday(LocalDate.parse(resultSet.getDate("birthday").toString()));
                result.setSalary(resultSet.getDouble("salary"));
                result.setFirstName(resultSet.getString("first_name"));
                result.setLastName(resultSet.getString("last_name"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Update Person`s salary by id
     *
     * @param person new person state
     * @return true if success, false if fail
     */
    @Override
    public boolean update(Person person) {
        boolean result = false;

        try(PreparedStatement statement = connection.prepareStatement(PersonSQL.UPDATE.QUERY)) {
            statement.setDouble(1, person.getSalary());
            statement.setInt(2, person.getId());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Delete Person by id
     *
     * @param person for delete
     * @return true if person was deleted, false if person not exist
     */
    @Override
    public boolean delete(Person person) {
        boolean result = false;

        try(PreparedStatement statement = connection.prepareStatement(PersonSQL.DELETE.QUERY)) {
            statement.setInt(1, person.getId());
            result = statement.executeQuery().next();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Convert ResultSet into Person
     *
     * @param rs ResultSet to convert
     * @return Person object
     * @throws SQLException
     */
    public Person resultSetToObj(ResultSet rs) throws SQLException {
        Person person = new Person();
         
            person.setId(rs.getInt("id"));
            person.setFirstName(rs.getString("first_name"));
            person.setLastName(rs.getString("last_name"));
            person.setBirthday(rs.getDate("birthday").toLocalDate());
            person.setSalary(rs.getDouble("salary"));

        return person;
    }

    public List<Person> findAll() throws SQLException {
        List<Person> list = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(PersonSQL.GET_ALL.QUERY)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Person person = resultSetToObj(resultSet);
                list.add(person);
            }
            return list;
        }
    }

}
