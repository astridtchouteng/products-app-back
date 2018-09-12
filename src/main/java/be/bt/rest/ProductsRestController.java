package be.bt.rest;

import be.bt.domain.Product;
import be.bt.repository.IProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/products")
public class ProductsRestController {

    @Autowired
    private IProductsRepository repository;

    @GetMapping("")
    
    public List<Product> getAll(){
        return repository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> add(@RequestBody Product product){
        Product result = repository.save(product);

        return (result != null) ? new ResponseEntity<>(result, HttpStatus.OK)
                                : new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
