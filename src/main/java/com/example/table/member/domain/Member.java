package com.example.table.member.domain;

import com.example.table.member.type.MemberType;
import com.example.table.reservation.domain.ReservationHistory;
import com.example.table.review.domain.Review;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Enumerated(EnumType.STRING)
  private MemberType memberType;
  private String role;
  private String username;
  private String password;
  private String name;
  private String phoneNumber;
  private String email;
  private boolean hasActiveReservation;
  @CreatedDate
  private LocalDateTime registeredAt;
  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<ReservationHistory> reservationHistoryList = new ArrayList<>();
  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<Review> reviewList = new ArrayList<>();

}
