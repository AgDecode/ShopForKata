package alex.Bank.Controlers;

import alex.Bank.models.Person;
import alex.Bank.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ADMIN/ManagePeople")
public class AdminPeopleController {
    private final PeopleService peopleService;

    @Autowired
    public AdminPeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "Admin/AdminPeople/ManagePeople";
    }

    @PostMapping("/ChangeRole/{userName}")
    public String setNewRole(@PathVariable String userName, @RequestParam String role) {
        peopleService.setRoleByUserName(userName,role);
        return "redirect:/ADMIN/ManagePeople";
    }

    @DeleteMapping("/DeletePerson/{userName}")
    public String deleteUser(@PathVariable String userName) {
        peopleService.deleteByUserName(userName);
        return "redirect:/ADMIN/ManagePeople";
    }
}
