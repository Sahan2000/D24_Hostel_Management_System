package lk.ijse.d24_hostel_management_system.dao.custom.impl;

import lk.ijse.d24_hostel_management_system.dao.custom.UserDAO;
import lk.ijse.d24_hostel_management_system.entity.User;
import lk.ijse.d24_hostel_management_system.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean check(String userName, String password, String password1) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<User> userList = session.createNativeQuery("SELECT * FROM User WHERE userName = ?", User.class)
                .setParameter(1,userName)
                .getResultList();
        for (User user:userList) {
            transaction.commit();
            session.close();
            if (user.getPassword().equals(password) || user.getPassword().equals(password1)){
                return true;
            }
        }
        return false;
    }
}
