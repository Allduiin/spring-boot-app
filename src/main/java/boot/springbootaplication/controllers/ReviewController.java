package boot.springbootaplication.controllers;

import boot.springbootaplication.mapper.ReviewMapper;
import boot.springbootaplication.model.dto.ReviewRequestDto;
import boot.springbootaplication.model.dto.ReviewResponseDto;
import boot.springbootaplication.service.ReviewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewMapper reviewMapper;
    private final ReviewService reviewService;

    public ReviewController(ReviewMapper reviewMapper, ReviewService reviewService) {
        this.reviewMapper = reviewMapper;
        this.reviewService = reviewService;
    }

    @PostMapping("/add")
    public void addReview(@RequestBody ReviewRequestDto dto) {
        reviewService.save(reviewMapper.getReviewFromReviewRequestDto(dto));
    }

    @GetMapping("/get")
    public ReviewResponseDto getReviewById(@RequestParam Long id) {
        return reviewMapper.getReviewResponseDtoFromReview(reviewService.getById(id));
    }
}
