
import java.util.Scanner;


class Menu {
    String list = "";
    double totalPrice = 0;
    int count = 0; // кол-во товаров в списке

    public boolean addTovar( Tovar tovar ) {
        this.count++;
        this.list += this.count + " - " + tovar.getInfo() + "\r\n";
        this.totalPrice += tovar.price;
        return true;
    }

    public double calculate( int people ) {
        return this.totalPrice / people;
    }
}

class Tovar {
    String name;
    double price;
    public String getInfo() {
        return String.format("%s = %.2f %s", this.name, this.price, new Formatter().roubles(this.price) );
    }
}

class Formatter {
    public String roubles( double value ) {
        switch ( (int)value % 10 ) {
            case 1: return "рубль";
            case 2,3,4: return "рубля";
            default: return "рублей";
        }
    }
}

public class Main {

    public static void main(String[] args) {

        System.out.println("Калькулятор счёта");

        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        int peoples = 0;
        String userInput = "";

        while (peoples < 2) {
            System.out.println("Укажите кол-во человек:");
            userInput = scanner.next().trim();
            try {
                peoples = Integer.parseInt(userInput);
                if (peoples == 1) {
                    System.out.println("ОШИБКА: для одного человека нет смысла ничего считать!");
                } else if (peoples < 2) {
                    System.out.println("ОШИБКА: Значение должно быть больше \"1\"!");
                }
            } catch (Exception e){
                System.out.println("ОШИБКА: Значение должно быть целым числом");
            }
        }

        //System.out.println("Количество = "+peoples);

        Menu menu = new Menu();

        while (true) {

            Tovar tovar = new Tovar();

            // название товара
            System.out.println("Укажите название для товара №"+(menu.count+1)+":");
            tovar.name = scanner.next().trim();

            // цена товара
            System.out.println("Укажите цену для товара №"+(menu.count+1)+( tovar.name.length() == 0 ? " ("+tovar.name+")" : "")+":");
            while (true) {
                userInput = scanner.next().trim();
                try {
                    tovar.price = Double.parseDouble(userInput);
                    break;
                } catch (Exception e) {
                    System.out.println("ОШИБКА: Некорректное значение, попробуйте ещё раз");
                }
            }

            // добавляем товар в меню
            if ( menu.addTovar( tovar )) {
                System.out.println("Товар №" + menu.count + " (" + tovar.getInfo() + ") успешно добален.");
            } else {
                System.out.println("ОШИБКА: товар не добавлен");
                break;
            }

            System.out.println("Добавить следующий товар?\r\nВведите любой символ чтобы продолжить или введите \"Завершить\" чтобы закончить формирование списка.");
            userInput = scanner.next().trim();
            if (userInput.equalsIgnoreCase("завершить")) {
                break;
            }
        }
        if (menu.count > 0) {
            System.out.println("Добавленные товары:");
            System.out.println( menu.list );
            System.out.println( String.format( "Каждый должен заплатить по %.2f %s.", menu.calculate(peoples), new Formatter().roubles(menu.calculate(peoples))));
        }
    }
}