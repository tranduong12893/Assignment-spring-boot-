package fpt.t2010a.assignmentsprongbootapi.entity;

import fpt.t2010a.assignmentsprongbootapi.entity.base.BaseEntity;
import fpt.t2010a.assignmentsprongbootapi.entity.myenum.ProductStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Id
    private String id;
    private String name;
    private BigDecimal price;
    private String description;
    @Lob
    private String detail; // text
    private String thumbnails;
    @Enumerated(EnumType.ORDINAL)
    private ProductStatus status;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    @JsonManagedReference
    private Category category;

}
