package com.company;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while (true) {
            try{
                Scanner sc = new Scanner(System.in);
                System.out.print("Введите целое число:");
                BigInteger numbIn = sc.nextBigInteger();
                if (numbIn.toString().length()<65){
                    NumberToTitle title = new NumberToTitle(numbIn);
                    System.out.println(title.getName());
                }else {
                    System.out.println("Слишком большое число");
                }

            }catch (InputMismatchException e) {
                System.out.println("Вы ввели не число. Попробуйте еще раз.");
            }
        }
    }
}


