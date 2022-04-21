package uz.pdp.apppcmarket.custom;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.apppcmarket.entity.Role;

@Projection(types = Role.class)
public interface CustomRole {
    Integer getId();

    String getName();

}
