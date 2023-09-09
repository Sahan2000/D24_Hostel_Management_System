package lk.ijse.d24_hostel_management_system.bo.custom.impl;

import lk.ijse.d24_hostel_management_system.bo.custom.ReservationBO;
import lk.ijse.d24_hostel_management_system.dao.DAOFactory;
import lk.ijse.d24_hostel_management_system.dao.custom.ReservationDAO;
import lk.ijse.d24_hostel_management_system.dao.custom.StudentDAO;
import lk.ijse.d24_hostel_management_system.dto.ReservationDTO;
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

}
