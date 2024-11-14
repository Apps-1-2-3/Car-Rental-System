import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Rental{
    private static final int WIDTH = 1150;
    private static final int HEIGHT = 600;

    //////////////////////////////////////////////////////////MAIN//////////////////////////////////////////
    public static void main(String[] args) {

        JFrame frame = new RentalView();//--- Car,Customer,Transaction,Warning car,customers,reserve.
        ImageIcon img = new ImageIcon("Project/images/Logo.png");
        frame.setIconImage(img.getImage());
        frame.setTitle("CAR RENTAL SYSTEM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
