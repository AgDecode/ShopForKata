package alex.Bank.Controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class UtilsController {

    //Don't delete unused model.They provide access to AOP methods
    @GetMapping("/aboutUs")
    public String aboutUsPage(Model model){
        return "AllRole/utils/aboutUs";
    }
    @GetMapping("/Contacts")
    public String ContactsPage(Model model){
        return "AllRole/utils/Contacts";
    }
    @GetMapping("/open")
    public String openPage(Model model){
        return "AllRole/utils/open";
    }
}
