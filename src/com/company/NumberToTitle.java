package com.company;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.*;



public class NumberToTitle  {
    private final static char ONEENDING= '1',TWOENDING='2',THREEENDINGS='3',FOURENDINGS ='4';
    private final static String ZERO = "000",MINUS = "минус",ZEROTITLE = "ноль";
    private final static String[] ENDINGS = new String[]{"а","ов","и"};
    private final StringBuffer composition = new StringBuffer();



    private static Map<Integer, String> degree = new HashMap<>();
    static {
        degree.put(21, "вигинтиллион");
        degree.put(20, "новемдециллион");
        degree.put(19, "октодециллион");
        degree.put(18, "септемд ециллион");
        degree.put(17, "сексдециллион");
        degree.put(16, "квиндециллион");
        degree.put(15, "кваттордециллион");
        degree.put(14, "тредециллион");
        degree.put(13, "дуодециллион");
        degree.put(12, "андециллион");
        degree.put(11, "дециллион");
        degree.put(10, "нониллион");
        degree.put(9, "октиллион");
        degree.put(8, "септиллион");
        degree.put(7, "секстиллион");
        degree.put(6, "квинтиллион");
        degree.put(5, "квадриллион");
        degree.put(4, "триллион");
        degree.put(3, "миллиард");
        degree.put(2, "миллион");
        degree.put(1, "тысяч");
    }
    ArrayList<String> dataStorage = new ArrayList<>();

    protected void getDataStorage(BigInteger numIn){
        String s = numIn.toString();
        String subString;
        for (int i = 0;s.length()>3; i++) {
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

    public  NumberToTitle(BigInteger numIn){
        getDataStorage(numIn);
        checkingForNegativity(dataStorage.get(0));
        for (int i=0;i<dataStorage.size();i++){
            UnitConvertor convertor = new UnitConvertor();
            StringBuffer subs = new StringBuffer(dataStorage.get(i));
            while (subs.length() < 3) {
                subs.insert(0, '0');
            }
            dataStorage.set(i,subs.toString());
            if(dataStorage.get(i).equals(ZERO) == false) {
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
                        composition.append(convertor.getName() + " " + degree.get(dataStorage.size() - i - 1) +  ENDINGS[2] + " ");
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
                            dataStorage.get(i).charAt(dataStorage.get(i).length() - 1) == FOURENDINGS ) &&
                            dataStorage.get(i).charAt(dataStorage.get(i).length() - 2) != ONEENDING
                    ) {
                        composition.append(convertor.getName()+ " " + degree.get(dataStorage.size() - i - 1) + ENDINGS[0] + " ");
                    } else {
                        composition.append(convertor.getName() + " " + degree.get(dataStorage.size() - i - 1) +  ENDINGS[1] +" ");
                    }

                }
            }else {
                composition.append(ZEROTITLE);

            }

        }
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
            dataStorage.set(0,"000");
        }
    }



}
