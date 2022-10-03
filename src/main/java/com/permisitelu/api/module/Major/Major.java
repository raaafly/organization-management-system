package com.permisitelu.api.module.Major;

import com.permisitelu.api.module.BaseEntity;
import com.permisitelu.api.module.Faculty.Faculty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Majors")
public class Major extends BaseEntity {
    @Column(name = "major_name", length = 100)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private Faculty faculty;

}