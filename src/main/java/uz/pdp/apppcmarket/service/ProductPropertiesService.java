package uz.pdp.apppcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apppcmarket.entity.Product;
import uz.pdp.apppcmarket.entity.ProductProperties;
import uz.pdp.apppcmarket.entity.templated.Result;
import uz.pdp.apppcmarket.payload.ProductPropertiesDto;
import uz.pdp.apppcmarket.repository.ProductPropertiesRepository;
import uz.pdp.apppcmarket.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductPropertiesService {
    @Autowired
    ProductPropertiesRepository productPropertiesRepository;

    @Autowired
    ProductRepository productRepository;

    public Result addProductProperties(ProductPropertiesDto propertiesDto){
        boolean exist = productPropertiesRepository.existsByKeyAndValueAndProduct_Id(propertiesDto.getKey(),
                propertiesDto.getValue(), propertiesDto.getProductId());
        if (exist)
            return new Result("Properties exsits", false);
        ProductProperties properties = new ProductProperties();
        properties.setKey(propertiesDto.getKey());
        properties.setValue(propertiesDto.getValue());

        Optional<Product> optionalProduct = productRepository.findById(propertiesDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Product not found create product", false);

        Product product = optionalProduct.get();

        properties.setProduct(product);

        productPropertiesRepository.save(properties);
        return new Result("Save Product properties", true);
    }

    public List<ProductProperties> getAllProperties(){
        List<ProductProperties> propertiesList = productPropertiesRepository.findAll();
        return propertiesList;
    }

    public ProductProperties getPropertiesById(Integer id){
        Optional<ProductProperties> optional = productPropertiesRepository.findById(id);
        return optional.orElse(null);
    }

    public Result edetProperties(Integer id, ProductPropertiesDto propertiesDto){
        Optional<ProductProperties> optionalproperties = productPropertiesRepository.findById(id);
        if (!optionalproperties.isPresent())
            return new Result("Properties not found", false);
        ProductProperties properties = optionalproperties.get();
        properties.setKey(propertiesDto.getKey());
        properties.setValue(properties.getValue());

        Optional<Product> optionalProduct = productRepository.findById(propertiesDto.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Product not found create product", false);
        Product product = optionalProduct.get();
        properties.setProduct(product);
        return new Result("Update properties", true);
    }

    public Result deleteProperties(Integer id){
        try {
            productPropertiesRepository.deleteById(id);
            return new Result("Deleted properties", true);
        }catch (Exception e){
            return new Result("No deleted", false);
        }
    }
}
