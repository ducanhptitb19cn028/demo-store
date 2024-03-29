package group.g22.demostore.service;

import group.g22.demostore.model.TypeProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeProductService {
    Page<TypeProduct> search(Pageable pageable);

    Boolean save(TypeProduct typeProduct);

    void delete(Long id);

    TypeProduct detail(Long id);

    Boolean update(TypeProduct typeProduct);
}
