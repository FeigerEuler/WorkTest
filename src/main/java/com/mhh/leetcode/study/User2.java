package com.mhh.leetcode.study;

import com.mhh.leetcode.annotation.ConvertType;

import com.mhh.leetcode.annotation.MyType;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class User2 {


    @NotBlank
    private String address;


    private String profession;

    @ConvertType(oriValues = {"男","女"},destValues = {"1","0"})
    private MyType gender;
}
