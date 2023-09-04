package com.example.table.review.controller;

import com.example.table.review.dto.ReviewRequest;
import com.example.table.review.dto.ReviewResponse;
import com.example.table.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/review")
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewService reviewService;
  @PostMapping
  public ReviewResponse postReview(@RequestBody @Valid ReviewRequest reviewRequest, Errors errors) {
    return ReviewResponse.of(reviewService.postReview(reviewRequest, errors));
  }

}
