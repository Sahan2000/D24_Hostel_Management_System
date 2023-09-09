package lk.ijse.d24_hostel_management_system.bo.custom.impl;

import lk.ijse.d24_hostel_management_system.bo.custom.ReservationBO;
import lk.ijse.d24_hostel_management_system.dao.DAOFactory;
import lk.ijse.d24_hostel_management_system.dao.custom.ReservationDAO;
import lk.ijse.d24_hostel_management_system.dao.custom.StudentDAO;
import lk.ijse.d24_hostel_management_system.dto.ReservationDTO;
import lk.ijse.d24_hostel_management_system.dto.RoomDTO;
import lk.ijse.d24_hostel_management_system.dto.StudentDTO;
import lk.ijse.d24_hostel_management_system.entity.Reservation;
import lk.ijse.d24_hostel_management_system.entity.Room;
import lk.ijse.d24_hostel_management_system.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {

    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public String generatenextReservationId() {
        return reservationDAO.generateNextId();
    }

    @Override
    public boolean saveReservation(ReservationDTO reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setRes_id(reservationDto.getRes_id());
        reservation.setDate(reservationDto.getDate());
        reservation.setStatus(reservationDto.getStatus());

        Room room = new Room();
        room.setRoom_type_id(reservationDto.getRoomDto().getRoom_type_id());
        room.setType(reservationDto.getRoomDto().getType());
        room.setKey_money(reservationDto.getRoomDto().getKey_money());
        room.setQty(reservationDto.getRoomDto().getQty());
        reservation.setRoom(room);

        Student student = new Student();
        student.setStudent_id(reservationDto.getStudentDto().getStudent_id());
        student.setName(reservationDto.getStudentDto().getName());
        student.setAddress(reservationDto.getStudentDto().getAddress());
        student.setContact_no(reservationDto.getStudentDto().getContact_no());
        student.setDate(reservationDto.getStudentDto().getDate());
        student.setGender(reservationDto.getStudentDto().getGender());

        reservation.setStudent(student);
        return reservationDAO.save(reservation);
    }

    @Override
    public List<ReservationDTO> getAllReservation() {
        List<ReservationDTO> reservationDTOArrayList = new ArrayList<>();
        List<Reservation> reservationsArrayList = reservationDAO.getAll();
        for (Reservation reservation:reservationsArrayList) {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setStudent_id(reservation.getStudent().getStudent_id());
            studentDTO.setName(reservation.getStudent().getName());
            studentDTO.setAddress(reservation.getStudent().getAddress());
            studentDTO.setContact_no(reservation.getStudent().getContact_no());
            studentDTO.setDate(reservation.getStudent().getDate());
            studentDTO.setGender(reservation.getStudent().getGender());

            RoomDTO roomDTO = new RoomDTO();
            roomDTO.setRoom_type_id(reservation.getRoom().getRoom_type_id());
            roomDTO.setType(reservation.getRoom().getType());
            roomDTO.setKey_money(reservation.getRoom().getKey_money());
            roomDTO.setQty(reservation.getRoom().getQty());
            reservationDTOArrayList.add(new ReservationDTO(reservation.getRes_id(),reservation.getDate(),reservation.getStatus(),studentDTO,roomDTO));
        }
        return reservationDTOArrayList;
    }

    @Override
    public boolean deleteReservation(String resId) {
        Reservation reservation = new Reservation();
        reservation.setRes_id(resId);
        return reservationDAO.delete(reservation);
    }

    @Override
    public ReservationDTO searchReservationByresId(String resId) {
        Reservation reservation = reservationDAO.search(resId);

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setRes_id(reservation.getRes_id());
        reservationDTO.setDate(reservation.getDate());

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudent_id(reservation.getStudent().getStudent_id());
        studentDTO.setName(reservation.getStudent().getName());
        studentDTO.setAddress(reservation.getStudent().getAddress());
        studentDTO.setContact_no(reservation.getStudent().getContact_no());
        studentDTO.setDate(reservation.getStudent().getDate());
        studentDTO.setGender(reservation.getStudent().getGender());
        reservationDTO.setStudentDto(studentDTO);

        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoom_type_id(reservation.getRoom().getRoom_type_id());
        roomDTO.setType(reservation.getRoom().getType());
        roomDTO.setKey_money(reservation.getRoom().getKey_money());
        roomDTO.setQty(reservation.getRoom().getQty());
        reservationDTO.setRoomDto(roomDTO);

        return reservationDTO;
    }

    @Override
    public boolean updateReservation(ReservationDTO dto) {
        Reservation reservation = new Reservation();
        reservation.setRes_id(dto.getRes_id());
        reservation.setDate(dto.getDate());
        reservation.setStatus(dto.getStatus());

        Room room = new Room();
        room.setRoom_type_id(dto.getRoomDto().getRoom_type_id());
        room.setType(dto.getRoomDto().getType());
        room.setKey_money(dto.getRoomDto().getKey_money());
        room.setQty(dto.getRoomDto().getQty());
        reservation.setRoom(room);

        Student student = new Student();
        student.setStudent_id(dto.getStudentDto().getStudent_id());
        student.setName(dto.getStudentDto().getName());
        student.setAddress(dto.getStudentDto().getAddress());
        student.setContact_no(dto.getStudentDto().getContact_no());
        student.setDate(dto.getStudentDto().getDate());
        student.setGender(dto.getStudentDto().getGender());

        reservation.setStudent(student);
        return reservationDAO.update(reservation);
    }

}
