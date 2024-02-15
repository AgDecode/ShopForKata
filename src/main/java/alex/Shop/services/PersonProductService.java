package alex.Bank.services;

import alex.Bank.models.Person;
import alex.Bank.models.PersonProduct;
import alex.Bank.models.Product;
import alex.Bank.repositories.PersonProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonProductService {
    private final PersonProductRepository personProductRepository;
    private final PeopleService peopleService;

    public PersonProductService(PersonProductRepository personProductRepository, PeopleService peopleService) {
        this.personProductRepository = personProductRepository;
        this.peopleService = peopleService;
    }
    public void save(PersonProduct personProduct, int count){
        personProduct.setCountProduct(personProduct.getCountProduct() + count);
        personProductRepository.save(personProduct);
    }

    public List<Product> allPersonProduct(String userName){
        Person person = peopleService.findByUserName(userName);
        List<PersonProduct> personProductList = personProductRepository.findByPerson(person);

        List<Product> products = null;

        for (PersonProduct i : personProductList){
            products.add(i.getProduct());
        }
        return products;
    }

    public boolean personHaveProduct(String userName, int productID){
        List<Product> products = allPersonProduct(userName);
        for (Product i : products){
            if(i.getId() == productID){
                return true;
            }
        }
        return false;
    }
}
