package com.example.table.review.service;

import com.example.table.review.dto.ReviewDto;
import com.example.table.review.dto.ReviewRequest;
import org.springframework.validation.Errors;

public interface ReviewService {

  ReviewDto postReview(ReviewRequest reviewRequest, Errors errors);
}
