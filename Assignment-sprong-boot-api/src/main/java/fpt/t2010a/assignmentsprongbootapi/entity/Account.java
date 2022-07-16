package fpt.t2010a.assignmentsprongbootapi.entity;

import fpt.t2010a.assignmentsprongbootapi.entity.base.BaseEntity;
import fpt.t2010a.assignmentsprongbootapi.entity.myenum.AccountStatus;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {
    @Id
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String thumbnail;
    @Lob
    private String detail;
    @Enumerated(EnumType.ORDINAL)
    private AccountStatus status;
    private String role;
}
