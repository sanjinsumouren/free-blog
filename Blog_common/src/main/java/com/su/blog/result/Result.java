package com.su.blog.result;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;



@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 统一响应结果封装类
**/
public class Result<T> implements Serializable {


    /**
     * 序列化版本标识符，用于序列化兼容性检查
     */
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 响应码，用于标识请求处理的结果状态
     */
    private Integer code;

    /**
     * 响应消息，用于描述请求处理的结果信息
     */
    private String msg;

    /**
     * 响应数据，用于承载请求处理的具体业务数据
     */
    private T data;

    /**
     * success
     */
    public static <T> Result<T> success() {
        return new Result<>(200, "success", null);
    }
    /**
     * success with data
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data);
    }
    /**
     * error
     */
    public static <T> Result<T> error(Integer code, String msg) {
        return new Result<T>(code, msg, null);
    }
}
