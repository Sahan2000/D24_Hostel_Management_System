package lk.ijse.d24_hostel_management_system.bo.custom;

import lk.ijse.d24_hostel_management_system.bo.SuperBO;
import lk.ijse.d24_hostel_management_system.dto.ReservationDTO;

import java.util.List;

public interface ReservationBO extends SuperBO {
    String generatenextReservationId();

    boolean saveReservation(ReservationDTO reservationDto);
}
