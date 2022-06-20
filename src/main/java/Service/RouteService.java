package Service;

import Dao.RouteDao;
import Entity.Route;
import Util.CollectionUtil;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RouteService {
    RouteDao routeDao = new RouteDao();
    public static List<Route> routeList;

    public void addRoute(){
        System.out.println("Nhập vào số chuyến mới:");
        int routeNumber = -1;
        do {
            try {
                routeNumber = new Scanner(System.in).nextInt();
                if (routeNumber > 0) {
                    break;
                }
                System.out.println("Phải nhập số nguyên dương, xin mời nhập lại!");
            } catch (InputMismatchException e){
                System.out.println("Phải nhập số nguyên dương, xin mời nhập lại!");
            }
        } while (true);
        for (int i=0;i<routeNumber;i++){
            Route route = new Route();
            route.inputRoute();
            routeList.add(route);
            routeDao.addNewRoute(route);
        }
    }

    public void showRoute(){
        for (Route route : routeList) {
            System.out.println(route);
        }
    }

    public static Route findRouteById(int routeId) {
        for (Route route : routeList) {
            if (route.getRouteID() == routeId)
                return route;
        }
        return null;
    }

    public void initializeRouteData() {
        List<Route> routeList = routeDao.getAll();
        if (!CollectionUtil.isNullOrEmpty(routeList)) {
            this.routeList = routeList;
        } else {
            this.routeList = new ArrayList<>();
        }
    }
}
