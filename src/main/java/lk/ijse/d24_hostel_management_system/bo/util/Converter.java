package lk.ijse.d24_hostel_management_system.bo.util;

import lk.ijse.d24_hostel_management_system.dto.ReservationDTO;
import lk.ijse.d24_hostel_management_system.dto.RoomDTO;
import lk.ijse.d24_hostel_management_system.dto.StudentDTO;
import lk.ijse.d24_hostel_management_system.dto.UserDTO;
import lk.ijse.d24_hostel_management_system.entity.Reservation;
import lk.ijse.d24_hostel_management_system.entity.Room;
import lk.ijse.d24_hostel_management_system.entity.Student;
import lk.ijse.d24_hostel_management_system.entity.User;

public class Converter {
    private static Converter converter;

    private Converter() {

    }

    public static Converter getInstance() {
        return converter == null ? converter = new Converter() : converter;
    }

    public UserDTO toUserDto(User entity) {
        return new UserDTO(entity.getUserId(), entity.getUserName(), entity.getPassword(), entity.getPassword_hint(), entity.getEmail());
    }

    public User toUserEntity(UserDTO dto) {
        return new User(dto.getUserId(), dto.getUserName(), dto.getPassword(), dto.getPasswordHint(), dto.getEmail());
    }

    public StudentDTO toStudentDto(Student student) {
        StudentDTO studentDto = new StudentDTO();
        studentDto.setStudent_id(student.getStudent_id());
        studentDto.setName(student.getName());
        studentDto.setContact_no(student.getContact_no());
        studentDto.setDate(student.getDate());
        studentDto.setGender(student.getGender());
        studentDto.setAddress(student.getAddress());
        return studentDto;
    }

    public Student toStudentEntity(StudentDTO dto) {
        System.out.println(dto);
        Student studentEntity = new Student();
        studentEntity.setStudent_id(dto.getStudent_id());
        studentEntity.setName(dto.getName());
        studentEntity.setContact_no(dto.getContact_no());
        studentEntity.setDate(dto.getDate());
        studentEntity.setGender(dto.getGender());
        studentEntity.setAddress(dto.getAddress());
        return studentEntity;
    }

    public RoomDTO toRoomDto(Room entity) {
        RoomDTO dto = new RoomDTO();
        dto.setRoom_type_id(entity.getRoom_type_id());
        dto.setType(entity.getType());
        dto.setKey_money(entity.getKey_money());
        dto.setQty(entity.getQty());
        return dto;
    }

    public Room toRoomEntity(RoomDTO dto) {
        Room entity = new Room();
        entity.setRoom_type_id(dto.getRoom_type_id());
        entity.setType(dto.getType());
        entity.setKey_money(dto.getKey_money());
        entity.setQty(dto.getQty());
        return entity;
    }

    public Reservation toReservationEntity(ReservationDTO dto) {
        System.out.println(dto);
        Reservation entity = new Reservation();
        entity.setRes_id(dto.getRes_id());
        entity.setStudent(toStudentEntity(dto.getStudentDto()));
        entity.setRoom(toRoomEntity(dto.getRoomDto()));
        entity.setDate(dto.getDate());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    public ReservationDTO toReservationDto(Reservation entity) {
        ReservationDTO dto = new ReservationDTO();
        dto.setRes_id(entity.getRes_id());
        dto.setStudentDto(toStudentDto(entity.getStudent()));
        dto.setRoomDto(toRoomDto(entity.getRoom()));
        dto.setDate(entity.getDate());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
