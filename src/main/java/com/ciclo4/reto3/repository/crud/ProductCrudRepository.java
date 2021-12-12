package com.ciclo4.reto3.repository.crud;

import com.ciclo4.reto3.model.Product;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductCrudRepository extends MongoRepository<Product, Integer>{
    
    //Para seleccionar el producto con el id maximo
    Optional<Product> findTopByOrderByIdDesc();
}
