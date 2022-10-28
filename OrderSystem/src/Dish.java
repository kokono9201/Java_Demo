import java.util.ArrayList;
import java.util.List;

/**
 * @author jiuzhe
 * @create 2022-10-28-19:42
 */
public class Dish implements DishInterface{

    /**
     * id
     */
    private int id;
    /**
     * dishName
     */
    private String name;
    /**
     * price
     */
    private double price;
    /**
     * get,set,constructor
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Dish() {
    }

    public Dish(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    /**
     * initialize the menu
     *
     * @return the menu list
     */
    public List<Dish> initMenu() {
        List<Dish> dishList = new ArrayList<>();
        dishList.add(new Dish(1, "DishSet1", 58));
        dishList.add(new Dish(2, "DishSet2", 68));
        dishList.add(new Dish(3, "DishSet3", 38));
        dishList.add(new Dish(4, "DishSet4", 48));
        dishList.add(new Dish(5, "DishSet5", 18));
        dishList.add(new Dish(6, "DishSet6", 28));
        dishList.add(new Dish(7, "DishSet7", 25));
        dishList.add(new Dish(8, "DishSet8", 10));
        dishList.add(new Dish(9, "DishSet9", 15));
        dishList.add(new Dish(10, "DishSet10", 20));
        return dishList;
    }

    /**
     * display the menu
     *
     * @param dishList menu list
     */
    public void showMenu(List<Dish> dishList) {
        System.out.println("------------MENU-------------");
        System.out.println("No.\t\tDish\t\t\t\tPrice");
        for (int i = 0; i < dishList.size(); i++) {
            System.out.format("%d\t\t%s\t\t\t%.2f\n", dishList.get(i).getId(), dishList.get(i).getName(), dishList.get(i).getPrice());
        }
        System.out.println("---------------------------");
    }

    /**
     * delete dish
     *
     * @param id
     * @param dishList
     * @param orderedList
     */
    public void deleteDish(int id, List<Dish> dishList, List<Dish> orderedList) {
        if (!orderedList.isEmpty()) {
            orderedList.remove(dishList.get(id - 1));
        }
    }

    /**
     * check cart
     */
    public void showOrderedMenu(List<Dish> orderedList) {
        if (orderedList.isEmpty()) {
            System.out.println("No record, please make an order!");
        } else {
            System.out.println("There is your order");
            for (int i = 0; i < orderedList.size(); i++) {
                System.out.println(orderedList.get(i).getName());
            }
        }
    }

    /**
     * check out
     */
    public void checkout(List<Dish> orderedList) {
        double money;
        money = 0.0;
        if (orderedList.isEmpty()) {
            System.out.println("No record, please make an order!");
        } else {
            System.out.println("Please sit and wait……");
            for (int i = 0; i < orderedList.size(); i++) {
                money += orderedList.get(i).getPrice();
            }
            System.out.format("Checkout Total Amount：￥ %.2f\n", money);
        }
    }
}
