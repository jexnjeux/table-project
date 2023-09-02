package com.example.table.reservation.domain;

import com.example.table.member.domain.Member;
import com.example.table.reservation.type.ReservationStatus;
import com.example.table.store.domain.Store;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private ReservationStatus status;
  private int guestCount;
  private LocalDateTime confirmationDate;
  private LocalDateTime cancellationDate;
  private LocalDateTime reservationDate;
  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;
  @ManyToOne
  @JoinColumn(name = "store_id")
  private Store store;
  @ManyToOne
  @JoinColumn(name = "reservation_id")
  private Reservation reservation;
}
