package org.naamtamilar.magazine.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/product-page")
    public String showProductPage() {
        logger.info("Showing the product page");
        return "productPage";
    }

}
