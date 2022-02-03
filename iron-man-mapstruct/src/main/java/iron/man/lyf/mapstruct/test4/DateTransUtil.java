package iron.man.lyf.mapstruct.test4;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author liuyanfei
 * @description
 * @date 2021/12/8 10:57 下午
 **/
public class DateTransUtil {

    public static LocalDateTime str2LocalDateTime(String str){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(str,dtf);
    }

    public static String localDateTime2Str(LocalDateTime localDateTime){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dtf.format(localDateTime);
    }
}

