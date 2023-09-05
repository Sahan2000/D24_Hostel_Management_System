package lk.ijse.d24_hostel_management_system.bo.custom.impl;

import lk.ijse.d24_hostel_management_system.bo.custom.StudentBO;
import lk.ijse.d24_hostel_management_system.dao.DAOFactory;
import lk.ijse.d24_hostel_management_system.dao.custom.StudentDAO;
import lk.ijse.d24_hostel_management_system.dto.StudentDTO;
import lk.ijse.d24_hostel_management_system.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    @Override
    public String generatenextStudentId() {
        return studentDAO.generateNextId();
    }

    @Override
    public boolean saveStudent(StudentDTO studentDTO) {
        return studentDAO.save(new Student(studentDTO.getStudent_id(),studentDTO.getName(),studentDTO.getAddress(),studentDTO.getContact_no(),studentDTO.getDate(),studentDTO.getGender()));
    }

    @Override
    public boolean deleteStudent(StudentDTO studentDTO) {
        return studentDAO.delete(new Student(studentDTO.getStudent_id(),studentDTO.getName(),studentDTO.getAddress(),studentDTO.getContact_no(),studentDTO.getDate(),studentDTO.getGender()));
    }

    @Override
    public List<StudentDTO> getAllStudent() {
        List<StudentDTO> customerDTOArrayList = new ArrayList<>();
        List<Student> customerArrayList = studentDAO.getAll();
        for (Student student:customerArrayList) {
            customerDTOArrayList.add(new StudentDTO(student.getStudent_id(),student.getName(), student.getAddress(), student.getContact_no(),student.getDate(),student.getGender()));
        }
        return customerDTOArrayList;
    }
}
