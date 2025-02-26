package com.msc.clashroyal.entity;

import java.util.List;

/**
 *
 * @author Michael
 */
public class Items {

    public List<Card> items;
    public List<Card> supportItems;

    public List<Card> combined() {
        items.addAll(this.supportItems);
        return items;
    }
}
