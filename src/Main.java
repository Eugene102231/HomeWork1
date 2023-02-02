import javax.management.monitor.StringMonitor;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int sum = 0;
        String[] products = {"Молоко", "Хлеб", "Гречневая крупа"};
        int[] prices = {20, 10, 35};
        int[] count = new int[products.length];
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб/шт");
        }

        while (true) {
            System.out.println("Выберите товар и количество или введите 'end'");
            String input =  scan.nextLine();

            if (input.equals("end")) {
                break;
            }

            String[] parts = input.split(" ");

            try {
                int productNumber = Integer.parseInt(parts[0]) - 1;
                if (parts.length != 2) {
                    System.out.println("Ошибка! Введите 2 части!");
                    continue;
                }
                if (productNumber >= products.length || productNumber <= -1) {
                    System.out.println("Ошибка! Вы ввели неверный номер продукта!");
                    continue;
                }
                int productCount = Integer.parseInt(parts[1]);
                if (productCount <= 0) {
                    System.out.println("Ошибка! Вы ввели неверное кол-во товара!");
                    continue;
                }

                count[productNumber] += productCount;

            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введите числа!");
            }
        }

        System.out.println("Ваша корзина: ");
        for (int i = 0; i < products.length; i++) {
            if (count[i] != 0)
                System.out.println(products[i] + " " + count[i] + " шт " + prices[i] + " руб/шт " + count[i] * prices[i] + " руб в сумме");
            sum += count[i] * prices[i];
        }
        System.out.println("Итого: " + sum + " руб");

        SalesManager salesManager = new SalesManager(count);
        System.out.println(salesManager.max());
    }
}