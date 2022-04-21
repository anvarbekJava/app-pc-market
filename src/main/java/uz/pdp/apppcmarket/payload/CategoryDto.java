package uz.pdp.apppcmarket.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryDto {

    @NotNull(message = "Name toldirish shart")
    private String name;

    @NotNull(message = "Name toldirish shart")
    private Integer parentCategoryId;

    private Boolean status;
}
