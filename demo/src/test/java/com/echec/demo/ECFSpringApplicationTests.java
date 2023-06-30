package com.echec.demo;

import com.echec.demo.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ECFSpringApplicationTests {

	private User user;

	@BeforeEach
	void setUp(){
		user = new User();
	}




	@Test
	void testUserRankinvoid() {

	}

}
