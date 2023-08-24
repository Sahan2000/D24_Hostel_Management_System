package lk.ijse.d24_hostel_management_system.bo.custom;

import lk.ijse.d24_hostel_management_system.bo.SuperBO;
import lk.ijse.d24_hostel_management_system.dto.UserDTO;
import lk.ijse.d24_hostel_management_system.entity.User;

public interface UserBO extends SuperBO {
    boolean checkUser(String userName, String password, String password1);

    String generatenextUserId();

    boolean saveUser(UserDTO user);
}
