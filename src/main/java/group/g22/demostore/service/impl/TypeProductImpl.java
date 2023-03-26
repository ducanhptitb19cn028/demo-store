package group.g22.demostore.service.impl;

import group.g22.demostore.enums.TypeProductStatusOption;
import group.g22.demostore.model.Employee;
import group.g22.demostore.model.TypeProduct;
import group.g22.demostore.repository.TypeProductRepository;
import group.g22.demostore.service.TypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TypeProductImpl implements TypeProductService {
    @Autowired
    private TypeProductRepository typeProductRepository;


    @Override
    public List<TypeProduct> findAll() {
        return typeProductRepository.findAll();
    }

    @Override
    public Page<TypeProduct> search(Pageable pageable) {
        return typeProductRepository.search(pageable);
    }

    @Override
    public String save(TypeProduct typeProduct) {
        /**Nếu là tạo thì không có id*/
        if (typeProduct.getTypeProductId() == null) {
            typeProduct.setCreateDate(LocalDateTime.now());
        }
        if (typeProduct.getTypeProductStatus() == TypeProductStatusOption.CLOSE.getValue()) {
            typeProduct.setStopDate(LocalDateTime.now());
        } else {
            typeProduct.setStopDate(null);
        }
        typeProductRepository.save(typeProduct);
        return "Create success";
    }

    @Override
    public void delete(Long id) {
        TypeProduct typeProduct = typeProductRepository.findById(id).orElseThrow(() -> new RuntimeException());
        typeProductRepository.delete(typeProduct);
    }

    @Override
    public TypeProduct detail(Long id) {
        return typeProductRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public String update(TypeProduct typeProduct) {
        TypeProduct typeProductUpdate = typeProductRepository.findById(typeProduct.getTypeProductId()).orElseThrow(() -> new RuntimeException());

        typeProductUpdate.setTypeProductCode(typeProduct.getTypeProductCode());
        typeProductUpdate.setTypeProductName(typeProduct.getTypeProductName());
        typeProductUpdate.setTypeProductStatus(typeProduct.getTypeProductStatus());
        typeProductUpdate.setStopDate(
                typeProduct.getTypeProductStatus() == TypeProductStatusOption.CLOSE.getValue()
                        ? LocalDateTime.now()
                        : null
        );

        typeProductRepository.save(typeProductUpdate);
        return "Update success";
    }

    @Override
    public TypeProduct getTypeProductById(long id) {
        Optional<TypeProduct> optionalTypeProduct = typeProductRepository.findById(id);
        TypeProduct typeProduct;
        if (optionalTypeProduct.isPresent()) {
            typeProduct = optionalTypeProduct.get();
        } else {
            throw new RuntimeException("Employee not found for id:" + id);
        }
        return typeProduct;
    }
}
