package com.autoParkingLot.autoParkingLot.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "parking")
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "carType", nullable = false)
    private String carType;

    @Column(name = "carHeight", nullable = false)
    private Long carHeight;

    @Column(name = "carWeight", nullable = false)
    private Long carWeight;

    @Column(name = "checkinTime", nullable = false)
    private Date checkinTime;

    @Column(name = "checkoutTime")
    private Date checkoutTime;

    @Column(name = "pricePerMinute", nullable = false)
    private Long pricePerMinute;

    @Column(name = "paymentPrice")
    private Long paymentPrice;

    @Column(name = "entryGate", nullable = false)
    private String entryGate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Floor floor;
}
