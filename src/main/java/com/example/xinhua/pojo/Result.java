package com.example.xinhua.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result<T> {

    private Integer code;
    private String msg;
    private T data;

    public static <E> Result<E> success(String msg, E data) {
        return new Result<>(2000, msg, data);
    }

    public static Result error(String msg) {
        return new Result(4000, msg, null);
    }

    public static <E> Result error(String msg, E data) {
        return new Result(4000, msg, data);
    }

    public Result(boolean b, String msg2, Object data2) {
    }
}
