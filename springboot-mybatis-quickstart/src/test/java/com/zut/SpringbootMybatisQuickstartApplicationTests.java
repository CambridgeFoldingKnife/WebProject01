package com.zut;

import com.zut.mapper.UserMapper;
import com.zut.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootMybatisQuickstartApplicationTests {
    @Autowired//依赖注入自动装配
	private UserMapper userMapper;
	@Test
	public void testFindAll(){
		/*User testUser = new User(999, "test_lombok", "123456", "测试Lombok", 25);
		System.out.println("手动创建的User对象：" + testUser); // 打印这个对象
*/
		List<User> userList = userMapper.findAll();
		userList.stream().forEach(user -> {
			System.out.println(user);
		});

	}

}
