package com.example.xinhua.pojo;

import java.time.LocalDateTime;

import com.example.xinhua.anno.Age;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lombok.Data;

@Data
public class UserPojo {
    @Min(1)
    private Integer id;
    @NotNull(groups = { Add.class, Update.class })
    @Pattern(regexp = "^\\S{1,10}$")
    private String name;
    @JsonIgnore
    private String password;
    @Age
    private Integer age;// 默认分组
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    // 如果没有指定校验分组,那就是属于default分组
    // 分组可以继承

    public interface Add extends Default {

    }

    public interface Update extends Default {

    }

}
