package com.cycle.demo01;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;

public class AutoCode {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://124.70.3.242:3306/study","study","yh3DC4yCLYckZ2Cf")
                .globalConfig(builder -> {
                    builder.author("cycle")
                            .enableSwagger()
                            .outputDir(System.getProperty("user.dir")+"/src/main/java");
                }).packageConfig(builder -> {
                    builder.parent("com.cycle").moduleName("blog");
                }).strategyConfig(builder -> {
                    builder.addTablePrefix("ms_").addInclude("ms_article");
                }).execute();
    }
}
