package lk.ijse.d24_hostel_management_system.dao;

import lk.ijse.d24_hostel_management_system.entity.User;

public interface CrudDAO extends SuperDAO{
    String generateNextId();

    boolean save(User user);
}
