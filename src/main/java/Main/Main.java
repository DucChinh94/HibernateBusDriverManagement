package Main;

import Service.AssigmentService;
import Service.DriverService;
import Service.RouteService;

import java.util.Scanner;

public class Main {
    public static DriverService driverService = new DriverService();
    public static RouteService routeService = new RouteService();
    public static AssigmentService assigmentService = new AssigmentService();
    public static void main(String[] args) {
        initializeData();
        int choice = -1;
        do {
            System.out.println("\n\n==================PHẦN MỀM QUẢN LÝ PHÂN CÔNG LÁI XE BUS==================\n\n");
            System.out.println("1. Nhập danh sách lái xe mới");
            System.out.println("2. In ra danh sách lái xe");
            System.out.println("3. Nhập danh sách tuyến mới");
            System.out.println("4. In ra danh sách tuyến");
            System.out.println("5. Bảng phân công của lái xe và in ra danh sách bảng phân công");
            System.out.println("6. Sắp xếp họ tên lái xe");
            System.out.println("7. Sắp xếp số lượng tuyến trong ngày(Giảm dần)");
            System.out.println("8. Bảng kê tổng khoảng cách chạy xe trong ngày của mỗi lái xe");
            System.out.println("0. Exit");
            System.out.println("------------------------------------------------------------------------------");
            System.out.print("chon thao tac thuc hien: ");
            do {
                choice = new Scanner(System.in).nextInt();
                if (choice < 0 || choice > 8) System.out.println("thao tac khong hop le. Xin moi chon lai thao tac ");
            } while (choice < 0 || choice > 8);
            switch (choice) {
                case 1:
                    driverService.addDriver();
                    break;
                case 2:
                    driverService.showDriver();
                    break;
                case 3:
                    routeService.addRoute();
                    break;
                case 4:
                    routeService.showRoute();
                    break;
                case 5:
                    assigmentService.creatAssignmentTable();
                    assigmentService.showAssignmentTable();
                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 0:
                    System.out.println("Exit");
                    break;
            }
        } while (choice != 0);
    }

    public static void initializeData() {
        driverService.initializeDriverData();
        routeService.initializeRouteData();
        assigmentService.initializeAssignmentTableData();
    }
}
