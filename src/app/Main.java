package app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static int percent;
    private final static String CURRENCY = "грн";

    public static void main (String[] args){

        double amount = getData();
        getOutput(amount);
    }
    private static double getData () {
        // Зчитуємо суму покупки.
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        System.out.print("Введіть суму покупки:");
        return scanner.nextDouble();
    }

    private static double calcDiscount (double amount){
        // Визначаємо відсоток та обчислюємо знижку.
        if (amount <= 5000) percent = 5;
        else if (amount <= 10000) percent = 10;
        else percent = 15;

        return amount / 100 * percent;
    }

    private static double calcTotal (double amount, double discount){
        // Обчислюємо суму до сплати
        return amount - discount;
    }

    private static int checkNumber() {
        // Створимо випадкове генерування
        // Чьотирьохзначного номеру чеку.
        int min = 1111;
        int max = 9999;
        int diff = max - min;
        Random random = new Random();
        int num = random.nextInt(diff + 1);
        num += min;
        return num;
    }

    private static String date(){
        // Створили об’єкт Date для визначення та виведення поточної дати та часу,
        // Використали клас SimpleDateFormat, щоб створити власний шаблон форматування дати й часу,
        // Перетворили створений шаблон у рядкову форму.
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
        String formattedDateTime = dateFormat.format(date);
        return formattedDateTime;
    }

    private static void getOutput(double amount) {

        int num = checkNumber();
        double discount = calcDiscount(amount);
        double total = calcTotal (amount, discount);
        String formattedDateTime = date();

        // Виведимо платіжну інформацію.
        System.out.printf(Locale.US,
                        "\n          ФІСКАЛЬНИЙ ЧЕК" +
                        "\n             № %d" +
                        "\n" + "-".repeat(34) +
                        "\nЗагальна сума покупки: %.2f %s" +
                        "\n" + "-".repeat(34) +
                        "\nЗнижка -%d%%             %.2f %s" +
                        "\nСУМА ДО СПЛАТИ:        %.2f %s" +
                        "\n" + "-".repeat(34) +
                        "\n %33s \n",
                num, amount, CURRENCY, percent, discount, CURRENCY, total, CURRENCY, formattedDateTime);
    }

}
