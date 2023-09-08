package lk.ijse.d24_hostel_management_system.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserTM {
    private String userId;
    private String userName;
    private String email;
    private String password;
    private String password_hint;
}
