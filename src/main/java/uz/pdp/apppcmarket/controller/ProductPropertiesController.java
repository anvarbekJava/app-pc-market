package uz.pdp.apppcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apppcmarket.entity.ProductProperties;
import uz.pdp.apppcmarket.entity.templated.Result;
import uz.pdp.apppcmarket.payload.ProductPropertiesDto;
import uz.pdp.apppcmarket.service.ProductPropertiesService;

import java.util.List;

@RestController
@RequestMapping(value = "/properties")
public class ProductPropertiesController {
    @Autowired
    ProductPropertiesService productPropertiesService;

    @PostMapping()
    public HttpEntity<?> addProperties(@RequestBody ProductPropertiesDto propertiesDto){
        Result result = productPropertiesService.addProductProperties(propertiesDto);
        return ResponseEntity.status(result.getStatus()?201:409).body(result);
    }

    @GetMapping
    public HttpEntity<?> getAllProperties(){
        List<ProductProperties> allProperties = productPropertiesService.getAllProperties();
        return ResponseEntity.ok(allProperties);
    }

    @GetMapping(value = "/{id}")
    public HttpEntity<?> getPropertiesById(@PathVariable Integer id){
        ProductProperties properties = productPropertiesService.getPropertiesById(id);
        return ResponseEntity.ok(properties);
    }

    @DeleteMapping(value = "/{id}")
    public HttpEntity<?> deleteProperties(@PathVariable Integer id){
        Result result = productPropertiesService.deleteProperties(id);
        return ResponseEntity.status(result.getStatus()?203:409).body(result);
    }

    @PutMapping(value = "/{id}")
    public HttpEntity<?> edetProperties(@PathVariable Integer id, @RequestBody ProductPropertiesDto propertiesDto){
        Result result = productPropertiesService.edetProperties(id, propertiesDto);
        return ResponseEntity.status(result.getStatus()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(result);
    }
}

