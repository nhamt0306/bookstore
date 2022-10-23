package edu.hcmute.bookstore.controller;

import edu.hcmute.bookstore.config.LocalVariable;
import edu.hcmute.bookstore.dto.CategoryDTO;
import edu.hcmute.bookstore.model.CategoryEntity;
import edu.hcmute.bookstore.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;

@CrossOrigin(origins = "*")   //Để ghép AuthController với các controller khác
@RequestMapping
@RestController
public class CategoryController {
    @Autowired
    CategoryServiceImpl categorySerivce;

    @GetMapping("/category/getAll")
    public ResponseEntity<?> getAllCategory()
    {
        return ResponseEntity.ok(categorySerivce.getAllCategory());
    }

    @GetMapping("/category/getAllActive")
    public ResponseEntity<?> getAllCategoryActive()
    {
        return ResponseEntity.ok(categorySerivce.findAllCategoryActive());
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable long id){
        try {
            return ResponseEntity.ok(categorySerivce.findCategoryById(id));
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(LocalVariable.messageCannotFindCat + id, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/category/parentid/{id}")
    public ResponseEntity<?> getCategoryByParent(@PathVariable long id){
        try {
            return ResponseEntity.ok(categorySerivce.getCategoryByCatParentId(id));
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(LocalVariable.messageCannotFindCat + id, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/admin/category/createCategory")
    public Object createCategory(@RequestBody CategoryDTO categoryDTO) throws ParseException {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCatName(categoryDTO.getCatName());
        categoryEntity.setCatParent(categoryDTO.getCatParent());
        categoryEntity.setUpdate_at(new Timestamp(System.currentTimeMillis()));
        categoryEntity.setCreate_at(new Timestamp(System.currentTimeMillis()));
        return categorySerivce.save(categoryEntity);
    }

    @DeleteMapping("/admin/category/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable long id)
    {
        categorySerivce.deleteCategoryById(id);
        return ResponseEntity.ok(LocalVariable.messageDeleteCatSuccess);
    }
}
