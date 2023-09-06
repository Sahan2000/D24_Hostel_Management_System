package lk.ijse.d24_hostel_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private String room_type_id;
    private String type;
    private Double key_money;
    private Integer qty;
    @ToString.Exclude
    private List<ReservationDTO> reservationList = new ArrayList<>();
}
