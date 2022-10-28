import java.util.List;

/**
 * @author jiuzhe
 * @create 2022-10-28-19:42
 */
public interface DishInterface {

    /**
     * display the menu
     *
     * @param dishList menu list
     */
    public void showMenu(List<Dish> dishList);


    /**
     * check cart
     */
    public void showOrderedMenu(List<Dish> orderedList);

    /**
     * delete dish
     *
     * @param id
     * @param dishList
     * @param orderedList
     */
    public void deleteDish(int id, List<Dish> dishList, List<Dish> orderedList);

    /**
     * check out
     */
    public void checkout(List<Dish> orderedList);
}
