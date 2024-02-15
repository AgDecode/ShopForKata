package alex.Bank.services;

import alex.Bank.Config.EncoderBean;
import alex.Bank.Config.SecurityConfig;
import alex.Bank.models.Person;
import alex.Bank.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final EncoderBean encoderBean;
    private final PeopleRepository peopleRepository;

    @Autowired
    public RegistrationService(EncoderBean encoderBean, PeopleRepository peopleRepository) {
        this.encoderBean = encoderBean;
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public void register(Person person){
        person.setPassword(encoderBean.getPasswordEncoder().encode(person.getPassword()));
        person.setRole("ROLE_USER");
        peopleRepository.save(person);
    }
}
