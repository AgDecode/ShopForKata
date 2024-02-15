package alex.Bank.repositories;

import alex.Bank.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer> {
}
