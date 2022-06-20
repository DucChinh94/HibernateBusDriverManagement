package Dao;

import Entity.Assignment;
import Entity.Table.AssignmentTable;
import Entity.Table.RouteTurn;
import Util.DataUtil;
import Util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class AssignmentDao {
    public void addNewAssgnment(Assignment assignment) {
        if (DataUtil.isNullOrEmpty(assignment)) {
            return;
        }
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(assignment);
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
            assert session != null;
            session.getTransaction().rollback();
        }
    }

    public List<AssignmentTable> getAll() {
        List<AssignmentTable> assignmentTableList = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<Assignment> assignmentList = session.createQuery("from Assignment").list();
            for (Assignment assignment : assignmentList) {
                boolean checkExits = false;
                for (AssignmentTable assignmentTable : assignmentTableList) {
                    if (assignmentTable.getDriver().getDriverId() == assignment.getDrivers().getDriverId()) {
                        assignmentTable.getRouteTurn().add(new RouteTurn(assignment.getRoute(), assignment.getTurnNumber()));
                        checkExits = true;
                        break;
                    }
                }
                if (!checkExits) {
                    List<RouteTurn> lineTurns = new ArrayList<>();
                    lineTurns.add(new RouteTurn(assignment.getRoute(), assignment.getTurnNumber()));
                    assignmentTableList.add(new AssignmentTable(assignment.getDrivers(), lineTurns));
                }
            }
            return assignmentTableList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Assignment assignment) {
        if (DataUtil.isNullOrEmpty(assignment)) {
            return;
        }
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(assignment);
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
            assert session != null;
            session.getTransaction().rollback();
        }
    }
}
