package service;

import dto.PersonDTO;
import exception.ServiceException;
import lab5.DAO.PersonDAO;
import lab5.database.ConnectionManager;
import lab5.model.Person;
import mapper.PersonMapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PersonService {
    private PersonDAO personDAO;

    public PersonService() throws ServiceException {
        try {
            Connection connection = ConnectionManager.getConnection();
            personDAO = new PersonDAO(connection);
        } catch (SQLException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public PersonDTO findById(Integer id) throws ServiceException {
        try {
            PersonDTO result = null;
            Optional<Person> optionalPerson= Optional.ofNullable(personDAO.read(id));
            if (optionalPerson.isPresent()) {
                result = PersonMapper.INSTANCE.getDto(optionalPerson.get());
            }
            return result;
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public List<PersonDTO> findAll() throws ServiceException {
        try {
            return PersonMapper.INSTANCE.getDtoList(personDAO.findAll());
        } catch (SQLException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public boolean insert(PersonDTO personDTO) throws ServiceException {
        try {
            Person person = PersonMapper.INSTANCE.getPerson(personDTO);
            return personDAO.create(person);
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public boolean update(PersonDTO personDTO) throws ServiceException {
        try {
            Person person = PersonMapper.INSTANCE.getPerson(personDTO);
            return personDAO.update(person);
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public boolean delete(Integer id) throws ServiceException {
        try {
            return personDAO.deleteById(id);
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

}
