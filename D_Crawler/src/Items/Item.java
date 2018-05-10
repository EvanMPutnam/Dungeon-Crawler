package Items;

import Placement.Player;

/**
 * Created by evanputnam on 8/18/17.
 * //TODO update stat read offs for each item
 */
public interface Item {

    String getItemName();
    String getStatReadOff();
    void useItem(Player player);
    boolean isEquiped();
}
