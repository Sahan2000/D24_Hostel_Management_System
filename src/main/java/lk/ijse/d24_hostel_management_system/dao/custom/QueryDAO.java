package lk.ijse.d24_hostel_management_system.dao.custom;

import lk.ijse.d24_hostel_management_system.dao.SuperDAO;
import lk.ijse.d24_hostel_management_system.entity.Student;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<Student> getUnpaidStudents();
}
