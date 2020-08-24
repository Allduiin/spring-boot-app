package boot.springbootaplication.service;

import java.util.List;

public interface ProductService {
    List<Object[]> findMostReviewedProducts(int limit, int offset);
}
