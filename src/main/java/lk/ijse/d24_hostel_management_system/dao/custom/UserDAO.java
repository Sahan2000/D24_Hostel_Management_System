package lk.ijse.d24_hostel_management_system.dao.custom;

import lk.ijse.d24_hostel_management_system.dao.CrudDAO;
import lk.ijse.d24_hostel_management_system.entity.User;

public interface UserDAO extends CrudDAO<User> {
    boolean check(String userName, String password, String password1);
}
