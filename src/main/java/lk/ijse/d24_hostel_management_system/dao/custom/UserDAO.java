package lk.ijse.d24_hostel_management_system.dao.custom;

import lk.ijse.d24_hostel_management_system.dao.CrudDAO;

public interface UserDAO extends CrudDAO {
    boolean check(String userName, String password, String password1);
}
