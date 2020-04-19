package com.pocketguide.model;

import com.pocketguide.model.enums.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="spent")
@Entity
public class Outgo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "enterprise_name")
    private String enterpriseName;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private Date date;

    @Column(name = "value")
    private float value;

    @Column(name = "tag")
    @Enumerated(EnumType.STRING)
    private Tag tag;
}
