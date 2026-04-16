package org.example.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "CURRENCY")
@Data  // Lombok
public class Currency {

    @Id
    @Column(name = "CODE", length = 10)
    private String code;           //

    @Column(name = "CHINESE_NAME", nullable = false, length = 50)
    private String chineseName;         //

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;  //
}
