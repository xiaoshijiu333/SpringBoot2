package com.fei.springboothigh;

import com.fei.springboothigh.enums.SexEnum;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringboothighApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void test() {
        SexEnum sexEnum = null;
        Integer integer = Optional.ofNullable(sexEnum).map(SexEnum::getSexCode).orElse(null);
        System.out.println(integer);
    }

    @Test
    @Ignore
    public void test2() {
        List<Integer> integers = new ArrayList<>();
        List<Integer> integers2 = null;
        // 这样会报错，因为集合并不是空，而是长度为零
        Integer integer = Optional.ofNullable(integers).map(list -> list.get(0)).orElse(null);
        // 不会报错，为null的时候可以避免
        Integer integer2 = Optional.ofNullable(integers2).map(list -> list.get(0)).orElse(null);
        System.out.println(integer);
    }
}
