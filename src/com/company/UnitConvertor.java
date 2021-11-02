package com.company;

public class UnitConvertor {
    private StringBuffer nameBuffer = new StringBuffer();
    private static final String[] TOKENS = new String[]{"", "один", "два", "три", "четыре",
            "пять", "шесть", "семь", "восемь", "девять"};
    private static final String[] TensTOKENSALT = new String[]{"десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать",
            "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};
    private static final String[] TOKENS_ALT = new String[]{"", "одна", "две", "три", "четыре",
            "пять", "шесть", "семь", "восемь", "девять"};
    private static final String[] TensTOKEENS = new String[]{"", "", "двадцать", "тридцать", "сорок",
            "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
    private static final String[] HundgredTOKENS = new String[]{"", "сто", "двести", "триста",
            "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};

    public String getName() {
        return nameBuffer.toString();
    }



    public void unitConvertor(String s) {

        char[] charArr = s.toCharArray();
        nameBuffer.append(HundgredTOKENS[Character.getNumericValue(charArr[0])]);
        if (charArr[0] != '0') {
            nameBuffer.append(" ");
        }
        if (charArr[1] == '1') {
            nameBuffer.append(TensTOKENSALT[Character.getNumericValue(charArr[2])] );

        } else {
            nameBuffer.append(TensTOKEENS[Character.getNumericValue(charArr[1])]);
            if (charArr[1] != '0') {
                nameBuffer.append(" ");
            }
            nameBuffer.append(TOKENS[Character.getNumericValue(charArr[2])]);


        }


    }

    public void unitThousandConvertor(String s) {
        StringBuffer subs = new StringBuffer(s);
        while (subs.length() < 3) {
            subs.insert(0, '0');
        }
        char[] charArr = subs.toString().toCharArray();
        nameBuffer.append(HundgredTOKENS[Character.getNumericValue(charArr[0])]);
        if (charArr[0] != '0') {
            nameBuffer.append(" ");
        }
        if (charArr[1] == '1') {
            nameBuffer.append(TensTOKENSALT[Character.getNumericValue(charArr[2])] );

        } else {
            nameBuffer.append(TensTOKEENS[Character.getNumericValue(charArr[1])]);
            if (charArr[1] != '0') {
                nameBuffer.append(" ");
            }
            nameBuffer.append(TOKENS_ALT[Character.getNumericValue(charArr[2])]);

        }
    }
}
