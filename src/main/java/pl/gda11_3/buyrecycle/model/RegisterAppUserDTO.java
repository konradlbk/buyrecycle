package pl.gda11_3.buyrecycle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAppUserDTO {
    private String username, password, confirm_password;
}