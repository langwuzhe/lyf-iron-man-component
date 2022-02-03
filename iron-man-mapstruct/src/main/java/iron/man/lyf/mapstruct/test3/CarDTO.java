package iron.man.lyf.mapstruct.test3;

import lombok.Data;

/**
 * @author liuyanfei
 * @description
 * @date 2021/12/8 10:04 上午
 **/
@Data
public class CarDTO {
    private Integer manufacturer;
    private Integer seatCount;
    private EngineDTO engine;
    private PersonDTO person;
}
