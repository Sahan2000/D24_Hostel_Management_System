package lk.ijse.d24_hostel_management_system.bo.custom;

import lk.ijse.d24_hostel_management_system.bo.SuperBO;
import lk.ijse.d24_hostel_management_system.dto.StudentDTO;

import java.util.Arrays;
import java.util.List;

public interface StudentBO extends SuperBO {
    String generatenextStudentId();

    boolean saveStudent(StudentDTO studentDTO);

    boolean deleteStudent(StudentDTO studentDTO);

    List<StudentDTO> getAllStudent();

    Arrays searchStudentByText(String text);
}
