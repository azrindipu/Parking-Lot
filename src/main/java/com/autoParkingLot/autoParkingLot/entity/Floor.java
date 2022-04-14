package com.autoParkingLot.autoParkingLot.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "floor", indexes = {@Index(columnList="floorNumber")})
public class Floor implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "floorNumber", nullable = false, unique = true)
    private Integer floorNumber;

    @Column(name = "ceilingHeight", nullable = false)
    private Long ceilingHeight;

    @Column(name = "totalWeightCapacity", nullable = false)
    private Long totalWeightCapacity;

    @Column(name = "remainingWeightCapacity", nullable = false)
    private Long remainingWeightCapacity;

    @Column(name = "createDate", nullable = false)
    private Date createDate;

    @Column(name = "lastUpdateTime", nullable = false)
    private Date lastUpdateTime;

    @OneToMany(mappedBy = "floor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Parking> parkings = new ArrayList<>();
}
