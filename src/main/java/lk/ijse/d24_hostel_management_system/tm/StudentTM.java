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
public class StudentTM {
    private String student_id;
    private String name;
    private String address;
    private String contact_no;
    private Date dob;
    private String gender;
}
