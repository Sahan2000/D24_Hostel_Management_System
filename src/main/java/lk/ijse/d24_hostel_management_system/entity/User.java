package lk.ijse.d24_hostel_management_system.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

@Table(
        uniqueConstraints = {@UniqueConstraint(name = "unique_email", columnNames = "email"),
                @UniqueConstraint(name = "unique_userName", columnNames = "userName")}
)

@NamedQuery(
        name = "User.findLatestUserId",
        query = "SELECT u.userId FROM User u ORDER BY u.userId DESC"
)

public class User {
    @Id
    private String userId;
    private String userName;
    private String email;
    private String password;
    private String password_hint;
}
