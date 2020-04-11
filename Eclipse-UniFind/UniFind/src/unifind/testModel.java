package unifind;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class testModel {
	private Model model;
	private ArrayList<University> universities; 
	
	@BeforeEach 
	void setUp() throws Exception {
		model = new Model();
		model.getData();
		universities = model.getUniversities();
	}
	
	@AfterEach 
	void tearDown() throws Exception {
		model = null;
		universities = null;
	}
	
	@Test
	void testIsNumeric() {
		assert true;
	}
	
	@Test
	void testremoveQuotations() {
		assert true;
	}
	
	@Test
	void testUniversitiesBasedOnCategory() {
		assert true;
	}
	
	
	@Test
	void testIncreasingOrder() {
		assert true;
	}
	
	@Test
	void testDecreasingOrder() {
		assert true;
	}
	
	
	@Test
	void testGetUniversity() {
		assert true;
	}
	
	@Test
	void customTestCase() {
		
	}
	
	@Test
	void customTestCase2() {
		
	}
	
	@Test
	void customTestCase3() {
		
	}
	

	
}
