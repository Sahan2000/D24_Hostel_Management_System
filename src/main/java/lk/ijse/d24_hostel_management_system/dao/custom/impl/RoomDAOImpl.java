package lk.ijse.d24_hostel_management_system.dao.custom.impl;

import lk.ijse.d24_hostel_management_system.dao.custom.RoomDAO;
import lk.ijse.d24_hostel_management_system.entity.Room;
import lk.ijse.d24_hostel_management_system.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public String generateNextId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query<String> query =  session.createNamedQuery("Room.findLatestRoomId", String.class);
        query.setMaxResults(1);
        String latestUserId = query.uniqueResult();

        if (latestUserId != null){
            transaction.commit();
            session.close();
            int newUserID = Integer.parseInt(latestUserId.replace("RM-", "")) + 1;
            return String.format("RM-%04d", newUserID);
        }else {
            return "RM-0001";
        }
    }

    @Override
    public boolean save(Room entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Room entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.remove(entity);
        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public List<Room> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Room> studentArrayList = session.createNativeQuery("SELECT * FROM Room").addEntity(Room.class).list();

        transaction.commit();
        session.close();
        return studentArrayList;
    }

    @Override
    public boolean update(Room entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Room search(String selectedItem) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Room room =  session.get(Room.class,selectedItem);

        transaction.commit();
        session.close();

        return room;
    }

    @Override
    public String getCount() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            long count = (long) session.createQuery("select SUM(qty) from Room").getSingleResult();
            transaction.commit();
            System.out.println(count);
            return String.valueOf(count);
        }catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
        return "0";
    }

    @Override
    public int getAcRoomValue() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        int count = 0;
        String type = "AC";
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT COUNT(i) FROM Room i WHERE i.type = :type");
            query.setParameter("type", type);
            Long result = (Long) query.uniqueResult();

            if (result != null) {
                count = result.intValue();
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle the exception properly in your application.
        } finally {
            session.close();
        }

        return count;
    }

    @Override
    public int getNonACRoomValue() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        int count = 0;
        String type = "Non-AC";
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT COUNT(i) FROM Room i WHERE i.type = :type");
            query.setParameter("type", type);
            Long result = (Long) query.uniqueResult();

            if (result != null) {
                count = result.intValue();
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle the exception properly in your application.
        } finally {
            session.close();
        }

        return count;
    }

    @Override
    public int getACFoodRoomValue() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        int count = 0;
        String type = "AC-Food";
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT COUNT(i) FROM Room i WHERE i.type = :type");
            query.setParameter("type", type);
            Long result = (Long) query.uniqueResult();

            if (result != null) {
                count = result.intValue();
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle the exception properly in your application.
        } finally {
            session.close();
        }

        return count;
    }

    @Override
    public int getNonACFoodValue() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        int count = 0;
        String type = "Non-AC-Food";
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT COUNT(i) FROM Room i WHERE i.type = :type");
            query.setParameter("type", type);
            Long result = (Long) query.uniqueResult();

            if (result != null) {
                count = result.intValue();
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle the exception properly in your application.
        } finally {
            session.close();
        }

        return count;
    }
}
