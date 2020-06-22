package com.excel.demo;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.util.List;

public class AnsyAndCombind {
    public static void main(String[] args) {
        ExcelReader reader06 = ExcelUtil.getReader(FileUtil.file("C:\\Users\\Admin\\Desktop\\06.xlsx"));
//        ExcelReader reader07 = ExcelUtil.getReader(FileUtil.file("1.xlsx","07"));
//        ExcelReader reader08 = ExcelUtil.getReader(FileUtil.file("1.xlsx","08"));
//        ExcelReader reader09 = ExcelUtil.getReader(FileUtil.file("1.xlsx","09"));
//        ExcelReader reader10 = ExcelUtil.getReader(FileUtil.file("1.xlsx","10"));
//        ExcelReader reader11 = ExcelUtil.getReader(FileUtil.file("1.xlsx","11"));
//        ExcelReader reader12 = ExcelUtil.getReader(FileUtil.file("1.xlsx","12"));
        List<List<Object>> readAll = reader06.read();
        System.out.println(readAll);


    }
}
