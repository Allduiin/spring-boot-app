package boot.springbootaplication.mapper;

import boot.springbootaplication.model.Review;
import boot.springbootaplication.model.dto.ProductResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponseDto getProductResponseDtoFromReview(Review productInfo) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(productInfo.getProductId());
        dto.setNumberOfReviews(productInfo.getNumberOfReviews());
        return dto;
    }
}
