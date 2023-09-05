package lk.ijse.d24_hostel_management_system.dao.custom.impl;

import lk.ijse.d24_hostel_management_system.dao.custom.UserDAO;
import lk.ijse.d24_hostel_management_system.entity.Student;
import lk.ijse.d24_hostel_management_system.entity.User;
import lk.ijse.d24_hostel_management_system.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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

    @Override
    public String generateNextId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query<String> query =  session.createNamedQuery("User.findLatestUserId", String.class);
        query.setMaxResults(1);
        String latestUserId = query.uniqueResult();

        if (latestUserId != null){
            transaction.commit();
            session.close();
            int newUserID = Integer.parseInt(latestUserId.replace("U00-", "")) + 1;
            return String.format("U00-%03d", newUserID);
        }else {
            return "U00-001";
        }
    }

    @Override
    public boolean save(User user) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(user);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean delete(Student student) {
        return false;
    }

    @Override
    public List<Student> getAll() {
        return null;
    }
}
