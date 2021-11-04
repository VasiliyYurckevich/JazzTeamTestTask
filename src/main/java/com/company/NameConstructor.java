package com.company;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.*;



public class NameConstructor  {
    private final static char ONEENDING= '1',TWOENDING='2',THREEENDINGS='3',FOURENDINGS ='4';
    private final static String ZERO = "000",MINUS = "минус",ZEROTITLE = "ноль";
    private final static String[] ENDINGS = new String[]{"а","ов","и"};
    private final StringBuffer composition = new StringBuffer();


    private final static Map<Integer, String> degree = new HashMap<>();
    static {
            try {
                Scanner scanner = new Scanner(new FileReader("src/main/java/com/company/resources/degrees.txt"));
                while (scanner.hasNextLine()) {
                    String[] columns = scanner.nextLine().split(",");
                    degree.put(Integer.parseInt(columns[0]),columns[1]);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

    }
    ArrayList<String> dataStorage = new ArrayList<>();

    protected void getDataStorage(BigInteger numIn){
        String s = numIn.toString();
        String subString;
        for (int i = s.length(); s.length()>3; i = i - 3) {
            subString = s.substring(s.length()-3);
            s = s.substring(0,s.length()-3);
            dataStorage.add(subString);
        }
        dataStorage.add(s);
        Collections.reverse(dataStorage);
    }

    public String getName(){

        return composition.toString();
    }


    private void checkingForNegativity(String s){
        try {
            if(Integer.parseInt(dataStorage.get(0)) < 0 ){
                composition.append(MINUS + " ");
                String value = dataStorage.get(0).substring(1);
                dataStorage.set(0,value);
            }
        }catch (NumberFormatException e){
            composition.append(MINUS + " ");
            dataStorage.set(0,ZERO);
        }
    }

    private void constructTitle(String s,int i) {
        UnitConvertor convertor = new UnitConvertor();
        if (dataStorage.size() == i + 1) {
            convertor.convertorUnit(dataStorage.get(i));
            composition.append(convertor.getName() + " ");
        } else if (dataStorage.size() == i + 2) {
            convertor.convertorThouthandsUnit(dataStorage.get(i));
            if (dataStorage.get(i).charAt(dataStorage.get(i).length() - 1) == ONEENDING &&
                    dataStorage.get(i).charAt(dataStorage.get(i).length() - 2) != ONEENDING) {
                composition.append(convertor.getName() + " " + degree.get(dataStorage.size() - i - 1) + ENDINGS[0] + " ");
            } else if ((dataStorage.get(i).charAt(dataStorage.get(i).length() - 1) == TWOENDING ||
                    dataStorage.get(i).charAt(dataStorage.get(i).length() - 1) == THREEENDINGS ||
                    dataStorage.get(i).charAt(dataStorage.get(i).length() - 1) == FOURENDINGS) &&
                    dataStorage.get(i).charAt(dataStorage.get(i).length() - 2) != ONEENDING
            ) {
                composition.append(convertor.getName() + " " + degree.get(dataStorage.size() - i - 1) + ENDINGS[2] + " ");
            } else {
                composition.append(convertor.getName() + " " + degree.get(dataStorage.size() - i - 1) + " ");
            }

        } else {
            convertor.convertorUnit(dataStorage.get(i));
            if (dataStorage.get(i).charAt(dataStorage.get(i).length() - 1) == ONEENDING &&
                dataStorage.get(i).charAt(dataStorage.get(i).length() - 2) != ONEENDING) {
                    composition.append(convertor.getName() + " " + degree.get(dataStorage.size() - i - 1) + " ");
            } else if ((dataStorage.get(i).charAt(dataStorage.get(i).length() - 1) == TWOENDING ||
                    dataStorage.get(i).charAt(dataStorage.get(i).length() - 1) == THREEENDINGS ||
                    dataStorage.get(i).charAt(dataStorage.get(i).length() - 1) == FOURENDINGS) &&
                    dataStorage.get(i).charAt(dataStorage.get(i).length() - 2) != ONEENDING
            ) {
                        composition.append(convertor.getName() + " " + degree.get(dataStorage.size() - i - 1) + ENDINGS[0] + " ");
            } else {
                        composition.append(convertor.getName() + " " + degree.get(dataStorage.size() - i - 1) + ENDINGS[1] + " ");
            }

        }
    }

    private void fillingFirstTriad(){
        StringBuffer subs = new StringBuffer(dataStorage.get(0));
        while (subs.length() < 3) {
            subs.insert(0, '0');
        }
        dataStorage.set(0,subs.toString());
    }

    public void compsiteAnswer(BigInteger numIn){
        getDataStorage(numIn);
        checkingForNegativity(dataStorage.get(0));
        for (int i=0;i<dataStorage.size();i++){
            fillingFirstTriad();
            if(dataStorage.get(i).equals(ZERO) == false) {
                constructTitle(dataStorage.get(i),i);
            }else if(dataStorage.size() == 1) {
                composition.append(ZEROTITLE);
            }

        }
    }


}
