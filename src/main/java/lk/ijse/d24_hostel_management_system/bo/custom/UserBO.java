package lk.ijse.d24_hostel_management_system.bo.custom;

import lk.ijse.d24_hostel_management_system.bo.SuperBO;

public interface UserBO extends SuperBO {
    boolean checkUser(String userName, String password, String password1);
}
