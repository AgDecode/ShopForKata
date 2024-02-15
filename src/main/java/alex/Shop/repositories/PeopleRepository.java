package alex.Bank.repositories;

import alex.Bank.models.Person;
import alex.Bank.models.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person,Integer> {
    Optional<Person> findByUserName(String userName);
    @Modifying
    @Query("UPDATE Person p SET p.role = :role WHERE p.userName = :userName")
    @Transactional
    void updateRoleByUserName(@Param("userName") String userName, @Param("role") String role);
}
