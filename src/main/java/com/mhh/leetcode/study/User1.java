package com.mhh.leetcode.study;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class User1 {

    @NonNull
    private String name;

    private int age;

    private String hobby;

}
