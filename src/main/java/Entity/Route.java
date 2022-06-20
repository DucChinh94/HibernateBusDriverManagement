package Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

@Entity
@Table(name = "bus_route")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Route implements Serializable {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "route5_seq")
    @SequenceGenerator(name = "route5_seq", sequenceName = "route5_seq", allocationSize = 1, initialValue = 100)
    private int routeID;
    @Column(name = "BUS_ROUTE_RANGE")
    private float distance;
    @Column(name = "BUS_ROUTE_STOP_NUMBER")
    private int stopNumber;

    public void inputRoute(){
        inputDistance();
        inputStopNumber();
    }
    private void inputDistance(){
        System.out.println("nhap vao khoang cach:");
        distance = -1.0f;
        do {
            try {
                distance = new Scanner(System.in).nextFloat();
                if (distance > 0){
                    break;
                }
                System.out.println("Khoảng cách là số thực lớn hơn 0, xin moi nhap lai!");
            } catch (InputMismatchException e){
                System.out.println("Khoảng cách là số thực lớn hơn 0, xin moi nhap lai!");
            }
        } while (true);
    }

    private void inputStopNumber(){
        System.out.println("Nhập số điểm dừng:");
        stopNumber = -1;
        do {
            try {
                stopNumber = new Scanner(System.in).nextInt();
                if (stopNumber > 0){
                    break;
                }
                System.out.println("Số điểm dừng là số nguyên dương, xin mời nhập lại");
            } catch (InputMismatchException ex){
                System.out.println("Số điểm dừng là số nguyên dương, xin mời nhập lại");
            }
        } while (true);
    }
}
