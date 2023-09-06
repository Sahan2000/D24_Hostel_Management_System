package lk.ijse.d24_hostel_management_system.dao.custom;

import lk.ijse.d24_hostel_management_system.dao.CrudDAO;
import lk.ijse.d24_hostel_management_system.entity.Student;
import org.hibernate.Session;

import java.util.List;

public interface StudentDAO extends CrudDAO<Student> {
    List<Student> searchStudentByText(String text, Session session);
}
