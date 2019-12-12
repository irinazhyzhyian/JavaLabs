package mapper;

import dto.PharmacyDTO;
import lab5.DAO.PharmacyDAO;
import lab5.model.Pharmacy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PharmacyMapper {

    PharmacyMapper INSTANCE = Mappers.getMapper( PharmacyMapper.class );

    PharmacyDTO getDto(Pharmacy pharmacy);

    Pharmacy getCatalog(PharmacyDAO catalogDTO);

    List<PharmacyDTO> getDtoList(List<Pharmacy> pharmacies);

    Pharmacy getPharmacy(PharmacyDTO pharmacyDTO);
}
