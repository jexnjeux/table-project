package com.example.table.reservation.service;

import static com.example.table.common.type.ErrorCode.ALREADY_RESERVED;
import static com.example.table.common.type.ErrorCode.FULLED_RESERVATION;
import static com.example.table.common.type.ErrorCode.INVALID_RESERVATION_DATE;
import static com.example.table.common.type.ErrorCode.MEMBER_NOT_FOUND;
import static com.example.table.common.type.ErrorCode.MISSING_REQUEST_BODY;
import static com.example.table.common.type.ErrorCode.RESERVATION_NOT_FOUND;
import static com.example.table.common.type.ErrorCode.STORE_NOT_FOUND;
import static com.example.table.reservation.type.ReservationStatus.CONFIRMED;
import static com.example.table.reservation.type.ReservationStatus.RESERVED;

import com.example.table.member.domain.Member;
import com.example.table.member.exception.MemberNotFoundException;
import com.example.table.member.repository.MemberRepository;
import com.example.table.reservation.domain.Reservation;
import com.example.table.reservation.domain.ReservationHistory;
import com.example.table.reservation.dto.ReservationActionDto;
import com.example.table.reservation.dto.ReservationDto;
import com.example.table.reservation.dto.ReservationRequest;
import com.example.table.reservation.exception.ReservationException;
import com.example.table.reservation.repository.ReservationHistoryRepository;
import com.example.table.reservation.repository.ReservationRepository;
import com.example.table.store.domain.Store;
import com.example.table.store.exception.StoreException;
import com.example.table.store.repository.StoreRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImp implements ReservationService {

  private final MemberRepository memberRepository;
  private final StoreRepository storeRepository;
  private final ReservationRepository reservationRepository;
  private final ReservationHistoryRepository reservationHistoryRepository;

  @Transactional
  @Override
  public ReservationDto createReservation(ReservationRequest reservationRequest, Errors errors) {
    if (errors.hasErrors()) {
      FieldError fieldError = errors.getFieldError();
      if (fieldError != null) {
        throw new ReservationException(MISSING_REQUEST_BODY, fieldError.getDefaultMessage());
      }
    }

    Member member = memberRepository.findById(reservationRequest.getMemberId())
        .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));
    Store store = storeRepository.findById(reservationRequest.getStoreId())
        .orElseThrow(() -> new StoreException(STORE_NOT_FOUND));

    if (member.isHasActiveReservation()) {
      throw new ReservationException(ALREADY_RESERVED);
    }

    LocalDateTime currentTime = LocalDateTime.now();
    if (reservationRequest.getReservationDate().isBefore(currentTime)) {
      throw new ReservationException(INVALID_RESERVATION_DATE);
    }

    if (reservationRepository.existsByStoreIdAndReservationDate(reservationRequest.getStoreId(),
        reservationRequest.getReservationDate())) {
      throw new ReservationException(FULLED_RESERVATION);
    }

    Reservation reservation = Reservation.builder()
        .member(member)
        .store(store)
        .guestCount(reservationRequest.getGuestCount())
        .reservationDate(reservationRequest.getReservationDate())
        .status(RESERVED)
        .memo(reservationRequest.getMemo())
        .createdAt(LocalDateTime.now())
        .build();

    member.setHasActiveReservation(true);
    memberRepository.save(member);

    return ReservationDto.of(reservationRepository.save(reservation));
  }

  @Override
  public ReservationActionDto confirmReservation(String phoneNumber) {

    Member member = memberRepository.findByPhoneNumber(phoneNumber)
        .orElseThrow(() -> new MemberNotFoundException(MEMBER_NOT_FOUND));

    Reservation reservation = reservationRepository.findByMemberAndStatus(
            member, RESERVED)
        .orElseThrow(() -> new ReservationException(RESERVATION_NOT_FOUND));

    Store store = storeRepository.findById(reservation.getStore().getId())
        .orElseThrow(() -> new StoreException(STORE_NOT_FOUND));

    reservation.setStatus(CONFIRMED);
    reservationRepository.save(reservation);

    member.setHasActiveReservation(false);
    memberRepository.save(member);


    return ReservationActionDto.of(reservationHistoryRepository.save(ReservationHistory.builder()
        .status(CONFIRMED)
        .guestCount(reservation.getGuestCount())
        .member(member)
        .store(store)
        .confirmationDate(LocalDateTime.now())
            .reservationDate(reservation.getReservationDate())
        .build()));
  }


}
