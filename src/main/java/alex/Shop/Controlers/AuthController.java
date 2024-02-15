package alex.Bank.Controlers;

import alex.Bank.models.Person;
import alex.Bank.services.RegistrationService;
import alex.Bank.utils.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    public final RegistrationService registrationService;
    private final PersonValidator personValidator;

    @Autowired
    public AuthController(RegistrationService registrationService, PersonValidator personValidator) {
        this.registrationService = registrationService;
        this.personValidator = personValidator;
    }

    @GetMapping("/registration")
    public String register(@ModelAttribute("person") Person person){
        return "AllRole/auth/Registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("person") @Validated Person person,
                               BindingResult bindingResult){

        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "redirect:/registration";
        }

        registrationService.register(person);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "AllRole/auth/Login";
    }
}
