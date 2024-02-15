package alex.Bank.services;

import alex.Bank.models.Person;
import alex.Bank.models.Product;
import alex.Bank.repositories.ProductsRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> findAll(){
        return productsRepository.findAll();
    }

    public Product findOne(int id){
        Optional<Product> foundProduct = productsRepository.findById(id);
        return foundProduct.orElse(null);
    }

    public void delete(int id){
        productsRepository.deleteById(id);
    }

    public void save(Product product){
        productsRepository.save(product);
    }

    public void update(int id, Product updateProduct){
        updateProduct.setId(id);
        productsRepository.save(updateProduct);
    }

}
