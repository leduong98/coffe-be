package com.example.coffeebe.domain.services.impl.business;

import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.app.dtos.request.FilterDto;
import com.example.coffeebe.app.dtos.request.impl.ProductDto;
import com.example.coffeebe.app.dtos.request.impl.ProductFilterDto;
import com.example.coffeebe.app.dtos.responses.CustomPage;
import com.example.coffeebe.app.dtos.responses.ProductResponse;
import com.example.coffeebe.domain.entities.business.Category;
import com.example.coffeebe.domain.entities.business.Product;
import com.example.coffeebe.domain.services.BaseService;
import com.example.coffeebe.domain.services.impl.BaseAbtractService;
import com.example.coffeebe.domain.utils.exception.CustomErrorMessage;
import com.example.coffeebe.domain.utils.exception.CustomException;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ProductService extends BaseAbtractService implements BaseService<Product, Long> {

    @Override
    public CustomPage<Product> findAll(Pageable pageable) {
        Page<Product> productPage =  productRepository.findAll(pageable);
        return new CustomPage<>(productPage);
    }

    public CustomPage<ProductResponse> findAllByProductFilter(ProductFilterDto productFilterDto, Pageable pageable){
        Page<Product> productPage = productRepository.findAllByProductFilter(productFilterDto, pageable);
        CustomPage<ProductResponse> productCustomPage = new CustomPage<>();
        productCustomPage.setData(productPage.getContent().stream().map(ele -> modelMapper.map(ele, ProductResponse.class)).collect(Collectors.toList()));
        productCustomPage.setMetadata(new CustomPage.Metadata(productPage));

        return productCustomPage;
    }

    @Override
    public Product findById(HttpServletRequest request, Long id) {
        return getProductById(id);
    }

    @Override
    public Product create(HttpServletRequest request, DTO dto) {
        ProductDto productDto = modelMapper.map(dto, ProductDto.class);
        if (productRepository.existsByName(productDto.getName())){
            throw new CustomException(HttpStatus.BAD_REQUEST, CustomErrorMessage.NAME_EXISTS);
        }
        Category category = getCategoryById(productDto.getCategoryId());
        Product product = Product.builder()
                .category(category)
                .detail(productDto.getDetail())
                .name(productDto.getName())
                .image(productDto.getImage())
                .imageList(productDto.getImageList())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .description(productDto.getDescription())
                .detail(productDto.getDetail())
                .build();
        return productRepository.save(product);
    }

    @Override
    public Product update(HttpServletRequest request, Long id, DTO dto) {
        Product product = findById(request, id);
        ProductDto productDto = modelMapper.map(dto, ProductDto.class);
        Category category = getCategoryById(productDto.getCategoryId());
        product.setName(productDto.getName());
        product.setCategory(category);
        product.setDetail(productDto.getDetail());
        product.setImage(productDto.getImage());
        product.setImageList(productDto.getImageList());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        return productRepository.save(product);
    }

    @Override
    public boolean delete(HttpServletRequest request, Long id) {
        Product product = findById(request, id);
        productRepository.delete(product);
        return true;
    }

    @Override
    public Page<Product> filter(FilterDto<Product> dto, Pageable pageable) {
        return null;
    }

    @Override
    public List<Product> filter(HttpServletRequest request) {
        return null;
    }
}