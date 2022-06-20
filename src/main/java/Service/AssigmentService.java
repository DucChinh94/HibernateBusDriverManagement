package Service;

import Dao.AssignmentDao;
import Entity.Driver;
import Entity.Route;
import Entity.Assignment;
import Entity.Table.AssignmentTable;
import Entity.Table.RouteTurn;
import Util.CollectionUtil;
import Util.DataUtil;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AssigmentService {
    private  AssignmentDao assignmentDao = new AssignmentDao();
    public static List<AssignmentTable> assignmentTableList;

    private static boolean checkEmptyDriverOrRoute() {
        return DriverService.driverList.size() == 0 || RouteService.routeList.size() == 0;
    }

    public void creatAssignmentTable() {
        if (checkEmptyDriverOrRoute()) {
            return;
        }
        Driver driver = inputDriverId();

        int routeNumber = inputRouteNumber();
        int indexAgssignmentTableExits = findIndexAgssignmentTableExits(driver.getDriverId());

        if (indexAgssignmentTableExits < 0) {

            List<RouteTurn> routeTurn = new ArrayList<>();
            createAssignmentTableList(routeTurn, routeNumber, driver);
            assignmentTableList.add(new AssignmentTable(driver, routeTurn));
        } else {

            updateOrAddAssignmentTableExits(indexAgssignmentTableExits, routeNumber, driver);
        }

    }

    private Driver inputDriverId() {
        System.out.print("Nhập ID của lái xe mà bạn muốn thêm điểm: ");
        Driver driver;
        do {
            try {
                int driverId = new Scanner(System.in).nextInt();
                driver = DriverService.findDriverById(driverId);
                if (!DataUtil.isNullOrEmpty(driver)) {
                    break;
                }
                System.out.print("ID lãi xe vừa nhập không tồn tại trong hệ thống, vui lòng nhập lại: ");
            } catch (InputMismatchException ex) {
                System.out.print("ID lái xe phải là một số nguyên dương, không phải là chữ, vui lòng nhập lại: ");
            }
        } while (true);
        return driver;
    }

    private int inputRouteNumber() {
        System.out.print("Nhập số lượng tuyến lái xe chạy: ");
        int routeNumber = -1;
        do {
            try {
                routeNumber = new Scanner(System.in).nextInt();
                if (routeNumber > 0 && routeNumber <= RouteService.routeList.size()) {
                    break;
                }
                System.out.print("Số lượng tuyến là số nguyên và không vượt qua số tuyến có sẵn, xin mời nhập lại: ");
            } catch (InputMismatchException ex) {
                System.out.print("Số lượng tuyến là một số nguyên, xin mời nhập lại: ");
            }
        } while (true);
        return routeNumber;
    }

    private int findIndexAssignmentExits(int indexAgssignmentTableExitsed, int id) {
        for (int i = 0; i < assignmentTableList.get(indexAgssignmentTableExitsed).getRouteTurn().size(); i++) {
            if (assignmentTableList.get(indexAgssignmentTableExitsed).getRouteTurn().get(i).getRoute().getRouteID() == id)
                return i;
        }
        return -1;
    }

    private void createAssignmentTableList(List<RouteTurn> lineTurnList, int routeNumber, Driver driver) {
        for (int i = 0; i < routeNumber; i++) {
            Route route = inputRouteId(i, driver);
            int turnNumber = inputTurnNumber(route);
            int turnSumCurrent = 0;
            turnSumCurrent = lineTurnList.stream().mapToInt(RouteTurn::getTurnNumber).sum();
            if (turnNumber + turnSumCurrent > 15) {
                System.out.println("Số tuyến vượt 15 !");
            } else {
                lineTurnList.add(new RouteTurn(route, turnNumber));

                //Ghi vào DB
                Assignment assignment = new Assignment(driver, route, turnNumber);
                assignmentDao.addNewAssgnment(assignment);
            }
        }
    }

    private int findIndexAgssignmentTableExits(int driverId) {
        for (int i = 0; i < assignmentTableList.size(); i++) {
            if (driverId == assignmentTableList.get(i).getDriver().getDriverId())
                return i;
        }
        return -1;
    }

    private void updateOrAddAssignmentTableExits(int indexAgssignmentTableExitsed, int routeNumber, Driver drivers) {
        for (int i = 0; i < routeNumber; i++) {
            Route route = inputRouteId(i, drivers);
            int turnNumber = inputTurnNumber(route);
            int indexAssignmentExits = findIndexAssignmentExits(indexAgssignmentTableExitsed, route.getRouteID());
            int turnSumCurrent = 0;
            turnSumCurrent = assignmentTableList.get(indexAgssignmentTableExitsed).getRouteTurn()
                    .stream().mapToInt(RouteTurn::getTurnNumber).sum();

            if (indexAssignmentExits < 0) {
                if (turnNumber + turnSumCurrent > 15) {
                    System.out.println("Số tuyến vượt 15 !");
                } else {
                    assignmentTableList.get(indexAgssignmentTableExitsed).getRouteTurn()
                            .add(new RouteTurn(route, turnNumber));


                    Assignment assignment = new Assignment(drivers, route, turnNumber);
                    assignmentDao.addNewAssgnment(assignment);
                }
            }

            else {
                turnSumCurrent = turnSumCurrent - assignmentTableList.get(indexAgssignmentTableExitsed)
                        .getRouteTurn().get(indexAssignmentExits).getTurnNumber();
                if (turnSumCurrent + turnSumCurrent > 15) {
                    System.out.println("Số tuyến vượt 15 !");
                } else {
                    assignmentTableList.get(indexAgssignmentTableExitsed).getRouteTurn()
                            .set(indexAssignmentExits, new RouteTurn(route, turnNumber));


                    Assignment assignment = new Assignment(drivers, route, turnNumber);
                    assignmentDao.update(assignment);
                }
            }
        }
    }

    private Route inputRouteId(int j, Driver driver){
        System.out.print("Nhập ID tuyến đường thứ " + (j + 1) + " mà lái xe " + driver.getFullName() + " lái: ");
        Route route;
        do {
            try {
                int routeId = new Scanner(System.in).nextInt();
                route = RouteService.findRouteById(routeId);
                if (!DataUtil.isNullOrEmpty(route)) {
                    break;
                }
                System.out.print("ID môn học vừa nhập không tồn tại trong hệ thống, vui lòng nhập lại: ");
            } catch (InputMismatchException ex) {
                System.out.print("ID môn học phải là số nguyên dương, không phải là chữ, vui lòng nhập lại: ");
            }
        } while (true);
        return route;
    }

    private int inputTurnNumber(Route route) {
        System.out.print("Nhập số lượt của tuyến " + route.getRouteID() + " :");
        int turnNumber = -1;
        do {
            try {
                turnNumber = new Scanner(System.in).nextInt();
                if (turnNumber > 0) {
                    break;
                }
                System.out.print("Số lượt là số nguyên dương, vui lòng nhập lại: ");
            } catch (InputMismatchException ex) {
                System.out.print("Số lượt là số nguyên dương, không phải là chữ, vui lòng nhập lại: ");
            }
        } while (true);
        return turnNumber;
    }

    public void showAssignmentTable () {
        for (AssignmentTable assignmentTable : assignmentTableList)
            System.out.println(assignmentTable);
    }

    public void initializeAssignmentTableData() {
        assignmentTableList = assignmentDao.getAll();
        if (CollectionUtil.isNullOrEmpty(assignmentTableList)) {
            assignmentTableList = new ArrayList<>();
        }
    }
}

