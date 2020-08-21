package boot.springbootaplication.service.impl;

import boot.springbootaplication.mapper.ReviewMapper;
import boot.springbootaplication.model.Review;
import boot.springbootaplication.model.dto.ReviewRequestDto;
import boot.springbootaplication.service.CsvParserService;
import boot.springbootaplication.service.FileWorkService;
import boot.springbootaplication.service.ReviewService;
import boot.springbootaplication.service.RoleService;
import boot.springbootaplication.service.UserService;
import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FileWorkServiceImpl implements FileWorkService {
    private static final String USER_PASSWORD = "1234";
    private final CsvParserService csvParserService;
    private final ReviewMapper reviewMapper;
    private final ReviewService reviewService;
    private final UserService userService;
    private final RoleService roleService;

    @Override
    public boolean add(String path) {
        List<ReviewRequestDto> dtos;
        try {
            dtos = csvParserService.readFromFile(path);
        } catch (IOException e) {
            throw new RuntimeException("IOException at fileReaderService", e);
        }
        List<Review> reviews = reviewMapper.getReviewsFromDto(dtos);
        for (Review review : reviews) {
            reviewService.save(review);
        }
        return true;
    }
}
