package boot.springbootaplication.service;

import boot.springbootaplication.model.dto.ProductResponseDto;
import java.util.List;

public interface ProductService {
    List<ProductResponseDto> findMostReviewedProducts(int limit, int offset);
}
