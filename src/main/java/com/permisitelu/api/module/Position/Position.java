package com.permisitelu.api.module.Position;

import com.permisitelu.api.module.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Positions")
public class Position extends BaseEntity implements Serializable {
    @Column(name = "position_name", length = 50)
    private String name;
}