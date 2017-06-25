package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.*;

/**
 * Created by evan_ on 24/06/2017.
 */
@Entity
public class Items extends Model{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_gen")
    private String productId;
    private String title;
    private double cost;
    private String description;
    private String catagory;


    public static Finder<String, Items> find = new Finder<String, Items>(Items.class);

    public static List<Items> findAll(){
        return Items.find.all();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }
}
