package com.example.yuyuhee_2th;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Yuyuhee2thApplicationTests {

	@Test
	void contextLoads() {
		String test = "SONG";
		assertEquals("SONG", test);
	}
}
