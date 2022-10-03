package com.permisitelu.api.module.Membership;

import com.permisitelu.api.module.BaseEntity;
import com.permisitelu.api.module.Major.Major;
import com.permisitelu.api.module.MembershipType.MembershipType;
import com.permisitelu.api.module.User.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Memberships")
public class Membership extends BaseEntity implements Serializable {
    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_birthday")
    private Date DOB;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id", referencedColumnName = "id")
    private Major major;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membership_type_id", referencedColumnName = "id")
    private MembershipType membershipType;
}