package group.g22.demostore.repository;

import group.g22.demostore.model.TypeProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeProductRepository extends JpaRepository<TypeProduct, Long> {

    @Query("select tp from TypeProduct tp")
    Page<TypeProduct> search(Pageable pageable);
}
