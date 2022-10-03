package com.permisitelu.api.module.Department;

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
@Table(name = "Departments")
public class Department extends BaseEntity implements Serializable {
    @Column(name = "department_name", length = 100)
    private String name;
}