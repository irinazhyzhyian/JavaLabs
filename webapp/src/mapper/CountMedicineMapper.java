package mapper;

import dto.CountMedicineDTO;
import lab5.model.CountMedicine;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CountMedicineMapper {

    CountMedicineMapper INSTANCE = Mappers.getMapper( CountMedicineMapper.class );

    CountMedicineDTO getDto(CountMedicine countMedicine);

    CountMedicine getCountMedicine(CountMedicineDTO countMedicineDTO);

    List<CountMedicineDTO> getDtoList(List<CountMedicine> countMedicines);
}
