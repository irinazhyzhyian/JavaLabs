package service;

import dto.PharmacyDTO;
import exception.ServiceException;
import lab5.DAO.PharmacyDAO;
import lab5.database.ConnectionManager;
import lab5.model.CountMedicine;
import lab5.model.Pharmacy;
import mapper.PharmacyMapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PharmacyService {
    private PharmacyDAO pharmacyDAO;

    public PharmacyService() throws ServiceException {
        try {
            Connection connection = ConnectionManager.getConnection();
            pharmacyDAO = new PharmacyDAO(connection);
        } catch (SQLException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public PharmacyDTO findById(Integer id) throws ServiceException {
        try {
            PharmacyDTO result = null;
            Optional<Pharmacy> optional= Optional.ofNullable(pharmacyDAO.read(id));
            if (optional.isPresent()) {
                result = PharmacyMapper.INSTANCE.getDto(optional.get());
            }
            return result;
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public List<PharmacyDTO> findAll() throws ServiceException {
        try {
            return PharmacyMapper.INSTANCE.getDtoList(pharmacyDAO.findAll());
        } catch (SQLException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public boolean insert(PharmacyDTO pharmacyDTO) throws ServiceException {
        try {
            Pharmacy pharmacy = PharmacyMapper.INSTANCE.getPharmacy(pharmacyDTO);
            return pharmacyDAO.create(pharmacy);
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public boolean update(PharmacyDTO pharmacyDTO) throws ServiceException {
        try {
            Pharmacy pharmacy = PharmacyMapper.INSTANCE.getPharmacy(pharmacyDTO);
            return pharmacyDAO.update(pharmacy);
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public boolean delete(Integer id) throws ServiceException {
        try {
            return pharmacyDAO.deleteById(id);
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public List<CountMedicine> getListCountMedicine(Integer id) throws SQLException {
        List<CountMedicine> list = pharmacyDAO.getListCountMedicine(id);
        return list;
    }
}
