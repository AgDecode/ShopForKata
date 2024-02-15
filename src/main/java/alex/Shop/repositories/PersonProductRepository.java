package alex.Bank.repositories;

import alex.Bank.models.Person;
import alex.Bank.models.PersonProduct;
import alex.Bank.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonProductRepository extends JpaRepository<PersonProduct, Integer> {
    List<PersonProduct> findByPerson(Person person);
}
