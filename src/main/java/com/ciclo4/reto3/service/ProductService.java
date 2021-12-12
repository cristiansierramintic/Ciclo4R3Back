package com.ciclo4.reto3.service;

import com.ciclo4.reto3.model.Product;
import com.ciclo4.reto3.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(int id) {
        return productRepository.getProduct(id);
    }

    public Product save(Product product) {

        Optional<Product> productIdMaxima = productRepository.lastProductId();

        if (product.getId() == null) {
            if (productIdMaxima.isEmpty()) {
                product.setId(1);
            } else {
                product.setId(productIdMaxima.get().getId() + 1);
            }
        }

        Optional<Product> productAux = productRepository.getProduct(product.getId());
        if (productAux.isEmpty()) {
            return productRepository.create(product);
        } else {
            return product;
        }
    }

    public Product update(Product product) {

        if (product.getId() != null) {
            Optional<Product> productAux = productRepository.getProduct(product.getId());
            if (!productAux.isEmpty()) {
                if (product.getBrand() != null) {
                    productAux.get().setBrand(product.getBrand());
                }
                if (product.getProcesor() != null) {
                    productAux.get().setProcesor(product.getProcesor());
                }
                if (product.getOs() != null) {
                    productAux.get().setOs(product.getOs());
                }
                if (product.getDescription() != null) {
                    productAux.get().setDescription(product.getDescription());
                }
                if (product.getMemory() != null) {
                    productAux.get().setMemory(product.getMemory());
                }
                if (product.getHardDrive() != null) {
                    productAux.get().setHardDrive(product.getHardDrive());
                }
                if (product.getPrice() != 0.0) {
                    productAux.get().setPrice(product.getPrice());
                }
                if (product.getQuantity() != 0) {
                    productAux.get().setQuantity(product.getQuantity());
                }
                if (product.getPhotography() != null) {
                    productAux.get().setPhotography(product.getPhotography());
                }
                productAux.get().setAvailability(product.isAvailability());
                productRepository.update(productAux.get());
                return productAux.get();
            } else {
                return product;
            }
        } else {
            return product;
        }
    }

    public boolean delete(int id) {
        Boolean aboolean = getProduct(id).map(product -> {
            productRepository.delete(product);
            return true;
        }).orElse(false);
        return aboolean;
    }
}
