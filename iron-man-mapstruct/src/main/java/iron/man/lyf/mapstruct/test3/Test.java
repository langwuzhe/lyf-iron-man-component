package iron.man.lyf.mapstruct.test3;

/**
 * @author liuyanfei
 * @description
 * @date 2021/12/9 11:28 上午
 **/
public class Test {
    public static void main(String[] args) {
        //利用 mapstruct 转换对象
        Car car = Car.builder().make(123).numberOfSeats(997).engine(new Engine()).build();
        CarDTO carDTO = CarMap.INSTANCE.carToCarDto(car);
        System.out.println(carDTO);


        //利用在接口中自定义的方式 来转换
        Person person = Person.builder().age(123).fullname("lyf").build();
        PersonDTO personDTO = CarMap.INSTANCE.personToPersonDto(person);
        System.out.println(personDTO);
    }
}
