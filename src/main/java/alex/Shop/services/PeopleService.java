package alex.Bank.services;

import alex.Bank.models.Person;
import alex.Bank.models.Product;
import alex.Bank.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository){
        this.peopleRepository = peopleRepository;
    }
    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public void setRoleByUserName(String userName, String role){
        peopleRepository.findByUserName(userName);
        peopleRepository.updateRoleByUserName(userName, role);

    }

    public Person findOne(int id){
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    public Person findByUserName(String userName){
        Optional<Person> foundPerson = peopleRepository.findByUserName(userName);
        return foundPerson.orElse(null);
    }

    public void deleteByUserName(String userName){
        Optional<Person> person = peopleRepository.findByUserName(userName);
        peopleRepository.deleteById(person.get().getId());
    }

    public void save(Person person){
        peopleRepository.save(person);
    }

    public void update(String userName, Person updatePerson){
        updatePerson.setUserName(userName);
        updatePerson.setId(peopleRepository.findByUserName(userName).get().getId());
        peopleRepository.save(updatePerson);
    }

    public List<Product> getProductByPersonUserName(String userName){
        Optional<Person> person = peopleRepository.findByUserName(userName);
        if (person.isEmpty()){
            Hibernate.initialize(person.get().getProducts());
        }
        return person.get().getProducts();
    }

    @Transactional
    public void addProductByUserName(String userName, Product product){
        System.out.println("я работаю");
        if (userName.equals(IsLoginNow()) && !userName.equals("anonymousUser")) {
            Optional<Person> person =  peopleRepository.findByUserName(userName);
            product.setOwner(Collections.singletonList(person.get()));
        }
    }

    public String IsLoginNow(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
