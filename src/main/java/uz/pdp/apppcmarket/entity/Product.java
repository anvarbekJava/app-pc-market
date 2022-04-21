package uz.pdp.apppcmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @JoinColumn(name = "attachment_id")
    @OneToOne
    private Attachment attachment;

    private Boolean active = true;
}
