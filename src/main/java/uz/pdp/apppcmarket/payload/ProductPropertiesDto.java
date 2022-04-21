package uz.pdp.apppcmarket.payload;

import lombok.Data;



@Data
public class ProductPropertiesDto {

    private String key;

    private String value;

    private Integer productId;
}
