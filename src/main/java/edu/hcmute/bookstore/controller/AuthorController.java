package edu.hcmute.bookstore.controller;

import edu.hcmute.bookstore.config.LocalVariable;
import edu.hcmute.bookstore.dto.AuthorDTO;
import edu.hcmute.bookstore.dto.CategoryDTO;
import edu.hcmute.bookstore.model.AuthorEntity;
import edu.hcmute.bookstore.model.CategoryEntity;
import edu.hcmute.bookstore.service.impl.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;

@CrossOrigin(origins = "*")   //Để ghép AuthController với các controller khác
@RequestMapping
@RestController
public class AuthorController {
    @Autowired
    AuthorServiceImpl authorService;
    @GetMapping("/author/getAll")
    public ResponseEntity<?> getAllAuthor()
    {
        return ResponseEntity.ok(authorService.getAllAuthor());
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable long id){
        try {
            return ResponseEntity.ok(authorService.findAuthorById(id));
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(LocalVariable.messageCannotFindCat + id, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/admin/author/create")
    public Object createAuthor(@RequestBody AuthorDTO authorDTO) throws ParseException {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setAutName(authorDTO.getAutName());
        authorEntity.setAutAddress(authorDTO.getAutAddress());
        authorEntity.setAutPhone(authorDTO.getAutPhone());
        authorEntity.setAutBiography(authorDTO.getAutBiography());
        authorEntity.setAutEmail(authorDTO.getAutEmail());
        authorEntity.setUpdate_at(new Timestamp(System.currentTimeMillis()));
        authorEntity.setCreate_at(new Timestamp(System.currentTimeMillis()));
        return authorService.save(authorEntity);
    }

    @DeleteMapping("/admin/author/{id}")
    public ResponseEntity<?> deleteAuthorById(@PathVariable long id)
    {
        authorService.deleteAuthorById(id);
        return ResponseEntity.ok(LocalVariable.messageDeleteCatSuccess);
    }
}
