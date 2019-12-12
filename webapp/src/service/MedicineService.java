package service;

import dto.MedicineDTO;
import exception.ServiceException;
import lab5.DAO.MedicineDAO;
import lab5.database.ConnectionManager;
import lab5.model.Medicine;
import mapper.MedicineMapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MedicineService {
    private MedicineDAO medicineDAO;

    public MedicineService() throws ServiceException {
        try {
            Connection connection = ConnectionManager.getConnection();
            medicineDAO = new MedicineDAO(connection);
        } catch (SQLException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public MedicineDTO findById(Integer id) throws ServiceException {
        try {
            MedicineDTO result = null;
            Optional<Medicine> optionalMedicine= Optional.ofNullable(medicineDAO.read(id));
            if (optionalMedicine.isPresent()) {
                result = MedicineMapper.INSTANCE.getDto(optionalMedicine.get());
            }
            return result;
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public List<MedicineDTO> findAll() throws ServiceException {
        try {
            return  MedicineMapper.INSTANCE.getDtoList(medicineDAO.findAll());
        } catch (SQLException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public boolean insert(MedicineDTO medicineDTO) throws ServiceException {
        try {
            Medicine medicine = MedicineMapper.INSTANCE.getMedicine(medicineDTO);
            return medicineDAO.create(medicine);
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public boolean update(MedicineDTO medicineDTO) throws ServiceException {
        try {
            Medicine medicine = MedicineMapper.INSTANCE.getMedicine(medicineDTO);
            return medicineDAO.update(medicine);
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public boolean delete(Integer id) throws ServiceException {
        try {
            return medicineDAO.deleteById(id);
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

}
