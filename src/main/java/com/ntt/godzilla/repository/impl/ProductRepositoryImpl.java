package com.ntt.godzilla.repository.impl;

import com.ntt.godzilla.annotation.GozTransaction;
import com.ntt.godzilla.entity.Product;
import com.ntt.godzilla.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository  {
    @PersistenceContext
    private EntityManager entityManager;
    @GozTransaction
    @Override
    public void insertProduct(Product product) {
        entityManager.createNativeQuery("INSERT INTO dbo_product (product_name, product_code, description, price, discount) " +
                        "                   VALUES (?,?,?,?,?)")
                .setParameter(1, product.getProductName())
                .setParameter(2, product.getProductCode())
                .setParameter(3, product.getDescription())
                .setParameter(4, product.getPrice())
                .setParameter(5, product.getDiscount())
                .executeUpdate();
    }

    @Override
    public List<Product> findAllProducts() {
        return entityManager.createNativeQuery("SELECT * FROM dbo_product").getResultList();
    }

    @Override
    public Long countAllProducts() {
        Query query = entityManager.createNativeQuery("SELECT COUNT(*)  FROM dbo_product");
        long count = ((Number) query.getSingleResult()).intValue();
        return count;
    }
}
