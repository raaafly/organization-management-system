package com.permisitelu.api.module.Faculty;

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
@Table(name = "Faculties")
public class Faculty extends BaseEntity implements Serializable {
    @Column(name = "faculty_name", length = 100)
    private String name;
}