package edu.hcmute.bookstore.controller;

import edu.hcmute.bookstore.config.LocalVariable;
import edu.hcmute.bookstore.dto.AuthorDTO;
import edu.hcmute.bookstore.dto.PubliserDTO;
import edu.hcmute.bookstore.model.AuthorEntity;
import edu.hcmute.bookstore.model.CategoryEntity;
import edu.hcmute.bookstore.model.PublisherEntity;
import edu.hcmute.bookstore.service.impl.AuthorServiceImpl;
import edu.hcmute.bookstore.service.impl.CategoryServiceImpl;
import edu.hcmute.bookstore.service.impl.PublisherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;

@CrossOrigin(origins = "*")   //Để ghép AuthController với các controller khác
@RequestMapping
@RestController
public class PublisherController {
    @Autowired
    PublisherServiceImpl publisherService;

    @GetMapping("/publisher/getAll")
    public ResponseEntity<?> getAllPublisher()
    {
        return ResponseEntity.ok(publisherService.getAllPublisher());
    }

    @GetMapping("/publisher/{id}")
    public ResponseEntity<?> getPublisherById(@PathVariable long id){
        try {
            return ResponseEntity.ok(publisherService.findPublisherById(id));
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(LocalVariable.messageCannotFindCat + id, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/admin/publisher/create")
    public Object createPublisher(@RequestBody PubliserDTO publisherEntity) throws ParseException {
        PublisherEntity publisher = new PublisherEntity();
        publisher.setPubName(publisherEntity.getPubName());
        publisher.setPubPhone(publisherEntity.getPubPhone());
        publisher.setPubAddress(publisherEntity.getPubAddress());
        publisher.setPubEmail(publisherEntity.getPubEmail());
        publisher.setUpdate_at(new Timestamp(System.currentTimeMillis()));
        publisher.setCreate_at(new Timestamp(System.currentTimeMillis()));

        return publisherService.save(publisher);
    }

    @DeleteMapping("/admin/publisher/{id}")
    public ResponseEntity<?> deletePublisherById(@PathVariable long id)
    {
        publisherService.deletePublisherById(id);
        return ResponseEntity.ok(LocalVariable.messageDeleteCatSuccess);
    }
}
