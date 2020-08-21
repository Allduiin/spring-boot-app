package boot.springbootaplication.controllers;

import boot.springbootaplication.mapper.UserMapper;
import boot.springbootaplication.model.dto.ProductResponseDto;
import boot.springbootaplication.service.ProductService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final UserMapper userMapper;

    @GetMapping
    public List<ProductResponseDto> getMostActiveUsers(
            @RequestParam(defaultValue = "1000") int limit,
            @RequestParam(defaultValue = "0") int offset) {
        return productService.findMostReviewedProducts(limit, offset);
    }
}
