package com.fjnu.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class ReataurantApplicationTests {

    DataSource dataSource;

    @Test
    void contextLoads() {
    }

    @Test
    void test1(){
        System.out.println(dataSource.getClass());
    }

}
