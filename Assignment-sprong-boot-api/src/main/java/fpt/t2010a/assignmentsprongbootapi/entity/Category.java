package fpt.t2010a.assignmentsprongbootapi.entity;

import fpt.t2010a.assignmentsprongbootapi.entity.base.BaseEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    @Id
    private String id;
    private String name;
}
