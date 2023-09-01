package com.example.table.member.domain;

import com.example.table.member.type.MemberType;
import com.example.table.reservation.domain.Reservation;
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
  @OneToMany(mappedBy = "member")
  private List<Reservation> reservations;
  @Enumerated(EnumType.STRING)
  private MemberType memberType;
  private String username;
  private String password;
  private String name;
  private String phoneNumber;
  private String email;

  private boolean hasActiveReservation;
  @CreatedDate
  private LocalDateTime registeredAt;

  public void addReservation(Reservation reservation) {
    if (reservations == null) {
      reservations = new ArrayList<>();
    }
    reservations.add(reservation);
    reservation.setMember(this);
  }
}
