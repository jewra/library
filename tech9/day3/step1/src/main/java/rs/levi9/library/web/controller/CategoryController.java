package rs.levi9.library.web.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.levi9.library.domain.Category;
import rs.levi9.library.service.CategoryService;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findOne(@PathVariable("id") Long id) {
        Category category = categoryService.findOne(id);
        if (category == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(category, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Category save(@Valid @RequestBody Category category) {
        return categoryService.save(category);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public Category update(@Valid @RequestBody Category category) {
        return categoryService.save(category);
    }

}
