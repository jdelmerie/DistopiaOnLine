package fr.fms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.fms.business.IBusinessImpl;

@SpringBootTest
class DistopiaOnLineApplicationTests {
	
	@Autowired
	IBusinessImpl iBusinessImpl;

	@Test
	void contextLoads() {
	}

}
