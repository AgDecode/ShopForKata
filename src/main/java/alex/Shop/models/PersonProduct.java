package alex.Bank.models;
import jakarta.persistence.*;

@Entity
@Table(name = "person_product")
public class PersonProduct {

    public PersonProduct(Person person, Product product) {
        this.person = person;
        this.product = product;
    }

    public PersonProduct(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TODO : нет id в таблице
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "count_product")
    private int CountProduct;


    public Person getPerson() {
            return person;
        }
    public void setPerson(Person person) {
        this.person = person;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getCountProduct() {
        return CountProduct;
    }
    public void setCountProduct(int countProduct) {
        CountProduct = countProduct;
    }
}


