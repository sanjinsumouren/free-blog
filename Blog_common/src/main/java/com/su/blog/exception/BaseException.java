package com.su.blog.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author su
 * @date 2020/5/16 15:05
 * 业务异常
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseException extends RuntimeException {

    private Integer code;

    private String msg;


    @Override
    public String getMessage() {
        return this.msg != null ? this.msg : super.getMessage();
    }
}
