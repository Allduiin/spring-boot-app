package boot.springbootaplication.service.impl;

import boot.springbootaplication.mapper.ReviewMapper;
import boot.springbootaplication.model.Review;
import boot.springbootaplication.service.FileParserService;
import boot.springbootaplication.service.FileReaderService;
import boot.springbootaplication.service.FileWorkService;
import boot.springbootaplication.service.ReviewService;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FileWorkServiceImpl implements FileWorkService { 
    private FileReaderService fileReaderService;
    private FileParserService fileParserService;
    private ReviewMapper reviewMapper;
    private ReviewService reviewService;

    public FileWorkServiceImpl(FileReaderService fileReaderService,
                               FileParserService fileParserService, ReviewMapper reviewMapper,
                               ReviewService reviewService) {
        this.fileReaderService = fileReaderService;
        this.fileParserService = fileParserService;
        this.reviewMapper = reviewMapper;
        this.reviewService = reviewService;
    }

    @Override
    public boolean add(String path) {
        List<String> strings;
        try {
            strings = fileReaderService.readFromFile(path);
        } catch (IOException e) {
            throw new RuntimeException("IOException at fileReaderService", e);
        }
        List<Review> reviews = reviewMapper.getReviewFromDto(fileParserService.parse(strings));
        for (Review review : reviews) {
            reviewService.save(review);
        }
        return true;
    }
}
