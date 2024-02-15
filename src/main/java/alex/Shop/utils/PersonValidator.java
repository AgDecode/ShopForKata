package alex.Bank.utils;

import alex.Bank.models.Person;
import alex.Bank.repositories.PeopleRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private final PeopleRepository peopleRepository;

    public PersonValidator(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors){
        Person person = (Person) target;


        Optional<Person> trust =  peopleRepository.findByUserName(person.getUserName());

        if (trust.isEmpty())
            return;

        errors.rejectValue("userName", "", "Пользователь с таким именем уже есть");
    }
}
