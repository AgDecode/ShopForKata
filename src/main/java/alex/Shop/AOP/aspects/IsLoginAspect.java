package alex.Bank.AOP.aspects;

import alex.Bank.models.Person;
import alex.Bank.repositories.PeopleRepository;
import alex.Bank.services.PeopleService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Optional;

@Aspect
@Component
public class IsLoginAspect {

    private final PeopleRepository peopleRepository;
    private final PeopleService peopleService;

    public IsLoginAspect(PeopleRepository peopleRepository, PeopleService peopleService) {
        this.peopleRepository = peopleRepository;
        this.peopleService = peopleService;
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMappingMethods() {
    }


    @Before("getMappingMethods()")
    public void addIsLoggedInAttribute(JoinPoint joinPoint) {

        String userName = peopleService.IsLoginNow();
        Optional<Person> person = peopleRepository.findByUserName(userName);

        boolean isLoggedIn = !userName.equals("anonymousUser");
        boolean isAdminRole = false;
        if (!person.isEmpty()){
            isAdminRole = person.get().getRole().equals("ROLE_ADMIN");
        }

        // Получаем объект Model из аргументов метода контроллера
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            if (arg instanceof Model model) {
                model.addAttribute("isLoggedIn", isLoggedIn);
                model.addAttribute("userName", userName);
                model.addAttribute("isAdminRole", isAdminRole);
                break;
            }
        }
    }
}
