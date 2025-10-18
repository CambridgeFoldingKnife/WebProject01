package com.zut.test;
import org.junit.jupiter.api.*;
import com.zut.UserService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UserServiceTest {
 /*   @BeforeAll
//    测试时最先运行
    public static void beforAll(){
        System.out.println("beforeAll");
    }
    @AfterAll
//    最后运行
    public static void afterAll(){
        System.out.println("afterAll");
    }
//    每个单元测试都运行
    @BeforeEach
    public void beforEach(){
        System.out.println("beforEach");
    }
//    每个单元测试之后运行
    @AfterEach
    public void afterEach(){
        System.out.println("afterEach");
    }*/
    @Test
    public void testGetAge(){
       Integer age =  new UserService().getAge("410329200312039839");
        System.out.println(age);
    }
    @Test
    public void testGetGender(){
        String gender = new UserService().getGender("410329200312039839");
        System.out.println(gender);
    }
//    批量测试
    @DisplayName("用户性别测试")
    @ValueSource(strings = {"410329200312035522","41032920031209738","410329200312037746"})
    @ParameterizedTest
    public void testGetGender2(String idcard){
        String gender = new UserService().getGender(idcard);
        System.out.println(gender);
    }
    @Test
    public void testGetGender3(){
        String gender = new UserService().getGender("410329200312039839");
        Assertions.assertEquals("男",gender);
        System.out.println(gender);
    }

}
