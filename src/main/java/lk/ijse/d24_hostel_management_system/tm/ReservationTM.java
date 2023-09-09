package lk.ijse.d24_hostel_management_system.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationTM {
    private String res_id;
    private Date date;
    private String status;
    private String student_id;
    private String room_id;
}
