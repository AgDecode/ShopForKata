package alex.Bank.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Product")
public class Product {
    public Product(String name, String type, int price, String info, int countInInventory, boolean isAllowedForChildren, String imagePath) {
        this.name = name;
        this.type = type;
        this.imagePath = imagePath;
        this.price = price;
        this.info = info;
        this.countInInventory = countInInventory;
        this.isAllowedForChildren = isAllowedForChildren;
    }

    public Product() {

    }


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "image_path")
    private String imagePath;
    @Column(name = "price")
    private int price;
    @Column(name = "info")
    private String info;
    @Column(name = "count_in_inventory")
    private int countInInventory;
    @Column(name = "is_allowed_for_children")
    private boolean isAllowedForChildren;
    @ManyToMany(mappedBy = "products")
    private List<Person> owners;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCountInInventory() {
        return countInInventory;
    }

    public void setCountInInventory(int countInInventory) {
        this.countInInventory = countInInventory;
    }

    public boolean isAllowedForChildren() {
        return isAllowedForChildren;
    }

    public void setAllowedForChildren(boolean allowedForChildren) {
        isAllowedForChildren = allowedForChildren;
    }

    public List<Person> getOwners() {
        return owners;
    }

    public void setOwner(List<Person> owners) {
        this.owners = owners;
    }

}
