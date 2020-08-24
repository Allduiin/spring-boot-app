package boot.springbootaplication.mapper;

import boot.springbootaplication.model.dto.ProductResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponseDto getProductResponseDtoFromReview(Object[] objects) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId((String) objects[0]);
        dto.setNumberOfReviews((Long) objects[1]);
        return dto;
    }
}
