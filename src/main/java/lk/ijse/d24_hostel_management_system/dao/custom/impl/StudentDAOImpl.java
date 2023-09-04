package lk.ijse.d24_hostel_management_system.dao.custom.impl;

import lk.ijse.d24_hostel_management_system.dao.custom.StudentDAO;
import lk.ijse.d24_hostel_management_system.entity.Student;
import lk.ijse.d24_hostel_management_system.entity.User;
import lk.ijse.d24_hostel_management_system.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public String generateNextId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query<String> query =  session.createNamedQuery("Student.findLatestUserId", String.class);
        query.setMaxResults(1);
        String latestUserId = query.uniqueResult();

        if (latestUserId != null){
            transaction.commit();
            session.close();
            int newUserID = Integer.parseInt(latestUserId.replace("S00-", "")) + 1;
            return String.format("S00-%03d", newUserID);
        }else {
            return "S00-001";
        }
    }

    @Override
    public boolean save(Student user) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Student student) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.remove(student);
        transaction.commit();
        session.close();

        return true;
    }
}
