package lk.ijse.d24_hostel_management_system.dao.custom.impl;

import lk.ijse.d24_hostel_management_system.dao.custom.ReservationDAO;
import lk.ijse.d24_hostel_management_system.entity.Reservation;
import lk.ijse.d24_hostel_management_system.entity.Room;
import lk.ijse.d24_hostel_management_system.entity.Student;
import lk.ijse.d24_hostel_management_system.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public String generateNextId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query<String> query =  session.createNamedQuery("Reservation.findLatestReservationId", String.class);
        query.setMaxResults(1);
        String latestUserId = query.uniqueResult();

        if (latestUserId != null){
            transaction.commit();
            session.close();
            int newUserID = Integer.parseInt(latestUserId.replace("REC-", "")) + 1;
            return String.format("REC-%06d", newUserID);
        }else {
            return "REC-000001";
        }
    }

    @Override
    public boolean save(Reservation entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Reservation entity) {
        return false;
    }

    @Override
    public List<Reservation> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Reservation> reservationArrayList = session.createNativeQuery("SELECT * FROM Reservation").addEntity(Reservation.class).list();

        transaction.commit();
        session.close();
        return reservationArrayList;
    }

    @Override
    public boolean update(Reservation entity) {
        return false;
    }

    @Override
    public Reservation search(String selectedItem) {
        return null;
    }
}
