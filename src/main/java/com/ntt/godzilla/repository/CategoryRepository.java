package com.ntt.godzilla.repository;

import com.ntt.godzilla.entity.Category;
import com.slyak.spring.jpa.GenericJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends GenericJpaRepository<Category,Long> {
    Page<Category> findCategoryByStatus(int status, Pageable pageable);
    Page<Category> findCategoryByStatusAndCategoryNameContaining(int status, String categoryName, Pageable pageable);
    void removeByCategoryId(long id);

    @Modifying
    @Query("UPDATE Category c SET c.status = :categoryStatus WHERE c.categoryId = :categoryId")
    void updateCategoryStatus(@Param("categoryId") long categoryId, @Param("categoryStatus") int categoryStatus);
}
