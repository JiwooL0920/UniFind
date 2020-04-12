package unifind;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Model {
    private String[] universityFileNames;
    private HashMap<String,String> universityNameConversion;
    private ArrayList<University> universities;
    private HashMap<String,String> rankingList;
    private ArrayList<String> boolList;

    public Model() {
        this.universityFileNames = new String[] {"algoma", "brock", "carleton",
                "guelph", "hearst", "lakehead",
                "laurentian", "mcmaster", "nipissing",
                "ocad", "uoit", "ottawa",
                "queens", "ryerson", "trent",
                "uoft", "waterloo", "western",
                "wilfred_laurier", "windsor", "york"};

        this.universityNameConversion = new HashMap<String,String>();
        this.universityNameConversion.put("algoma","Algoma University");
        this.universityNameConversion.put("brock","Brock University");
        this.universityNameConversion.put("carleton","Carleton University");
        this.universityNameConversion.put("guelph","University of Guelph");
        this.universityNameConversion.put("hearst","Université de Hearst");
        this.universityNameConversion.put("lakehead","Lakehead University");
        this.universityNameConversion.put("laurentian","Laurentian University");
        this.universityNameConversion.put("mcmaster","McMaster University");
        this.universityNameConversion.put("nipissing","Nipissing University");
        this.universityNameConversion.put("ocad","OCAD University");
        this.universityNameConversion.put("uoit","University of Ontario Institute of Technology");
        this.universityNameConversion.put("ottawa","University of Ottawa");
        this.universityNameConversion.put("queens","Queen's University");
        this.universityNameConversion.put("ryerson","Ryerson University");
        this.universityNameConversion.put("trent","Trent University");
        this.universityNameConversion.put("uoft","University of Toronto");
        this.universityNameConversion.put("waterloo","University of Waterloo");
        this.universityNameConversion.put("western","Western University");
        this.universityNameConversion.put("wilfred_laurier","Wilfred Laurier University");
        this.universityNameConversion.put("windsor","University of Windsor");
        this.universityNameConversion.put("york","York University");

        this.universities = new ArrayList<>();
        this.rankingList = new HashMap<>();
        this.boolList = new ArrayList<>();
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public String[] getUniversityFileNames() {
        return this.universityFileNames;
    }

    public HashMap<String,String> getUniversityNameConversion() {
        return this.universityNameConversion;
    }

    public ArrayList<University> getUniversities() {
        return this.universities;
    }

    public void addUniversity(University u) {
        this.universities.add(u);
    }

    public HashMap<String,String> getRankingList() {
        return this.rankingList;
    }

    public void addRankingList(String uniName, String ranking) {
        this.rankingList.put(uniName,ranking);
    }

    //Function to remove quotations (for requirement)
    public String removeQuotations(String s) {
        if (s.charAt(0) == '"') {
            return s.substring(1, s.length() - 1);
        } else return s;
    }

    //Function that changes Yes/No to boolean
    public boolean yesNoConversion(String s) {
        switch (s) {
            case "Yes":
                return true;
            case "No":
                return false;
            default: // ?
                return false;
        }
    }

    public String booleanToString(boolean b) {
        if (b) return "Yes";
        else return "No";
    }

    //Sort universities based on: admission average/tuition
    public University[] getProgramBasedOnCategory(String programName, String category, boolean isInternational, boolean coop, int tuitionUpperBound) {
        HashMap<String,Integer> programRanking = new HashMap<String,Integer>();
        for (University u : this.universities) {
            Program p = u.getProgram(programName);
            if (p != null) {
                int tuition;
                if (isInternational) {
                    tuition = p.getInternational_tuition();
                } else {
                    tuition = p.getLocal_tuition();
                }
                boolean test = false;
                if (coop) test = p.isCoop() == coop && tuition <= tuitionUpperBound;
                else test = tuition <= tuitionUpperBound;
                if (test) {
                    int val = 0;
                    switch (category) {
                        case "admission_average":
                            val = p.getAdmission_average();
                            break;
                        case "ranking":
                            val = u.getRanking();
                            break;
                        case "local_tuition":
                            val = p.getLocal_tuition();
                            break;
                        case "international_tuition":
                            val = p.getInternational_tuition();
                            break;
                    }
                    programRanking.put(u.getName(),new Integer(val));
                }
            }
        }


        //make hashmap into array [ names ]    [ val ]   at same index
        String[] universityNames = objToString(programRanking.keySet().toArray());
        Integer[] valuesInteger = objToInt(programRanking.values().toArray());
        //change this to int[]
        int[] values = new int[valuesInteger.length];
        for (int i = 0; i < valuesInteger.length; i++) {
            values[i] = Integer.parseInt(valuesInteger[i].toString());
        }
        //sort values and use that index to sort the names
        String[] resultInString = new String[universityNames.length];
        switch(category) {
            case "admission_average":
                resultInString = sortDecreasingOrder(universityNames, values);
                break;
            case "ranking":
            case "local_tuition":
            case "international_tuition":
                resultInString = sortIncreasingOrder(universityNames,values);
                break;


        }
        //Change this into array of universities
        University[] result = new University[resultInString.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = getUniversity(resultInString[i]);
        }
        return result;
    }


    public String[] objToString(Object[] obj) {
        String[] result = new String[obj.length];
        for (int i = 0; i < obj.length; i++) {
            result[i] = obj[i].toString();
        } return result;
    }

    public Integer[] objToInt(Object[] obj) {
        Integer[] result = new Integer[obj.length];
        for (int i = 0; i < obj.length; i++) {
            result[i] = Integer.parseInt(obj[i].toString());
        } return result;
    }

    //bubble sort
    public String[] sortDecreasingOrder(String[] universityNames, int[] values) {
        boolean sorted = true;
        while (sorted) {
            sorted = false;
            for (int i = 0; i < values.length - 1; i++) {
                if (values[i+1] > values[i]) {
                    exchInt(values,i,i+1);
                    exchString(universityNames,i,i+1);
                    sorted = true;
                }
            }
        } return universityNames;
    }

    public String[] sortIncreasingOrder(String[] universityNames, int[] values) {
        boolean sorted = true;
        while (sorted) {
            sorted = false;
            for (int i = 0; i < values.length - 1; i++) {
                if (values[i+1] < values[i]) {
                    exchInt(values,i,i+1);
                    exchString(universityNames,i,i+1);
                    sorted = true;
                }
            }
        } return universityNames;
    }

    public void exchString(String[] a, int i, int j) {
        String m1 = a[i];
        a[i] = a[j];
        a[j] = m1;
    }

    public void exchInt(int[] a, int i, int j) {
        int m1 = a[i];
        a[i] = a[j];
        a[j] = m1;
    }

    //Get university given string name
    public University getUniversity(String name) {
        for (University u : this.universities) {
            if (u.getName().equals(name)) return u;
        } return null;
    }


    
    //Get Data -- originally in MajorSortActivity.java but moved to here for eclipse demonstration purposes
    public void getData() throws Exception {
    	getUniversityData();
    	getUniversityRanking();
    }
    
    public void getUniversityData() throws Exception{
		try {
				for (String name : universityFileNames) {
					BufferedReader csvReader = new BufferedReader(new FileReader("data/"+name+".csv"));
					String currentLine = csvReader.readLine(); //start from non-header
					currentLine = csvReader.readLine();
					University university = new University(name);
					while (currentLine != null) {
						String[] cells = currentLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
						currentLine = csvReader.readLine();
		                Program p = new Program(cells[0],                       //name
		                        Integer.parseInt(cells[1]),                       //Admission Average
		                        Integer.parseInt(cells[2]),     //local tuition
		                        Integer.parseInt(cells[3]),     //international tuition
		                        cells[4],                       //requirement
		                        yesNoConversion(cells[5]),      //coop
		                        cells[6],                       //target enrolment
		                        yesNoConversion(cells[7]));     //supplementary application
		                university.addProgram(p);
					}	
					universities.add(university);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void getUniversityRanking() throws FileNotFoundException {
    	  BufferedReader ins = new BufferedReader(new FileReader("data/qs_world_ranking.csv"));
    	        Scanner scanner = new Scanner(ins);
    	        scanner.nextLine(); // skip first line
    	        while (scanner.hasNext()) {
    	            String[] line = scanner.nextLine().split(","); // split by comma
    	            rankingList.put(line[0], line[1]); // hashmap university name and qs ranking
    	        }
    	        scanner.close();
    	        Set<String> uni = this.rankingList.keySet(); // get all the key(formal university name)
    	        for (String u : this.universityFileNames) {
    	            for (String U : uni) {
    	                if (U.toLowerCase().contains(u)) {
    	                    String x = this.rankingList.get(U);
    	                    if (!x.equals("N/A")) {
    	                        int y = Integer.parseInt(x); // change type to int
    	                        for (University z : this.universities)
    	                            if (z.getName().equals(u))
    	                                z.setRanking(y); // set ranking
    	                    }
    	                }
    	            }
    	        }
    	    }
    
    public static boolean validMajor(String userInput) {
    	String s = "12345";
    	return s.contains(userInput);
    }
    
    public static boolean validCategory(String userInput) {
    	String s = "123";
    	return s.contains(userInput);
    }
    
    public static boolean validYesNo(String userInput) {
    	String s = "12";
    	return s.contains(userInput);
    }
    
    public static void main(String[] args) throws Exception {
    	Model m = new Model(); 
    	m.getData(); 


    	while (true) {
    		System.out.println("[UniFind Terminal Version - for Test Purposes]");
        	System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
        	System.out.println("This is a very simple terminal version of our android app for demonstration purposes. \n"
        			+ "For now, you can choose 5 majors to test how our code works (on android studio there are more selections user can choose from");
        	System.out.println("Example input:1");
        	System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");

        	Scanner scanner = new Scanner(System.in);
        	
    		boolean pass1 = false;
        	String major = null;
        	while (!pass1) {
            	System.out.println("[Step 1] Choose major:");
            	System.out.println("1) Computer Science");
            	System.out.println("2) Physics");
            	System.out.println("3) Nursing");
            	System.out.println("4) Health Science");
            	System.out.println("5) Engineering");
            	System.out.print("Input: ");
            	String in1 = scanner.nextLine();
            	if (validMajor(in1)) {
            		switch (in1) {
            			case "1":
            				major = "Computer Science";
            				break;
            			case "2":
            				major = "Physics";
            				break;
            			case "3":
            				major = "Nursing";
            				break;
            			case "4":
            				major = "Health Science";
            				break;
            			case "5":
            				major = "Engineering";
            				break;
            		}
            		pass1 = true;
            		System.out.println("");
            	} else {
            		System.out.println("\nInvalid Input. Try again.");
            	}
        	}
        	

        	
        	boolean pass3 = false;
        	boolean coop = false; 
        	while (!pass3) {
        		System.out.println("[Step 2] Filter for coop? : ");
        		System.out.println("1) Yes");
        		System.out.println("2) No");
        		System.out.print("Input: ");
        		String in3 = scanner.nextLine();
        		if (validYesNo(in3)) {
        			switch(in3) {
        			case "1":
        				coop = true;
        				break;
        			case "2":
        				coop = false;
        				break;
        			}
        			pass3 = true;
        			System.out.println("");
        		} else {
        			System.out.println("\nInvalid Input. Try again.");
        		}
        	}
        	
        	boolean pass4 = false;
        	boolean international = false;
        	while (!pass4) {
        		System.out.println("[Step 3] Are you international student? : ");
        		System.out.println("1) Yes");
        		System.out.println("2) No");
        		System.out.print("Input: ");
        		String in4 = scanner.nextLine();
        		if (validYesNo(in4)) {
        			switch(in4) {
        			case "1":
        				international = true;
        				break;
        			case "2":
        				international = false;
        				break;
        			}
        			pass4 = true;
        			System.out.println("");
        		} else {
        			System.out.println("\nInvalid Input. Try again.");
        		}
        	}
        	
        	boolean pass5 = false;
        	int tuitionUpperBound = Integer.MAX_VALUE;
        	while (!pass5) {
        		System.out.print("[Step 4] Tuition UpperBound: ");
        		String in5 = scanner.nextLine();
        		if (isNumeric(in5)) {
        			tuitionUpperBound = Integer.parseInt(in5);
        			pass5 = true;
        			System.out.println("");
        		} else {
        			System.out.println("\nInvalid Input. Try again.");
        		}
        	}
        	
        	boolean pass2 = false;
        	String category = "";
        	while(!pass2) {
        		System.out.println("[Step 4] Choose category:");
        		System.out.println("1) Ranking");
        		System.out.println("2) Admission Average");
        		System.out.println("3) Tuition");
        		System.out.print("Input: ");
        		String in2 = scanner.nextLine();
        		if (validCategory(in2)) {
        			switch(in2) {
        				case "1":
        					category = "ranking";
        					break;
        				case "2":
        					category = "admission_average";
        					break;
        				case "3":
        					if (international) {
        						category = "international_tuition";
        					} else {
        						category = "local_tuition";
        					}
        					break;
        			}
        			pass2 = true;
        			System.out.println("");
        		} else {
        			System.out.println("\n Invalid Input. Try again.");
        		}
        	}
        	
        	//print
        	University[] result = m.getProgramBasedOnCategory(major,category,international,coop,tuitionUpperBound);
        	int counter = 1; 
        	System.out.println("\n[RESULT]");
        	System.out.println("University,Ranking,Program Name,Admission Average,Domestic Tuition,International Tuition,Requirements,Coop,Target Enrolment,Supplementary Application");
        	for (University u: result) {
        		Program p = u.getProgram(major);
        		System.out.print(counter+") ");
        		System.out.print(u.getName() + ",");
        		System.out.print(u.getRanking()+",");
        		System.out.print(p.getName()+ ",");
        		System.out.print(p.getAdmission_average()+",");
        		System.out.print(p.getLocal_tuition()+",");
        		System.out.print(p.getInternational_tuition()+",");
        		System.out.print(p.getRequirements()+",");
        		System.out.print(p.isCoop()+",");
        		System.out.print(p.getTarget_enrolment()+",");
        		System.out.println(p.isSupplementary_applicatoin());
        		counter++;
        		
        	}
        	System.out.println("[FINISH]--------------------------------------------------------------------------------------------------------------\n\n\n\n\n");
    	}
    	
    	
    	
    }



}
