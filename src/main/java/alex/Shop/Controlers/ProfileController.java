package alex.Bank.Controlers;

import alex.Bank.models.Person;
import alex.Bank.models.PersonProduct;
import alex.Bank.models.Product;
import alex.Bank.services.PeopleService;
import alex.Bank.services.PersonProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final PeopleService peopleService;
    private final PersonProductService personProductService;

    public ProfileController(PeopleService peopleService, PersonProductService personProductService) {
        this.peopleService = peopleService;
        this.personProductService = personProductService;
    }


    @GetMapping("/{userName}")
    public String show(@PathVariable("userName") String userName, Model model){
        model.addAttribute("person", peopleService.findByUserName(userName));
        model.addAttribute("products", peopleService.getProductByPersonUserName(userName));
        return "User/Profile/Profile";
    }

    @GetMapping("/{userName}/edit")
    public String edit(Model model, @PathVariable("userName") String userName){
        model.addAttribute("person", peopleService.findByUserName(userName));
        return "User/Profile/EditProfile";
    }

    @PatchMapping("/{userName}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("userName") String userName){
        peopleService.update(userName, person);
        return "redirect:/profile/{userName}";
    }

    @DeleteMapping("/{userName}")
    public String delete(@PathVariable("userName") String userName){
        peopleService.deleteByUserName(userName);
        return "redirect:/shop";
    }

    @GetMapping("/ShoppingСart/{userName}")
    public String shoppingCart(Model model, @PathVariable("userName") String userName){
        List<Product> products = peopleService.getProductByPersonUserName(userName);
        model.addAttribute("products", products);
        return "User/Profile/ShoppingСart";
    }
}
