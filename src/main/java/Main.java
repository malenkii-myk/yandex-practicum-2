
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Калькулятор счёта");

        Scanner scanner = new Scanner(System.in);

        int peoples = 0;
        while (peoples < 1) {
            System.out.println("Укажите кол-во человек:");
            peoples = scanner.nextInt();
            if (peoples < 2) {
                System.out.println("Значение олжно быть больше \"1\"!");
            }
        }

        String menuList = "";
        double menuPrice = 0;
        int menuCount = 0;

        while (true) {

            String tovarName = "";
            System.out.println("Укажите название для товара №"+(menuCount+1)+":");
            tovarName = scanner.next();

            double tovarPrice = 0;
            System.out.println("Укажите цену для товара №"+(menuCount+1)+( tovarName.length() == 0 ? " ("+tovarName+")" : "")+":");
            tovarPrice = scanner.nextDouble();

            menuCount++;
            menuList += menuCount + " - " + tovarName + " = " + tovarPrice + " руб.\r\n";
            menuPrice += tovarPrice;

            System.out.println("Товар №"+menuCount+" ("+ tovarName + " = " + tovarPrice + " руб.) успешно добален.");

            System.out.println("Добавить ещё один товар? Введите любой символ чтобы продолжить. Введите \"Завершить\" чтобы закончить формирование списка.");
            String cmd = scanner.next();
            if ( cmd.equalsIgnoreCase( "завершить" )  ) {
                System.out.println("Формирование списка завершено:");
                System.out.println( menuList );
                break;
            }
        }
    }
}