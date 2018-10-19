package com.test.demo.modular.sys.controller;

import com.test.demo.modular.sys.entity.User;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamTest {


    static List<User> list = Arrays.asList(
            new User(1,"ron.zheng@tfschange.com",null,null),
            new User(2,"kds@qq.com",null,null),
            new User(3,"boduo@163.com",null,null),
            new User(4,"cangjin@gmail.com",null,null),
            new User(5,"xiaoze@hotmail.com",null,null),
            new User(6,"leblonjames@hotmail.com",null,null),
            new User(7,"allen.lei@tfschange.com",null,null),
            new User(8,"jr.smith@cel.com",null,null),
            new User(9,"dw.wade@cel.com",null,null),
            new User(10,"dw.wade@cel.com",null,null)
    );

    public static void main(String[] args) {
        //循环输出
        list.forEach(u -> System.out.println(u));

        // 复杂操作可以加个 {}
        list.forEach(u -> {
            System.out.println("第一次"+u);
            System.out.println("第二次"+u);
        });

        //filter（筛选条件）
        list.stream().filter(u -> u.getId()>5).forEach(u -> System.out.println(u));

        //limit(2) 取前两条
        list.stream().filter(u -> u.getId()>5).limit(2).forEach(u -> System.out.println(u));

        //skip(2) 跳过前两条
        list.stream().filter(u -> u.getId()>5).skip(2).forEach(u -> System.out.println(u));

        //map（） 方法
        list.stream().map(User::getId).collect(Collectors.toList()).forEach(System.out::println);

        //某一属性去重
        list.stream().filter(distinctByKey(u -> u.getUsername())).collect(Collectors.toList()).forEach(System.out::println);


    }

    // Predicate这个函数式接口，接受一个参数，返回布尔值
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


}
