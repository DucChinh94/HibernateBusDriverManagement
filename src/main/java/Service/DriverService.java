package Service;

import Dao.DriverDao;
import Entity.Driver;
import Util.CollectionUtil;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DriverService {
DriverDao driverDao = new DriverDao();
    public static List<Driver> driverList;

    public void addDriver(){
        System.out.println("Nhập số lái xe mới:");
        int driverNumber = -1;
        do {
            try {
                driverNumber = new Scanner(System.in).nextInt();
                if (driverNumber > 0) {
                    break;
                }
                System.out.println("Phải nhập số nguyên dương, xin mời nhập lại!");
            } catch (InputMismatchException e){
                System.out.println("Phải nhập số nguyên dương, xin mời nhập lại!");
            }
        } while (true);
        for (int i=0; i<driverNumber;i++){
            Driver driver = new Driver();
            driver.inputInfo();
            driverList.add(driver);
            driverDao.addNewDriver(driver);
        }
    }

    public void showDriver(){
        for (Driver driver : driverList) {
            System.out.println(driver);
        }
    }

    public static Driver findDriverById(int driverId) {
        for (Driver driver : driverList) {
            if (driver.getDriverId() == driverId)
                return driver;
        }
        return null;
    }

    public void initializeDriverData() {
        List<Driver> driverList = driverDao.getAll();
        if (!CollectionUtil.isNullOrEmpty(driverList)) {
            this.driverList = driverList;
        } else {
            this.driverList = new ArrayList<>();
        }
    }
}
