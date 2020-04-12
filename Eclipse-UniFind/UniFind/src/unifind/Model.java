package unifind;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Model {
    public String[] universityFileNames; // array of university ungly name
    private HashMap<String,String> universityNameConversion; // hashmap ugly name and formal name
    public ArrayList<University> universities; // list of universities with type University
    private HashMap<String,String> rankingList; // hashmap university and qs ranking
    private Model model;
    
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
        this.model = new Model();

    }

    // check if the string contains only numbers
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    // getter for UniversityFileNames
    public String[] getUniversityFileNames() {
        return this.universityFileNames;
    }

    //  getter for UniversityNameConversion
    public HashMap<String,String> getUniversityNameConversion() {
        return this.universityNameConversion;
    }

    // getter for Universities
    public ArrayList<University> getUniversities() {
        return this.universities;
    }

    // add university
    public void addUniversity(University u) {
        this.universities.add(u);
    }

    // getter for ranking of university
    public HashMap<String,String> getRankingList() {
        return this.rankingList;
    }

    // add hashmap university and ranking
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
                // Switch 105 on
                if (isInternational) {
                    tuition = p.getInternational_tuition();
                    // Switch 105 off
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
            model.addRankingList(line[0], line[1]); // hashmap university name and qs ranking
        }
        scanner.close();
        Set<String> uni = model.getRankingList().keySet(); // get all the key(formal university name)
        for (String u : model.getUniversityFileNames()) {
            for (String U : uni) {
                if (U.toLowerCase().contains(u)) {
                    String x = model.getRankingList().get(U);
                    if (!x.equals("N/A")) {
                        int y = Integer.parseInt(x); // change type to int
                        for (University z : model.getUniversities())
                            if (z.getName().equals(u))
                                z.setRanking(y); // set ranking
                    }
                }
            }
        }
    }



}
