package uz.pdp.apppcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apppcmarket.entity.Product;
import uz.pdp.apppcmarket.entity.templated.Result;
import uz.pdp.apppcmarket.payload.ProductDto;
import uz.pdp.apppcmarket.service.ProductService;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PreAuthorize(value = "hasAnyAuthority('READ_ALL')")
    @GetMapping()
    public HttpEntity<?> getAllProduct(){
        List<Product> allProduct = productService.getAllProduct();
        return ResponseEntity.ok(allProduct);
    }

    @PreAuthorize(value = "hasAnyAuthority('READ_ONE')")
    @GetMapping(value = "/{id}")
    public HttpEntity<?> getProductByCategoryId(@PathVariable Integer id){
        Product product = productService.getProductByCategoryId(id);
        return ResponseEntity.ok(product);
    }

    @PreAuthorize(value = "hasAnyAuthority('ADD')")
    @PostMapping()
    public HttpEntity<?> addProduct(@RequestBody ProductDto productDto){
        Result result = productService.addProduct(productDto);
        return ResponseEntity.status(result.getStatus()?201:409).body(result);
    }

    @PreAuthorize(value = "hasAnyAuthority('DELETE')")
    @DeleteMapping(value = "/{id}")
    public HttpEntity<?>deleteProduct(@PathVariable Integer id){
        Result result = productService.deleteProduct(id);
        return ResponseEntity.status(result.getStatus()?202:409).body(result);
    }

    @PreAuthorize(value = "hasAnyAuthority('EDET')")
    @PutMapping(value = "/{id}")
    public HttpEntity<?> edetProduct(@PathVariable Integer id, @RequestBody ProductDto productDto){
        Result result = productService.edetProduct(id, productDto);
        return ResponseEntity.status(result.getStatus()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(result);
    }

}
