package lk.ijse.d24_hostel_management_system.dao;

import lk.ijse.d24_hostel_management_system.entity.Student;
import lk.ijse.d24_hostel_management_system.entity.User;

import java.util.List;

public interface CrudDAO <T> extends SuperDAO{
    String generateNextId();

    boolean save(T user);

    boolean delete(Student student);

    List<Student> getAll();
}
