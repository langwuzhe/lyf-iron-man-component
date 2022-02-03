package iron.man.lyf.mapstruct.test3;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Objects;

/**
 * @author liuyanfei
 * @description
 * @date 2021/12/8 10:17 上午
 **/
@Mapper
public interface CarMap {
    CarMap INSTANCE = Mappers.getMapper(CarMap.class);

    @Mapping(source = "make", target = "manufacturer")
    @Mapping(source = "numberOfSeats", target = "seatCount")
    CarDTO carToCarDto(Car car);


    default PersonDTO personToPersonDto(Person person){
        if(Objects.isNull(person)){
            return null;
        }
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(person.getFullname());
        personDTO.setAge(person.getAge());
        return personDTO;
    }
}
