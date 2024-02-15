package alex.Bank.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    public Person(String userName, Date fullDateOfBirthday, String numberPhone, String email, String password, String role) {
        this.userName = userName;
        this.fullDateOfBirthday = fullDateOfBirthday;
        this.numberPhone = numberPhone;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public Person(){

    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "full_date_of_birthday")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(fallbackPatterns = "yyyy-MM-dd")
    private Date fullDateOfBirthday;
    @Column(name = "number_phone")
    private String numberPhone;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @Column(name = "images")
    private String images;
    @ManyToMany
    @JoinTable(
            name = "person_product",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getFullDateOfBirthday() {
        return fullDateOfBirthday;
    }

    public void setFullDateOfBirthday(Date fullDateOfBirthday) {
        this.fullDateOfBirthday = fullDateOfBirthday;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getEmail() {
        return email;
    }
    public String getRole() {
        return role;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", fullDateOfBirthday=" + fullDateOfBirthday +
                ", numberPhone='" + numberPhone + '\'' +
                ", email='" + email + '\'' +
                ", products=" + products +
                '}';
    }
}
