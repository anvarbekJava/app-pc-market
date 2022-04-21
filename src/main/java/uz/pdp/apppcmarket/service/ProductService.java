package uz.pdp.apppcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apppcmarket.entity.Attachment;
import uz.pdp.apppcmarket.entity.Category;
import uz.pdp.apppcmarket.entity.Product;
import uz.pdp.apppcmarket.entity.templated.Result;
import uz.pdp.apppcmarket.payload.ProductDto;
import uz.pdp.apppcmarket.repository.AttachmentRepository;
import uz.pdp.apppcmarket.repository.CategoryRepository;
import uz.pdp.apppcmarket.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public Result addProduct(ProductDto productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("Not found category", false);
        Category category = optionalCategory.get();

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getAttachmentId());
        if (!optionalAttachment.isPresent())
            return new Result("Not found attachment", false);
        Attachment attachment = optionalAttachment.get();

        product.setAttachment(attachment);
        product.setCategory(category);

        productRepository.save(product);
        return new Result("Save product", true);
    }

    public List<Product> getAllProduct(){
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    public Product getProductByCategoryId(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    public Result deleteProduct(Integer id){
        try{
            productRepository.deleteById(id);
            return new Result("Deleted product", true);
        }catch (Exception e){
            return new Result("No deleted", false);
        }
    }

    public Result edetProduct(Integer id, ProductDto productDto){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new Result("Not found product", false);
        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("Not found category", false);
        Category category = optionalCategory.get();

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getAttachmentId());
        if (!optionalAttachment.isPresent())
            return new Result("Not found attachment", false);
        Attachment attachment = optionalAttachment.get();

        product.setAttachment(attachment);
        product.setCategory(category);
        return new Result("Save product", true);
    }
}
