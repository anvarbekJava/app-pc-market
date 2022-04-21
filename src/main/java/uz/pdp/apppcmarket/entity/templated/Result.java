package uz.pdp.apppcmarket.entity.templated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Result {
    private String message;
    private Boolean status;
}
