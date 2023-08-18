package lk.ijse.d24_hostel_management_system.bo.custom.impl;

import lk.ijse.d24_hostel_management_system.bo.custom.UserBO;
import lk.ijse.d24_hostel_management_system.dao.DAOFactory;
import lk.ijse.d24_hostel_management_system.dao.custom.UserDAO;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public boolean checkUser(String userName, String password, String password1) {
        return userDAO.check(userName,password,password1);
    }
}
