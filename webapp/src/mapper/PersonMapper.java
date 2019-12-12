package mapper;

import dto.PersonDTO;
import lab5.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person getPerson(PersonDTO personDTO);

    PersonDTO getDto(Person person);

    List<PersonDTO> getDtoList(List<Person> all);
}
