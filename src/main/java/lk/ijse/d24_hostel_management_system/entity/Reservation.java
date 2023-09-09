package lk.ijse.d24_hostel_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@NamedQuery(
        name = "Reservation.findLatestReservationId",
        query = "SELECT r.res_id FROM Reservation r ORDER BY r.res_id DESC"
)
public class Reservation {
    @Id
    private String res_id;
    private Date date;
    private String status;
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Student student;
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Room room;
}
