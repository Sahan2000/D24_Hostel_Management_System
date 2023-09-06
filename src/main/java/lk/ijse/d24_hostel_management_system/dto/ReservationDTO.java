package lk.ijse.d24_hostel_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private String res_id;
    private Date date;
    private String status;
    @ToString.Exclude
    private StudentDTO studentDto;
    @ToString.Exclude
    private RoomDTO roomDto;
}
