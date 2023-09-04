package com.example.table.review.service;

import static com.example.table.common.type.ErrorCode.CANCELED_RESERVATION;
import static com.example.table.common.type.ErrorCode.EXPIRED_REVIEW_DATE;
import static com.example.table.common.type.ErrorCode.MEMBER_NOT_FOUND;
import static com.example.table.common.type.ErrorCode.MEMBER_RESERVATION_NOT_MATCH;
import static com.example.table.common.type.ErrorCode.MISSING_REQUEST_BODY;
import static com.example.table.common.type.ErrorCode.RESERVATION_NOT_FOUND;
import static com.example.table.common.type.ErrorCode.RESERVATION_NOT_YET;
import static com.example.table.reservation.type.ReservationStatus.CANCELED;
import static com.example.table.reservation.type.ReservationStatus.RESERVED;

import com.example.table.member.domain.Member;
import com.example.table.member.exception.MemberNotFoundException;
import com.example.table.member.repository.MemberRepository;
import com.example.table.reservation.domain.Reservation;
import com.example.table.reservation.exception.ReservationException;
import com.example.table.reservation.repository.ReservationRepository;
import com.example.table.review.domain.Review;
import com.example.table.review.dto.ReviewDto;
import com.example.table.review.dto.ReviewRequest;
import com.example.table.review.exception.ForbiddenReviewException;
import com.example.table.review.exception.GoneReviewException;
import com.example.table.review.exception.ReviewException;
import com.example.table.review.exception.UnprocessableReviewException;
import com.example.table.review.repository.ReviewRepository;
import com.example.table.review.type.ReviewRating;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

  private final ReservationRepository reservationRepository;
  private final MemberRepository memberRepository;
  private final ReviewRepository reviewRepository;

  @Transactional
  @Override
  public ReviewDto postReview(ReviewRequest reviewRequest, Errors errors) {
    if (errors.hasErrors()) {
      FieldError fieldError = errors.getFieldError();
      if (fieldError != null) {
        throw new ReviewException(MISSING_REQUEST_BODY, fieldError.getDefaultMessage());
      }
    }

    Reservation reservation = reservationRepository.findById(reviewRequest.getReservationId())
        .orElseThrow(() -> new ReservationException(RESERVATION_NOT_FOUND));
    Member member = memberRepository.findById(reviewRequest.getMemberId())
        .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

    if (!reservation.getMember().getId().equals(reviewRequest.getMemberId())) {
      throw new ForbiddenReviewException(MEMBER_RESERVATION_NOT_MATCH);
    }

    if (reservation.getStatus() == CANCELED) {
      throw new GoneReviewException(CANCELED_RESERVATION);
    } else if (reservation.getStatus() == RESERVED) {
      throw new UnprocessableReviewException(RESERVATION_NOT_YET);
    }

    LocalDateTime currentTime = LocalDateTime.now();
    if (reservation.getReservationDate().isBefore(currentTime.minusWeeks(1))) {
      throw new UnprocessableReviewException(EXPIRED_REVIEW_DATE);
    }

    return ReviewDto.of(reviewRepository.save(Review.builder()
        .title(reviewRequest.getTitle())
        .contents(reviewRequest.getContents())
        .rating(reviewRequest.getRating())
        .reservation(reservation)
        .member(member)
        .store(reservation.getStore())
        .build()));
  }
}
