package org.naamtamilar.magazine.controller.admin;

import org.naamtamilar.magazine.domain.Product;
import org.naamtamilar.magazine.service.impl.ProductImageService;
import org.naamtamilar.magazine.service.impl.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Balaji on 27/7/18.
 */
@Controller
public class ProductAdminController extends AdminRootController{
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductAdminController.class);

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductImageService productImageService;

    @RequestMapping(value = "/product-list", method = RequestMethod.GET)
    public String listProduct(Model model) {
        model.addAttribute("products", this.productService.listProducts());
        return "admin/product-list";
    }

    @RequestMapping("/product/showCreatePage")
    public  String showCreatePage(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("playerCategories", productImageService.listPlayerCategory());
        return "product/createProduct";
    }

    //For add and update product both
    @RequestMapping(value= "/product/add", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("product") Product product, Model model){
        LOGGER.info("product : {}",product);
        if(product.getId() == 0){
            //new product, add it
            this.productService.addProduct(product);
            model.addAttribute("createSuccess", true);
        }else{
            //existing product, call update
            model.addAttribute("editSuccess", true);
            this.productService.updateProduct(product);
        }
        return "redirect:/a/product-list";

    }

    @RequestMapping("/product/remove/{id}")
    public String removeproduct(@PathVariable("id") int id){
        this.productService.removeProduct(id);
        return "redirect:/a/product-list";
    }

    @RequestMapping("/product/edit/{id}")
    public String editproduct(@PathVariable("id") int id, Model model){
        model.addAttribute("product", this.productService.getProductById(id));
        model.addAttribute("playerCategories", productImageService.listPlayerCategory());
        return "product/createProduct";
    }

}
