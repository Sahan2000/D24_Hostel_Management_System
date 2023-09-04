package lk.ijse.d24_hostel_management_system.dto;

import lombok.*;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class StudentDTO {
    private  String student_id;
    private String name;
    private String address;
    private String contact_no;
    private Date date;
    private String gender;
}
