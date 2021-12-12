package com.ciclo4.reto3.repository;

import com.ciclo4.reto3.model.Product;
import com.ciclo4.reto3.repository.crud.ProductCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    @Autowired
    private ProductCrudRepository productCrudRepository;

    public List<Product> getAll(){
        return productCrudRepository.findAll();
    }

    public Optional<Product> getProduct(int id){
        return productCrudRepository.findById(id);
    }

    public Product create(Product product){
        return productCrudRepository.save(product);
    }

    public void update(Product product){
        productCrudRepository.save(product);
    }

    public void delete(Product product){
        productCrudRepository.delete(product);
    }
    public Optional<Product> lastProductId(){
        return productCrudRepository.findTopByOrderByIdDesc();
    }
}

