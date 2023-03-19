package group.g22.demostore.service.impl;

import group.g22.demostore.model.TypeProduct;
import group.g22.demostore.repository.TypeProductRepository;
import group.g22.demostore.service.TypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TypeProductImpl implements TypeProductService {
    @Autowired
    private TypeProductRepository typeProductRepository;
    @Override
    public Page<TypeProduct> search(Pageable pageable) {
        return typeProductRepository.search(pageable);
    }
}
