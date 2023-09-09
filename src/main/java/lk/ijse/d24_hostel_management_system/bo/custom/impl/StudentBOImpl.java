package lk.ijse.d24_hostel_management_system.bo.custom.impl;

import com.sun.javafx.scene.layout.region.Margins;
import lk.ijse.d24_hostel_management_system.bo.custom.StudentBO;
import lk.ijse.d24_hostel_management_system.bo.util.Converter;
import lk.ijse.d24_hostel_management_system.dao.DAOFactory;
import lk.ijse.d24_hostel_management_system.dao.custom.QueryDAO;
import lk.ijse.d24_hostel_management_system.dao.custom.StudentDAO;
import lk.ijse.d24_hostel_management_system.dto.RoomDTO;
import lk.ijse.d24_hostel_management_system.dto.StudentDTO;
import lk.ijse.d24_hostel_management_system.entity.Room;
import lk.ijse.d24_hostel_management_system.entity.Student;
import lk.ijse.d24_hostel_management_system.util.FactoryConfiguration;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StudentBOImpl implements StudentBO {

    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);
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
    public boolean deleteStudent(String id) {
        Student student = new Student();
        student.setStudent_id(id);
        return studentDAO.delete(student);
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

    @Override
    public Arrays searchStudentByText(String text) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try (session) {
            List<Student> searchStudentByText = studentDAO.searchStudentByText(text, session);
            if (searchStudentByText.size() > 0) {
                return (Arrays) searchStudentByText.stream().map(student -> Converter.getInstance().toStudentDto(student)).collect(Collectors.toList());
            }
            throw new RuntimeException("No any match found");
        }
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) {
        return studentDAO.update(new Student(studentDTO.getStudent_id(), studentDTO.getName(), studentDTO.getAddress(), studentDTO.getContact_no(), studentDTO.getDate(),  studentDTO.getGender()));
    }

    @Override
    public List<String> getCodes() {
        List<Student> reservations = studentDAO.getAll();
        List<String> studentsId = new ArrayList<>();
        for (Student student: reservations) {
            studentsId.add(student.getStudent_id());
        }
        return studentsId;
    }

    @Override
    public StudentDTO searchbyStudentId(String value) {
        Student entity = studentDAO.search(value);
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudent_id(entity.getStudent_id());
        studentDTO.setName(entity.getName());
        studentDTO.setAddress(entity.getAddress());
        studentDTO.setContact_no(entity.getContact_no());
        studentDTO.setDate(entity.getDate());
        studentDTO.setGender(entity.getGender());

        return studentDTO;
    }

    @Override
    public List<StudentDTO> getUnpaidStudents() {
        List<Student> unpaidStudents = queryDAO.getUnpaidStudents();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student :unpaidStudents) {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setStudent_id(student.getStudent_id());
            studentDTO.setName(student.getName());
            studentDTO.setAddress(student.getAddress());
            studentDTO.setContact_no(student.getContact_no());
            studentDTO.setDate(student.getDate());
            studentDTO.setGender(student.getGender());

            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }
}
