package Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Scanner;

@Entity
@Table(name = "driver")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Driver implements Serializable {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "driver5_seq")
    @SequenceGenerator(name = "driver5_seq", sequenceName = "driver5_seq", allocationSize = 1, initialValue = 10000)
    private int driverId;
    @Column(name = "DRIVER_NAME")
    private String fullName;
    @Column(name = "DRIVER_ADDRESS")
    private String address;
    @Column(name = "DRIVER_PHONE")
    private String phoneNumber;
    @Column(name = "DRIVER_LEVEL")
    private String level;

    public void inputInfo() {
        System.out.print("Nhap vao ten lái xe: ");
        this.setFullName(new Scanner(System.in).nextLine());
        System.out.print("Nhap vao dia chi lái xe: ");
        this.setAddress(new Scanner(System.in).nextLine());
        System.out.print("Nhap so dien thoai: ");
        this.setPhoneNumber(new Scanner(System.in).nextLine());
        inputLevel();
    }

    private void inputLevel(){
        System.out.println("Nhap trinh do lai xe: ");
        String leveDriver = "";
        do {
            leveDriver = new Scanner(System.in).nextLine();
            if (leveDriver.matches("[a-fA-F]")){
                level = leveDriver;
                break;
            }
            System.out.println("Trình đồ từ A - F, xin mời nhập lại!");
        } while (true);
    }
}
