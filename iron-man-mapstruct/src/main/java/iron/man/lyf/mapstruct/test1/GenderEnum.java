package iron.man.lyf.mapstruct.test1;

/**
 * @author liuyanfei
 * @description
 * @date 2021/12/7 9:53 下午
 **/
public enum GenderEnum {
    Male("1", "男"),
    Female("0", "女");

    private String code;
    private String name;

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    GenderEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
}