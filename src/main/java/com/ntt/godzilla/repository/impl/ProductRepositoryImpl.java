package com.ntt.godzilla.repository.impl;

import com.ntt.godzilla.annotation.GozTransaction;
import com.ntt.godzilla.entity.Product;
import com.ntt.godzilla.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository  {
    @PersistenceContext
    private EntityManager entityManager;
    @GozTransaction
    @Override
    public void insertProduct(Product product) {
        entityManager.createNativeQuery("INSERT INTO product (product_name, product_code, description, price, discount,product_id) VALUES (?,?,?,?,?,?)")
                .setParameter(1, product.getProductName())
                .setParameter(2, product.getProductCode())
                .setParameter(3, product.getDescription())
                .setParameter(4, product.getPrice())
                .setParameter(5, product.getDiscount())
                .setParameter(6, product.getProductId())
                .executeUpdate();
    }

    @Override
    public List<Product> findAllProducts() {
        return entityManager.createQuery("FROM Product").getResultList();
    }
}
