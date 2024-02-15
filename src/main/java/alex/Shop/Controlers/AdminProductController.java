package alex.Bank.Controlers;

import alex.Bank.models.Product;
import alex.Bank.services.ProductsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ADMIN/product")
public class AdminProductController {

    private final ProductsService productsService;

    public AdminProductController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping()
    public String allProduct(Model model){
        model.addAttribute("product", productsService.findAll());
        return "Admin/AdminProduct/ManageProducts";
    }

    @GetMapping("/{id}/edit")
    public String editProducts(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productsService.findOne(id));
        return "Admin/AdminProduct/EditProduct";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("product") Product product, @PathVariable("id") int id){
        productsService.update(id, product);
        return "redirect:/shop";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        productsService.delete(id);
        return "redirect:/shop";
    }

    @GetMapping("/new")
    public String newProductsPage(){
        return "Admin/AdminProduct/NewProduct";
    }

    @PostMapping("/new")
    public String newProductsAdd(           @RequestParam("name") String name,
                                            @RequestParam("type") String type,
                                            @RequestParam("price") int price,
                                            @RequestParam("info") String info,
                                            @RequestParam("countInInventory") int countInInventory,
                                            @RequestParam("isAllowedForChildren") boolean allowedForChildren,
                                            @RequestParam("imagePath") String imagePath){
        Product product = new Product(name, type, price, info, countInInventory, allowedForChildren, imagePath);
        System.out.println(product);
        productsService.save(product);
        return "redirect:/shop";
    }
}
