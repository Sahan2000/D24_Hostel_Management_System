package lk.ijse.d24_hostel_management_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity

@NamedQuery(
        name = "Student.findLatestUserId",
        query = "SELECT s.student_id FROM Student s ORDER BY s.student_id DESC"
)
public class Student {
    @Id
    private  String student_id;
    private String name;
    private String address;
    private String contact_no;
    private Date date;
    private String gender;
    @ToString.Exclude
    @OneToMany(targetEntity = Reservation.class, mappedBy = "student", cascade = CascadeType.ALL)
    private List<Reservation> reservationList = new ArrayList<>();

    public Student(String student_id, String name, String address, String contact_no, Date date, String gender) {
        this.student_id = student_id;
        this.name = name;
        this.address = address;
        this.contact_no = contact_no;
        this.date = date;
        this.gender = gender;
    }
}
