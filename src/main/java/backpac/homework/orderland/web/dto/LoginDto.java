package backpac.homework.orderland.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
public class LoginDto {
    private String email;
    private String password;
}
