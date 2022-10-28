import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * main entry
 * @author jiuzhe
 *
 */
public class DishApp {
    public static void main(String[] args) {
        Dish dish = new Dish();
        // initialize the menu
        List<Dish> dishList = dish.initMenu();

        Scanner scanner = new Scanner(System.in);

        List<Dish> orderedList = new ArrayList<>();
        while (true) {
            System.out.println("----------Welcome to Order System--------");
            System.out.println("-------------【1】Make Order--------------");
            System.out.println("-------------【2】Show Ordered Menu-------");
            System.out.println("-------------【3】Delete Dish-------------");
            System.out.println("-------------【4】Check out---------------");
            System.out.println("-------------【0】Return/Exit-------------");

            System.out.println("Input your choice");
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    while (true) {
                        dish.showMenu(dishList);
                        System.out.println("Input the Id，or Input 0 to Return");
                        int id = scanner.nextInt();
                        if (id == 0) {
                            break;
                        }

                        System.out.println("Id:" + id);

                        System.out.println("Dish：" + dishList.get(id - 1).getName());
                        // add the dish to the cart
                        orderedList.add(dishList.get(id - 1));
                    }
                    break;
                case "2":
                    dish.showOrderedMenu(orderedList);
                    break;
                case "3":
                    if (orderedList.isEmpty()) {
                        System.out.println("There is no order. Please make your order!");
                    } else {
                        System.out.println("Input the dish number want to be deleted");
                        int id = scanner.nextInt();
                        dish.deleteDish(id, dishList, orderedList);
                    }
                    break;
                case "4":
                    dish.checkout(orderedList);
                    break;

                case "0":
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}
