package group.g22.demostore.service;

import group.g22.demostore.model.TypeProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeProductService {
    Page<TypeProduct> search(Pageable pageable);
}
