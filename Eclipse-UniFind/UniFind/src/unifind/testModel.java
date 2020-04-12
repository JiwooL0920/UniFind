package unifind;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class testModel {
	private Model model;
	private ArrayList<University> universities; 
	 private String str1;
	 private String str2;
	 private String str3;
	 private String str4; 
	 private String str5;
	 private String str6;
	 private String[] uniNames1 = {"brock", "mcmaster", "york", "ottawa"};
	 private int[] values1 = {24, 0, 97, 58};
	 
	
	@BeforeEach 
	void setUp() throws Exception {
		model = new Model();
		model.getData();
		universities = model.getUniversities();
		  str1 = "57290";
		  str2 = "uniUniFind3";
		  str3 = "\"quotationS\"";
		  str4 = "ttttesTT29dj";
		  str5 = "algoma";
		  str6 = "Algoma University";
	}
	
	@AfterEach 
	void tearDown() throws Exception {
		model = null;
		universities = null;
		  str1 = null;
		  str2 = null;
		  str3 = null;
		  str4 = null;
		  str5 = null;
		  str6 = null;
	}
	
	@Test
	void testIsNumeric() {
		  assertTrue(model.isNumeric(str1));
		  assertFalse(model.isNumeric(str2));
	}
	
	@Test
	void testremoveQuotations() {
		  assertTrue(model.removeQuotations(str3).equals("quotationS"));
		  assertTrue(model.removeQuotations(str4).equals("ttttesTT29dj"));
		  assertFalse(model.removeQuotations(str4).equals("\"ttttesTT29dj"));
	}
	
	@Test
	void testUniversitiesBasedOnCategory() {
		University[] output = model.getProgramBasedOnCategory("Computer Science", "ranking", false, false, Integer.MAX_VALUE);
		String[] expected = new String[] {"uoft","mcmaster","waterloo","york","guelph","windsor","carleton","ryerson","wilfred_laurier","laurentian","uoit","trent","nipissing","brock","lakehead"};
		
		for (int i = 0; i < output.length; i++) {
			assertTrue(output[i].getName().equals(expected[i]));
		}
	}
	
	
	@Test
	void testIncreasingOrder() {
		String[] item1 = model.sortIncreasingOrder(uniNames1, values1);
		String[] item2 = new String[] {"mcmaster", "brock", "ottawa", "york"}; //[mcmaster, brock, ottawa, york]
		for (int i = 0; i < item1.length; i++) {
			assert(item1[i].equals(item2[i]));
		}

		
	}
	
	@Test
	void testDecreasingOrder() {
		String[] item1 = model.sortDecreasingOrder(uniNames1, values1);
		String[] item2 = new String[] {"york","ottawa","brock","mcmaster"};
		for (int i = 0; i < item1.length; i++) {
			assert(item1[i].equals(item2[i]));
		}
	}
	
	
	
}
