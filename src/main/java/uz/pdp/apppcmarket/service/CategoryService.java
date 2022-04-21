package uz.pdp.apppcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uz.pdp.apppcmarket.entity.Category;
import uz.pdp.apppcmarket.entity.templated.Result;
import uz.pdp.apppcmarket.payload.CategoryDto;
import uz.pdp.apppcmarket.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    /*
        Add Category
     */
    public Result addCategory(CategoryDto categoryDto){
        boolean exits = categoryRepository.existsByName(categoryDto.getName());
        if (exits)
            return new Result("Category exits!", false);
        Category category = new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId()!=null){
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalCategory.isPresent())
                return new Result("Category does not exist", false);
            Category categoryParent = optionalCategory.get();
            category.setParentCategory(categoryParent);
            category.setStatus(categoryDto.getStatus());

        }
        categoryRepository.save(category);
        return new Result("Save category success!", true);

    }
    /*
      Get All Category
     */
    public List<Category> getAllCategory(){
        List<Category> list = categoryRepository.findAll();
        return list;
    }
    /*
           Category By ParentCategoryId
     */
    public List<Category> getAllCategoryByParentCategoryId(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        Category category = optionalCategory.orElse(null);
        List<Category> categoryList = categoryRepository.findTopByParentCategory(category);
        return categoryList;
    }

    public Result deleteCategory(Integer id){
        try{
            categoryRepository.deleteById(id);
            return new Result("Deleted success!", true);
        }catch (Exception e){
            return new Result("No deleted!", false);
        }
    }

    public Result edetCategory(Integer id, CategoryDto categoryDto){
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (!categoryOptional.isPresent())
            return new Result("Not found category", false);
        Category category = categoryOptional.get();
        category.setName(categoryDto.getName());
        category.setStatus(categoryDto.getStatus());
        if (categoryDto.getParentCategoryId()!=null){
            Optional<Category> categoryParent = categoryRepository.findById(categoryDto.getParentCategoryId());
            Category categoryParentOne = categoryParent.get();
            category.setParentCategory(categoryParentOne);
        }
        categoryRepository.save(category);
        return new Result("Save category", true);
    }
}
