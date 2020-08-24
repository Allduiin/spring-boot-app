package boot.springbootaplication.service.impl;

import boot.springbootaplication.repositories.ReviewRepository;
import boot.springbootaplication.service.ProductService;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private ReviewRepository reviewRepository;

    @Override
    public List<Object[]> findMostReviewedProducts(int limit, int offset) {
        PageRequest pageRequest = PageRequest.of(offset, limit);
        return reviewRepository.findMostReviewedProducts(pageRequest);
    }
}
