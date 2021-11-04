package Test;

import com.company.NameConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.*;

public class NumbersToTitleTest {

    @Test
    public void testGetNameZero() throws Exception {

        BigInteger zero = BigInteger.valueOf(0);
        String ZERO = "ноль";
        NameConstructor testing = new NameConstructor();
        testing.compsiteAnswer(zero);
        System.out.println("test 1 : zero");
        System.out.println(zero + " = " + testing.getName());
        Assertions.assertEquals(ZERO, testing.getName(),"Ошибка 1");
    }
    @Test
    public void testGetNameRandomNum() throws Exception {

        BigInteger testInt = BigInteger.valueOf(333222111);
        String testResult = "триста тридцать три миллиона двести двадцать две тысячи сто одиннадцать ";
        NameConstructor testing = new NameConstructor();
        testing.compsiteAnswer(testInt);
        System.out.println("test 2 : random number");
        System.out.println(testInt + " = " + testing.getName());
        Assertions.assertEquals( testResult, testing.getName(),"Ошибка 2");
    }

    @Test
    public void testUnitsAndAltTens() throws Exception {

        String[] TOKENS = new String[]{"один ", "два ", "три ", "четыре ",
                "пять ", "шесть ", "семь ", "восемь ", "девять ", "десять ", "одиннадцать ", "двенадцать ", "тринадцать ",
                "четырнадцать ", "пятнадцать ", "шестнадцать ", "семнадцать ", "восемнадцать ", "девятнадцать "};

        System.out.println("test 3: Numbers 1-19");
        for (int i = 1; i < 20; i++) {
            NameConstructor testing = new NameConstructor();
            testing.compsiteAnswer(BigInteger.valueOf(i));
            System.out.println(i + " = " + testing.getName());
            Assertions.assertEquals(TOKENS[i-1], testing.getName(),"Ошибка в промежутке от 1 до 19");
        }
    }

    @Test
    public void testGetNameTens() throws Exception {

        String[] TOKENS = new String[]{"двадцать девять ", "восемьдесят три ", "сорок семь ", "шестьдесят пять "};
        BigInteger tokens[] = new BigInteger[]{BigInteger.valueOf(29), BigInteger.valueOf(83),BigInteger.valueOf(47),BigInteger.valueOf(65)};
        System.out.println(" test 4 : double-digit numbers");
        for (int i = 0; i < 4; i++) {
            NameConstructor testing = new NameConstructor();
            testing.compsiteAnswer(tokens[i]);
            System.out.println(tokens[i] + " = " + testing.getName());
            Assertions.assertEquals(TOKENS[i], testing.getName(),"Ошибка для двухзначных чисел" );
        }
    }

    @Test
    public void testInputMismatchExceptionWhenInnerNotInt() throws InputMismatchException {
        Exception exception = Assertions.assertThrows(InputMismatchException.class, () -> {
             Scanner sc = new Scanner("1f11");
             BigInteger numIn = sc.nextBigInteger();
        });
    }

    @Test
    public void testExceptionWhenMinusString() throws NumberFormatException {
        Exception exception = Assertions.assertThrows(NumberFormatException.class, () -> {
            String s = new String("-");
            Integer.parseInt(s);
        });
    }


    @Test
    public void testGetNameAllTable() throws Exception {
        ArrayList<BigInteger> testdata = new ArrayList<>();
        ArrayList<String> exeptedata = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new FileReader("src/test/java/Test/TestResources/TestData.txt"));
            int i=0;
            while (scanner.hasNextLine()) {
                String[] columns = scanner.nextLine().split(";");
                testdata.add(i,BigInteger.valueOf(Long.parseLong(columns[0])));
                exeptedata.add(i,columns[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(int i = 0;testdata.size()> i;i++){
            NameConstructor testing = new NameConstructor();
            testing.compsiteAnswer(testdata.get(i));
            Assertions.assertEquals( testing.getName(),exeptedata.get(i),"Ошибка в числе: "+ testdata.get(i));
        }
    }


}