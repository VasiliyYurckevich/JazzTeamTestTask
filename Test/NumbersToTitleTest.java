import com.company.NumberToTitle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;

public class NumbersToTitleTest {

    @Test
    public void testGetNameZero() throws Exception {

        BigInteger zero = BigInteger.valueOf(0);
        String ZERO = "ноль";
        NumberToTitle testing = new NumberToTitle(zero);
        System.out.println("test 1 : zero");
        System.out.println(zero + " = " + testing.getName());
        Assertions.assertEquals(ZERO, testing.getName(),"Ошибка 1");
    }
    @Test
    public void testGetNameRandomNum() throws Exception {

        BigInteger testInt = BigInteger.valueOf(333222111);
        String testResult = "триста тридцать три миллиона двести двадцать две тысячи сто одиннадцать ";
        NumberToTitle testing = new NumberToTitle(testInt);
        System.out.println("Ttest 2 : random number");
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
            NumberToTitle testing = new NumberToTitle(BigInteger.valueOf(i));
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
            NumberToTitle testing = new NumberToTitle(tokens[i]);
            System.out.println(tokens[i] + " = " + testing.getName());
            Assertions.assertEquals(TOKENS[i], testing.getName(),"Ошибка для двухзгначных чисел" );
        }
    }




}