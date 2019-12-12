package service;

import dto.CountMedicineDTO;
import exception.ServiceException;
import lab5.DAO.CountMedicineDAO;
import lab5.database.ConnectionManager;
import lab5.model.CountMedicine;
import mapper.CountMedicineMapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class CountMedicineService {
    private CountMedicineDAO countMedicineDAO;

    public CountMedicineService() throws ServiceException {
        try {
            Connection connection = ConnectionManager.getConnection();
            countMedicineDAO = new CountMedicineDAO(connection);
        } catch (SQLException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public CountMedicineDTO findById(Integer id) throws ServiceException {
        try {
            CountMedicineDTO result = null;
            Optional<CountMedicine> optional= Optional.ofNullable(countMedicineDAO.read(id));
            if (optional.isPresent()) {
                result = CountMedicineMapper.INSTANCE.getDto(optional.get());
            }
            return result;
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

//    public List<CountMedicineDTO findAll() throws ServiceException {
//        try {
//            return PersonMapper.INSTANCE.getDtoList(countMedicineDAO.findAll());
//        } catch (SQLException ex) {
//            throw new ServiceException(ex.getMessage());
//        }
//    }

    public boolean insert(CountMedicineDTO countMedicineDTO) throws ServiceException {
        try {
            CountMedicine countMedicine = CountMedicineMapper.INSTANCE.getCountMedicine(countMedicineDTO);
            return countMedicineDAO.create(countMedicine);
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public boolean update(CountMedicineDTO countMedicineDTO) throws ServiceException {
        try {
            CountMedicine countMedicine = CountMedicineMapper.INSTANCE.getCountMedicine(countMedicineDTO);
            return countMedicineDAO.update(countMedicine);
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public boolean delete(Integer id) throws ServiceException {
        try {
            return countMedicineDAO.deleteById(id);
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

}
