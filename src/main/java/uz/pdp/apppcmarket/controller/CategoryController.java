package uz.pdp.apppcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apppcmarket.entity.Category;
import uz.pdp.apppcmarket.entity.templated.Result;
import uz.pdp.apppcmarket.payload.CategoryDto;
import uz.pdp.apppcmarket.service.CategoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PreAuthorize(value = "hasAnyAuthority('READ_ALL')")
    @GetMapping()
    public HttpEntity<?> getAllCategory(){
        List<Category> allCategory = categoryService.getAllCategory();
        return ResponseEntity.ok(allCategory);
    }

    @PreAuthorize(value = "hasAnyAuthority('READ_ONE')")
    @GetMapping(value = "/{id}")
    public HttpEntity<?> getCategoryParrentId(@PathVariable Integer id){
        List<Category> allCategoryByParentCategoryId = categoryService.getAllCategoryByParentCategoryId(id);
        return ResponseEntity.ok(allCategoryByParentCategoryId);
    }

    @PreAuthorize(value = "hasAnyAuthority('ADD')")
    @PostMapping()
    public ResponseEntity<Result> addCategory(@RequestBody CategoryDto categoryDto){
        Result result = categoryService.addCategory(categoryDto);
        return ResponseEntity.status(result.getStatus()?201:409).body(result);
    }

    @PreAuthorize(value = "hasAnyAuthority('DELETE')")
    @DeleteMapping(value = "/{id}")
    public HttpEntity<?> deleteCategory(@PathVariable Integer id){
        Result result = categoryService.deleteCategory(id);
        return ResponseEntity.status(result.getStatus()?202:409).body(result);
    }

    @PreAuthorize(value = "hasAnyAuthority('EDET')")
    @PutMapping(value = "/{id}")
    public HttpEntity<?> edetCategory(@PathVariable Integer id, @RequestBody CategoryDto categoryDto){
        Result result = categoryService.edetCategory(id, categoryDto);
        return ResponseEntity.status(result.getStatus()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(result);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
