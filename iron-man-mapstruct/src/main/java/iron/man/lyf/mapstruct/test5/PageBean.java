package iron.man.lyf.mapstruct.test5;

import lombok.*;

import java.util.List;

/**
 * @author liuyanfei
 * @description
 * @date 2021/12/9 10:05 上午
 **/
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {
    /**
     * 每页显示的条数
     */
    private Integer pageSize = 10;

    /**
     * 当前的页码
     */
    private Integer pageNum;

    /**
     * 一共有多少条记录
     */
    private Long total;

    /**
     * 一共有多少页
     */
    private Integer pages;

    /**
     * 每一页所显示的数据
     */
    private List<T> result;

    /**
     * 分页请求路径
     */
    private String url;

}

