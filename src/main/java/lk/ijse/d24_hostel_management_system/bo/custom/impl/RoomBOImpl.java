package lk.ijse.d24_hostel_management_system.bo.custom.impl;

import lk.ijse.d24_hostel_management_system.bo.custom.RoomBO;
import lk.ijse.d24_hostel_management_system.bo.util.Converter;
import lk.ijse.d24_hostel_management_system.dao.DAOFactory;
import lk.ijse.d24_hostel_management_system.dao.custom.RoomDAO;
import lk.ijse.d24_hostel_management_system.dto.RoomDTO;
import lk.ijse.d24_hostel_management_system.dto.StudentDTO;
import lk.ijse.d24_hostel_management_system.entity.Room;
import lk.ijse.d24_hostel_management_system.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoomBOImpl implements RoomBO {

    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public List<String> getCodes() {
        List<Room> reservations = roomDAO.getAll();
        List<String> studentsId = new ArrayList<>();
        for (Room student: reservations) {
            studentsId.add(student.getRoom_type_id());
        }
        return studentsId;
    }

    @Override
    public boolean saveRooms(RoomDTO roomDTO) {
        Room room = new Room();
        room.setRoom_type_id(roomDTO.getRoom_type_id());
        room.setType(roomDTO.getType());
        room.setKey_money(roomDTO.getKey_money());
        room.setQty(roomDTO.getQty());
        return roomDAO.save(room);
    }

    @Override
    public boolean updateRooms(RoomDTO roomDTO) {
        Room room = new Room();
        room.setRoom_type_id(roomDTO.getRoom_type_id());
        room.setType(roomDTO.getType());
        room.setKey_money(roomDTO.getKey_money());
        room.setQty(roomDTO.getQty());
        return roomDAO.update(room);
    }

    @Override
    public List<RoomDTO> getAllRooms() {
        /*List<Room> roomList = roomDAO.getAll();*/

        /*if (roomList.size() > 0) {
            return roomList.stream().map(room -> Converter.getInstance().toRoomDto(room)).collect(Collectors.toList());
        }
        throw new RuntimeException("Empty Room list!");*/
        List<RoomDTO> roomsDTOArrayList = new ArrayList<>();
        List<Room> roomsArrayList = roomDAO.getAll();
        for (Room room : roomsArrayList) {
            RoomDTO roomDTO = new RoomDTO();
            roomDTO.setRoom_type_id(room.getRoom_type_id());
            roomDTO.setType(room.getType());
            roomDTO.setKey_money(room.getKey_money());
            roomDTO.setQty(room.getQty());
            roomsDTOArrayList.add(roomDTO);
        }
        return roomsDTOArrayList;
    }

    @Override
    public boolean deleteRoom(String roomTypeId) {
        Room room = new Room();
        room.setRoom_type_id(roomTypeId);
        return roomDAO.delete(room);
    }

    @Override
    public String generatenextRoomId() {
        return roomDAO.generateNextId();

    }

    @Override
    public RoomDTO searchByRoomTypeId(String selectedItem) {
        Room entity = roomDAO.search(selectedItem);
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoom_type_id(entity.getRoom_type_id());
        roomDTO.setType(entity.getType());
        roomDTO.setKey_money(entity.getKey_money());
        roomDTO.setQty(entity.getQty());

        return roomDTO;
    }

    @Override
    public String availableRoomsCount() {
        return roomDAO.getCount();
    }

    @Override
    public int getAcRoomValue() {
        return roomDAO.getAcRoomValue();
    }

    @Override
    public int getNonACRoomValue() {
        return roomDAO.getNonACRoomValue();
    }

    @Override
    public int getACFoodRoomValue() {
        return roomDAO.getACFoodRoomValue();
    }

    @Override
    public int getNonACFoodValue() {
        return roomDAO.getNonACFoodValue();
    }

}
