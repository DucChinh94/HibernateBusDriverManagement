package Dao;

import Entity.Driver;
import Util.DataUtil;
import Util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class DriverDao {
    public void addNewDriver(Driver driver) {
        if (DataUtil.isNullOrEmpty(driver)) return;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(driver);
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
            assert session != null;
            session.getTransaction().rollback();
        }
    }

    public List<Driver> getAll(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            return (List<Driver>) session.createQuery("from Driver").list();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
