package com.gallant.java8;

import com.gallant.common.Print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * (::)方法引用, 类名::方法名
 * className::methodName
 * instanceName::methodName
 * ClassName::new
 * <p>
 * Created by huangjunhao on 19/12/29.
 */
public class LamabaDemo {


    //函数式接口声明
    @FunctionalInterface
    public interface PersonFactory<P extends Person> {
        P create(String name, int age);
    }


    //传统实现
    static PersonFactory<Person> personFactoryOld = new PersonFactory<Person>() {
        @Override
        public Person create(String name, int age) {
            return new Person(name, age);
        }
    };

    //lambda 实现
    static PersonFactory<Person> personFactoryLambda = (String name, int age) -> {
        return new Person(name, age);
    };

    //方法引用实现 Class::new 方式指定了接口 PersonFactory的实现类
    static PersonFactory<Person> personFactory = Person::new;

    public static void main(String[] args) {

        //函数式编程,流式表达,方法引用
        String[] strs = new String[]{"h", "e", "l", "l", "o"};
        Arrays.stream(strs).forEach(System.out::print);

        System.out.println();
        List<Person> lst = new ArrayList<>();
        lst.add(personFactoryOld.create("张三丰", 200));
        lst.add(personFactoryLambda.create("xxx", 20));
        lst.add(personFactory.create("ooo", 10));

        lst.stream().map(Person::getName).forEach(System.out::println);
        lst.stream().map(p -> p.getName() + "享年" + p.getAge() + "岁").forEach(System.out::println);
        lst.stream().forEach(Person::Introduction);
    }
}
