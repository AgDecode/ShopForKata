package alex.Bank.Controlers;


import alex.Bank.models.Person;
import alex.Bank.models.PersonProduct;
import alex.Bank.models.Product;
import alex.Bank.services.PeopleService;
import alex.Bank.services.PersonProductService;
import alex.Bank.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {
    private final ProductsService productsService;
    private final PeopleService peopleService;
    private final PersonProductService personProductService;
    @Autowired
    public ShopController(ProductsService productsService, PeopleService peopleService, PersonProductService personProductService) {
        this.productsService = productsService;
        this.peopleService = peopleService;
        this.personProductService = personProductService;
    }

    @GetMapping()
    public String indexProducts(Model model){
        model.addAttribute("products", productsService.findAll());
        return "AllRole/Shop/shop";
    }

    @PostMapping()
    public String addToShoppingCart(@RequestParam("productId") int productId,
                                    @RequestParam("quantity") int quantity){

        String userName = peopleService.IsLoginNow();

        if (userName.equals("anonymousUser")){
            return "redirect:/registration";
        }

        PersonProduct personProduct = new PersonProduct();
        personProduct.setPerson(peopleService.findByUserName(userName));
        personProduct.setProduct(productsService.findOne(productId));
        personProductService.save(personProduct, quantity);

        return "redirect:/shop";
    }

    @GetMapping("/{id}")
    public String showProducts(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person){
        model.addAttribute("product", productsService.findOne(id));

        String userName = peopleService.IsLoginNow();

        if (userName.equals("anonymousUser")){
            return "redirect:/registration";
        }
        return "AllRole/Shop/ProductDetails";
    }

//    @PostMapping()
//    public String addToShoppingCartByShowProducts(@ModelAttribute("productId") int productId){
//
//        String userName = peopleService.IsLoginNow();
//        if (userName.equals("anonymousUser")){
//            return "redirect:/registration";
//        }
//
//        PersonProduct personProduct = new PersonProduct();
//        personProduct.setPerson(peopleService.findByUserName(userName));
//        personProduct.setProduct(productsService.findOne(productId));
//
//        System.out.println(peopleService.findByUserName(userName));
//        System.out.println(productsService.findOne(productId));
//        personProductService.save(personProduct);
//
//        return "redirect:/shop";
//    }
}
