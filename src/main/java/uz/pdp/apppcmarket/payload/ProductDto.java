package uz.pdp.apppcmarket.payload;

import lombok.Data;


@Data
public class ProductDto {

    private String name;

    private Integer price;

    private Integer categoryId;

    private Integer attachmentId;

    private Boolean active;
}

