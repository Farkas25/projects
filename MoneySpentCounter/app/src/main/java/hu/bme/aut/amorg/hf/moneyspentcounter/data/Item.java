package hu.bme.aut.amorg.hf.moneyspentcounter.data;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.Date;

public class Item extends SugarRecord implements Serializable {

    private String itemName;
    private int itemPrice;
    private String buyingPlace;
    private Date buyingDate;

    public Item() {}

    public Item(String name, int price, String place, Date date) {
        itemName = name;
        itemPrice = price;
        buyingPlace = place;
        buyingDate = date;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public String getBuyingPlace() {
        return buyingPlace;
    }

    public Date getBuyingDate() {
        return buyingDate;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setBuyingPlace(String buyingPlace) {
        this.buyingPlace = buyingPlace;
    }

    public void setBuyingDate(Date buyingDate) {
        this.buyingDate = buyingDate;
    }
}