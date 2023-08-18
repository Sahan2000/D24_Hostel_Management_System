package lk.ijse.d24_hostel_management_system.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserDTO {
    private String userId;
    private String userName;
    private String email;
    private String password;
}
