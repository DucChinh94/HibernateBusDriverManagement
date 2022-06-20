package Dao;

import Entity.Route;
import Util.DataUtil;
import Util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class RouteDao {
    public void addNewRoute(Route route){
        if (DataUtil.isNullOrEmpty(route)) return;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(route);
            session.getTransaction().commit();
        }catch (Exception exception) {
            exception.printStackTrace();
            assert session != null;
            session.getTransaction().rollback();
        }
    }
    public List<Route> getAll(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            return (List<Route>) session.createQuery("from Driver").list();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
