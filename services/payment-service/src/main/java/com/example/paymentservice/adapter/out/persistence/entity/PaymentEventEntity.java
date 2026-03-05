package com.example.paymentservice.adapter.out.persistence.entity;

import com.example.paymentservice.domain.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@Table(name = "payment_event")
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class PaymentEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("pk")
    private Long id;

    @CreatedDate
    @Comment("생성일")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Comment("수정일")
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    Long userId;

    @Column(nullable = false)
    String orderId;

    @Column(nullable = false)
    String orderName;

    String paymentKey;

    LocalDateTime approvedAt;

    @Column(nullable = false)
    @ColumnDefault("'NOT_STARTED'")
    @Enumerated(EnumType.STRING)
    PaymentStatus status;

    @ColumnDefault("false")
    Boolean isPaymentDone;
}
