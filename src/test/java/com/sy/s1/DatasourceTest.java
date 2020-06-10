package com.sy.s1;

import static org.junit.jupiter.api.Assertions.*;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DatasourceTest {

	@Autowired
	private DataSource datasource;
	
	@Test
	void datasourceTest() throws Exception{
		assertNotNull(datasource.getConnection());
	}

}
