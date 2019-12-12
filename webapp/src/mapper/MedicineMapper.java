package mapper;

import dto.MedicineDTO;
import lab5.model.Medicine;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MedicineMapper {

    MedicineMapper INSTANCE = Mappers.getMapper(MedicineMapper.class );

    MedicineDTO getDto(Medicine medicine);

    Medicine getMedicine(MedicineDTO medicineDTO);

    List<MedicineDTO> getDtoList(List<Medicine> medicines);
}
