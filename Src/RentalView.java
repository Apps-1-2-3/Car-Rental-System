import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class RentalView extends JFrame{
    private Transaction aTrans = new Transaction(); //accessing class Transaction as global declaration
    private Customer aCustomer = new Customer(); //accessing  Customer as global declaration
    private Car aCar = new Car(); //accessing Car
    WarningReserve openWarningReserve; //class to access warning window
    WarningCustomer openWarningCustomer; //even more warnings
    WarningCar openWarningCar;// another one

    Container pane = getContentPane();
    private JPanel mainP, reserveP, customerP, carP, finalP; //panels for CardLayout

    //////////////////////////////////////////////welcome////////////////////////////////////////////////////
    private JLabel titleL, dateL; //
    private JButton mainNextB;
    private JComboBox cMCB, cDCB, cYCB;

    //////////////////////////////////////////////date reservation////////////////////////////////////////////
    private JLabel dayL, pickupDateL, returnDateL, birthDateL; //inside the panel of reserveP
    private JComboBox pUMCB, pUDCB, pUYCB, rMCB, rDCB, rYCB, bMCB, bDCB, bYCB;
    private JLabel dateChecker, ageChecker;
    private JButton reserveNextB, reserveBackB;

    ////////////////////////////////////////////////customer info//////////////////////////////////////
    private JLabel customerL, firstNameL, lastNameL, locationL, contactL, genderL, ageL,showAgeL;
    private JTextField firstNameTF, lastNameTF, locationTF, contactTF, genderTF ;
    private JLabel firstNameChecker, lastNameChecker, genderChecker, locationChecker, contactChecker;
    private JButton customerNextB, customerBackB;

    ////////////////////////////////////////////////pick a car/////////////////////////////////
    private JLabel carL; //inside the panel of carP
    private JButton firstCarB, secondCarB, thirdCarB, forthCarB, carNextB, carBackB;
    private JTextArea carDetailsTA;

    ///////////////////////////////////////////////receipt/////////////////////////////////////
    private JTextArea receiptA;

    public RentalView() {
        /////////////////////////////////////////// First Panel ////////////////////////////////
        pane.setLayout(new CardLayout());
        mainP = new JPanel(null);
        mainP.setBackground(Color.BLACK);

        ImageIcon titleImage = new ImageIcon("../images/Logo.png");
        titleL = new JLabel(titleImage);
        titleL.setBounds(1, 1, 600, 500);
        mainP.add(titleL);

        dateL = new JLabel("DATE TODAY"); //date today label
        dateL.setFont(new Font("Serif", Font.BOLD, 23));
        dateL.setForeground(Color.RED);
        dateL.setBounds(700, 150, 200, 25);
        mainP.add(dateL);

        //------------------------------ BOX for current date
        String[] currentMonthCB = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        cMCB = new JComboBox(currentMonthCB);
        cMCB.setBounds(700, 190, 60, 25);
        mainP.add(cMCB);

        Integer[] currentDateCB = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
        cDCB = new JComboBox(currentDateCB);
        cDCB.setBounds(765, 190, 60, 25);
        mainP.add(cDCB);

        Integer[] currentYearCB = {2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023 ,2024};
        cYCB = new JComboBox(currentYearCB);
        cYCB.setBounds(830, 190, 60, 25);
        mainP.add(cYCB);

        //--------------------------------BUTTONS to go reserve section
        ImageIcon mainNextImage = new ImageIcon("../images/next.jpg");
        mainNextB = new JButton(mainNextImage);
        mainNextB.setBackground(Color.WHITE);
        mainNextB.addActionListener(new mainNextButton());
        mainNextB.setBounds(800, 300, 100, 50);
        mainNextB.setRolloverEnabled(true);
        mainNextB.setRolloverIcon(mainNextImage);
        mainNextB.setPressedIcon(mainNextImage);
        mainNextB.setActionCommand("NextToReserve");
        mainP.add(mainNextB);
        pane.add(mainP, "welcomeForm");

        //////////////////////////////////////////////////Second Panel "reserve" (reserveP)//////////////////////////////////////////////
        //-----------------------------------------LABELS for reservation date.
        reserveP = new JPanel(null);
        reserveP.setBackground(Color.BLACK);

        dayL = new JLabel("Reserve Rental Day(s):");
        dayL.setFont(new Font("Verdana", Font.BOLD, 18));
        dayL.setForeground(Color.BLUE);
        dayL.setBounds(50, 1, 250, 100);
        reserveP.add(dayL);

        pickupDateL = new JLabel("Pick Up Date:");
        pickupDateL.setFont(new Font("Serif", Font.BOLD, 15));
        pickupDateL.setForeground(Color.YELLOW);
        pickupDateL.setBounds(50, 40, 100, 100);
        reserveP.add(pickupDateL);

        returnDateL = new JLabel("Return Date:");
        returnDateL.setFont(new Font("Serif", Font.BOLD, 15));
        returnDateL.setForeground(Color.YELLOW);
        returnDateL.setBounds(50, 80, 100, 100);
        reserveP.add(returnDateL);

        birthDateL = new JLabel("Birth Date:");
        birthDateL.setFont(new Font("Serif", Font.BOLD, 15));
        birthDateL.setForeground(Color.YELLOW);
        birthDateL.setBounds(60, 150, 100, 100);
        reserveP.add(birthDateL);

        //-------------------------------------------------CHECK BOX for pickup date, return date, birth date
        //---------------------------------------------Pick up date section
        String[] pickUpMonthCB = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        pUMCB = new JComboBox(pickUpMonthCB);
        pUMCB.setBounds(150, 80, 60, 25);
        reserveP.add(pUMCB);

        Integer[] pickUpDateCB = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
        pUDCB = new JComboBox(pickUpDateCB);
        pUDCB.setBounds(215, 80, 60, 25);
        reserveP.add(pUDCB);

        Integer[] pickUpYearCB = {2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024};
        pUYCB = new JComboBox(pickUpYearCB);
        pUYCB.setBounds(280, 80, 60, 25);
        reserveP.add(pUYCB);

        //-------------------------------------------return date section
        String[] returnMonthCB = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        rMCB = new JComboBox(returnMonthCB);
        rMCB.setBounds(150, 120, 60, 25);
        reserveP.add(rMCB);

        Integer[] returnDateCB = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
        rDCB = new JComboBox(returnDateCB);
        rDCB.setBounds(215, 120, 60, 25);
        reserveP.add(rDCB);

        Integer[] returnYearCB = {2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020 , 2021 , 2022, 2023, 2024};
        rYCB = new JComboBox(returnYearCB);
        rYCB.setBounds(280, 120, 60, 25);
        reserveP.add(rYCB);

        //---------------------------------------------birth date section
        String[] birthMonthCB = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        bMCB = new JComboBox(birthMonthCB);
        bMCB.setBounds(150, 190, 60, 25);
        reserveP.add(bMCB);

        Integer[] birthDateCB = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
        bDCB = new JComboBox(birthDateCB);
        bDCB.setBounds(215, 190, 60, 25);
        reserveP.add(bDCB);

        Integer[] birthYearCB = {1970, 1971, 1972, 1973, 1974, 1975, 1976, 1977, 1978, 1979, 1980,
                1981, 1982, 1983, 1984, 1985, 1986, 1987, 1988, 1989, 1990,
                1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998, 1999, 2000,
                2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010,
                2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022};
        bYCB = new JComboBox(birthYearCB);
        bYCB.setBounds(280, 190, 60, 25);
        reserveP.add(bYCB);

        //---------------------------------------------checking for date
        dateChecker = new JLabel("");
        dateChecker.setFont(new Font("Serif", Font.ITALIC, 15));
        dateChecker.setBounds(350, 83, 100, 100);
        reserveP.add(dateChecker);

        ageChecker = new JLabel("");
        ageChecker.setFont(new Font("Serif", Font.ITALIC, 15));
        ageChecker.setBounds(350, 153, 100, 100);
        reserveP.add(ageChecker);

        //--------------------------------------BUTTONS to go customer section ---------------------------------- //
        ImageIcon dateNextImage = new ImageIcon("../images/customer.jpg");
        reserveNextB = new JButton(dateNextImage);
        reserveNextB.setBackground(Color.WHITE);
        reserveNextB.addActionListener(new reserveNextButton());
        reserveNextB.setBounds(460, 300, 100, 50);
        reserveNextB.setActionCommand("NextToCustomer");
        reserveP.add(reserveNextB);

        //---------------------------------------------------to go back to welcome section
        ImageIcon dateBackImage = new ImageIcon("../images/back.jpg");
        reserveBackB = new JButton(dateBackImage);
        reserveBackB.setBackground(Color.WHITE);
        reserveBackB.setBounds(330, 300, 100, 50);
        reserveBackB.addActionListener(new reserveBackButton());
        reserveBackB.setActionCommand("BackToWelcome");
        reserveP.add(reserveBackB);
        pane.add(reserveP, "reserveForm");


        //////////////////////////////////////////////////////////third Panel "customer" (customerP)/////////////////////////////////////
        //---------------------------------------------LABELS for customer details
        customerP = new JPanel(null);
        customerP.setBackground(Color.BLACK);

        customerL = new JLabel("Customer's Data");
        customerL.setFont(new Font("Verdana", Font.BOLD, 20));
        customerL.setForeground(Color.RED);
        customerL.setBounds(50, 10, 250, 30);
        customerP.add(customerL);

        firstNameL = new JLabel("First Name:");
        firstNameL.setFont(new Font("Serif", Font.BOLD, 15));
        firstNameL.setForeground(Color.BLUE);
        firstNameL.setBounds(50, 60, 250, 30);
        customerP.add(firstNameL);

        lastNameL = new JLabel("Last Name:");
        lastNameL.setFont(new Font("Serif", Font.BOLD, 15));
        lastNameL.setForeground(Color.BLUE);
        lastNameL.setBounds(50, 95, 250, 30);
        customerP.add(lastNameL);

        genderL = new JLabel("Gender:");
        genderL.setFont(new Font("Serif", Font.BOLD, 15));
        genderL.setForeground(Color.BLUE);
        genderL.setBounds(50, 130, 250, 30);
        customerP.add(genderL);


        locationL = new JLabel("Location:");
        locationL.setFont(new Font("Serif", Font.BOLD, 15));
        locationL.setForeground(Color.BLUE);
        locationL.setBounds(50, 200, 250, 30);
        customerP.add(locationL);

        contactL = new JLabel("Contact:");
        contactL.setFont(new Font("Serif", Font.BOLD, 15));
        contactL.setForeground(Color.BLUE);
        contactL.setBounds(50, 235, 250, 30);
        customerP.add(contactL);

        ageL = new JLabel("Age:");
        ageL.setFont(new Font("Serif", Font.BOLD, 15));
        ageL.setForeground(Color.BLUE);
        ageL.setBounds(50, 270, 250, 30);
        customerP.add(ageL);

        showAgeL = new JLabel("");
        showAgeL.setFont(new Font("Serif", Font.PLAIN, 15));
        showAgeL.setHorizontalAlignment(JTextField.CENTER);
        showAgeL.setForeground(Color.BLUE);
        showAgeL.setBounds(145, 270, 60, 30);
        customerP.add(showAgeL);

        //-------------------------------------TEXT FIELD for customer details and get data from keyboard section
        firstNameTF = new JTextField(""); //
        firstNameTF.setHorizontalAlignment(JTextField.CENTER);
        firstNameTF.setForeground(Color.GRAY);
        firstNameTF.setBounds(150, 60, 250, 30);
        customerP.add(firstNameTF);

        lastNameTF = new JTextField(""); //
        lastNameTF.setHorizontalAlignment(JTextField.CENTER);
        lastNameTF.setForeground(Color.GRAY);
        lastNameTF.setBounds(150, 95, 250, 30);
        customerP.add(lastNameTF);

        genderTF = new JTextField("");
        genderTF.setHorizontalAlignment(JTextField.CENTER);
        genderTF.setForeground(Color.GRAY);
        genderTF.setBounds(150, 130, 50, 30);
        customerP.add(genderTF);

        locationTF = new JTextField("");
        locationTF.setHorizontalAlignment(JTextField.CENTER);
        locationTF.setForeground(Color.GRAY);
        locationTF.setBounds(150, 200, 250, 30);
        customerP.add(locationTF);

        contactTF = new JTextField("");
        contactTF.setHorizontalAlignment(JTextField.CENTER);
        contactTF.setForeground(Color.GRAY);
        contactTF.setBounds(150, 235, 250, 30);
        customerP.add(contactTF);

        //-----------------checker customer

        firstNameChecker = new JLabel("");
        firstNameChecker.setFont(new Font("Serif", Font.BOLD, 15));
        firstNameChecker.setBounds(410, 25, 100, 100);
        customerP.add(firstNameChecker);

        lastNameChecker = new JLabel("");
        lastNameChecker.setFont(new Font("Serif", Font.BOLD, 15));
        lastNameChecker.setBounds(410, 60, 100, 100);
        customerP.add(lastNameChecker);

        genderChecker = new JLabel("");
        genderChecker.setFont(new Font("Serif", Font.BOLD, 15));
        genderChecker.setBounds(210, 95, 100, 100);
        customerP.add(genderChecker);

        locationChecker = new JLabel("");
        locationChecker.setFont(new Font("Serif", Font.BOLD, 15));
        locationChecker.setBounds(410, 165, 100, 100);
        customerP.add(locationChecker);

        contactChecker = new JLabel("");
        contactChecker.setFont(new Font("Serif", Font.BOLD, 15));
        contactChecker.setBounds(410, 200, 100, 100);
        customerP.add(contactChecker);

        //-------------------------------------------BUTTONS for customer
        //----------------------------------------------to go pick a car section
        ImageIcon customerNextImage = new ImageIcon("../images/car.jpg");
        customerNextB = new JButton(customerNextImage);
        customerNextB.setBackground(Color.WHITE);
        customerNextB.setBounds(460, 300, 100, 50);
        customerNextB.setRolloverEnabled(true);
        customerNextB.setRolloverIcon(customerNextImage);
        customerNextB.setPressedIcon(customerNextImage);
        customerNextB.addActionListener(new customerNextButton());
        customerNextB.setActionCommand("NextToPickCar");
        customerP.add(customerNextB);

        //--------------------------------------------to go back reserve section
        ImageIcon customerBackImage = new ImageIcon("../images/customer_back.jpg");
        customerBackB = new JButton(customerBackImage);
        customerBackB.setBackground(Color.WHITE);
        customerBackB.setBounds(330, 300, 100, 50);
        customerBackB.setRolloverEnabled(true);
        customerBackB.setRolloverIcon(customerBackImage);
        customerBackB.setPressedIcon(customerBackImage);
        customerBackB.addActionListener(new customerBackButton());
        customerBackB.setActionCommand("backToReserve");
        customerP.add(customerBackB);
        pane.add(customerP, "customerForm");


        /////////////////////////////////////////////////////fourth Panel "pick a car" (carP)//////////////////////////////////////////
        //--------------------------------LABELS for pick a car
        carP = new JPanel(null);
        carP.setBackground(Color.DARK_GRAY);

        carL = new JLabel("Car Detail's");
        carL.setFont(new Font("Verdana", Font.PLAIN, 18));
        carL.setForeground(Color.BLUE);
        carL.setBounds(435, 4, 140, 30);
        carP.add(carL);

        //------------------------------------BUTTONS for pick a car
        //---------------------------------------------------------Pick Car Buttton]
        //-------------------------------------first car button
        ImageIcon firstCarImage = new ImageIcon("../images/Creta.jpg");
        firstCarB = new JButton(firstCarImage);
        firstCarB.setBackground(Color.WHITE);
        firstCarB.addActionListener(new firstCarButton());
        firstCarB.setRolloverEnabled(true);
        firstCarB.setRolloverIcon(firstCarImage);
        firstCarB.setPressedIcon(firstCarImage);
        firstCarB.setBounds(10, 10, 190, 130);
        carP.add(firstCarB);

        //---------------------------------second car button
        ImageIcon secondCarImage = new ImageIcon("../images/Omni.jpg");
        secondCarB = new JButton(secondCarImage);
        secondCarB.setBackground(Color.WHITE);
        secondCarB.addActionListener(new secondCarButton());
        secondCarB.setRolloverEnabled(true);
        secondCarB.setRolloverIcon(secondCarImage);
        secondCarB.setPressedIcon(secondCarImage);
        secondCarB.setBounds(210, 10, 190, 130);
        carP.add(secondCarB);

        //---------------------------------------third car button
        ImageIcon thirdCarImage = new ImageIcon("../images/End.jpg");
        thirdCarB = new JButton(thirdCarImage);
        thirdCarB.setBackground(Color.WHITE);
        thirdCarB.addActionListener(new thirdCarButton());
        thirdCarB.setRolloverEnabled(true);
        thirdCarB.setRolloverIcon(thirdCarImage);
        thirdCarB.setPressedIcon(thirdCarImage);
        thirdCarB.setBounds(10, 150, 190, 130);
        carP.add(thirdCarB);

        //------------------------------------------forth car button
        ImageIcon forthCarImage = new ImageIcon("../images/Thar.jpg");
        forthCarB = new JButton(forthCarImage);
        forthCarB.setBackground(Color.WHITE);
        forthCarB.addActionListener(new forthCarButton());
        forthCarB.setRolloverEnabled(true);
        forthCarB.setRolloverIcon(forthCarImage);
        forthCarB.setPressedIcon(forthCarImage);
        forthCarB.setBounds(210, 150, 190, 130);
        carP.add(forthCarB);

        //-------------------------------------------------to go the receipt section(Final step)
        ImageIcon carNextImage = new ImageIcon("../images/submit.jpg");
        carNextB = new JButton(carNextImage);
        carNextB.setBackground(Color.BLACK);
        carNextB.setBounds(650, 320, 100, 50);
        carNextB.addActionListener(new finalStepButton());
        carNextB.setRolloverEnabled(true);
        carNextB.setRolloverIcon(carNextImage);
        carNextB.setPressedIcon(carNextImage);
        carP.add(carNextB);

        //--------------------------------------------------to go back to the customer section
        ImageIcon carBackImage = new ImageIcon("../images/car_back.jpg");
        carBackB = new JButton(carBackImage);
        carBackB.setBackground(Color.WHITE);
        carBackB.addActionListener(new carBackButton());
        carBackB.setBounds(520, 320, 100, 50);
        carBackB.setRolloverEnabled(true);
        carBackB.setRolloverIcon(carBackImage);
        carBackB.setPressedIcon(carBackImage);
        carBackB.setActionCommand("BackToCustomer");
        carP.add(carBackB);

        //-----------------------------------TEXT AREA for pick a car putting details for car when we click buttons over top
        carDetailsTA = new JTextArea(1000, 1000);
        carDetailsTA.setText("Pick a Car:");
        carDetailsTA.setAutoscrolls(true);
        carDetailsTA.setEditable(false);
        carDetailsTA.setBounds(420, 40, 650, 270);
        carP.add(carDetailsTA);
        pane.add(carP, "pickCarForm");

        ///////////////////////////////////////////////////////last panel "receipt" (finalP)/////////////////////////////////////
        finalP = new JPanel(null);

        receiptA = new JTextArea(2000, 2000);
        receiptA.setFont(new Font("Serif", Font.PLAIN, 15));
        receiptA.setForeground(Color.BLUE);
        receiptA.setEditable(false);
        receiptA.setBounds(80, 30, 400, 800); //
        finalP.add(receiptA);
        pane.add(finalP, "receipt");

    }

    /////////////////////////////////////////////////////////CONTROLLERS//////////////////////////////////////////////
    //-------------------------------------main (go to reserveForm)
    private class mainNextButton implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            CardLayout cL = (CardLayout)pane.getLayout();

            if(event.getActionCommand().equals("NextToReserve")) {
                cL.show(pane, "reserveForm");
            }
        }
    }

    //------------------------------------------------reserve
    //---------------------------------------------------go to customerForm
    //-----------------------------------------------including the validation if requirements are meet for Next Panel
    private class reserveNextButton implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            CardLayout cL = (CardLayout)pane.getLayout();

            if(event.getActionCommand().equals("NextToCustomer")) {

                String currentMonth, pickUpMonth, returnMonth, birthMonth;
                int	currentDate, currentYear, pickUpDate, pickUpYear, returnDate, returnYear, birthDate, birthYear;

                currentMonth = (String)cMCB.getSelectedItem();
                currentDate = (int)cDCB.getSelectedItem();
                currentYear = (int)cYCB.getSelectedItem();

                pickUpMonth = (String)pUMCB.getSelectedItem();
                pickUpDate = (int)pUDCB.getSelectedItem();
                pickUpYear = (int)pUYCB.getSelectedItem();

                returnMonth = (String)rMCB.getSelectedItem();
                returnDate = (int)rDCB.getSelectedItem();
                returnYear = (int)rYCB.getSelectedItem();

                birthMonth = (String)bMCB.getSelectedItem();
                birthDate = (int)bDCB.getSelectedItem();
                birthYear = (int)bYCB.getSelectedItem();

                aTrans.setTransaction(aCustomer, aCar, currentMonth, currentDate, currentYear, pickUpMonth,
                        pickUpDate, pickUpYear, returnMonth, returnDate, returnYear,
                        birthMonth, birthDate, birthYear);
                showAgeL.setText(aTrans.toStringAge()); // showing text of age from class

                if(!aTrans.toStringDateChecker().equals("TRUE")) {
                    dateChecker.setForeground(Color.RED);
                    dateChecker.setText("Invalid!");
                }

                else {
                    dateChecker.setForeground(Color.GREEN);
                    dateChecker.setText("Valid");
                }

                if(!aTrans.toStringAgeChecker().equals("TRUE")) { // for age validation
                    ageChecker.setForeground(Color.RED);
                    ageChecker.setText("Invalid!");
                }

                else {
                    ageChecker.setForeground(Color.GREEN);
                    ageChecker.setText("Valid");
                }

                if(aTrans.toStringDateChecker().equals("TRUE") && aTrans.toStringAgeChecker().equals("TRUE")) { //checker to proceed next panel
                    cL.show(pane, "customerForm");
                }

                else { //pop up window for warning to customer
                    if(openWarningReserve == null){
                        openWarningReserve = new WarningReserve();
                        ImageIcon warningReserveImage = new ImageIcon("../images/warning_top_icon.GIF");
                        openWarningReserve.setIconImage(warningReserveImage.getImage());
                        openWarningReserve.setTitle("WARNING !!");
                        openWarningReserve.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        openWarningReserve.setSize(300, 150);
                        openWarningReserve.setVisible(true);
                        openWarningReserve.setResizable(false);
                    }

                    else{
                        openWarningReserve.setVisible(!openWarningReserve.isVisible());
                    }
                }
            }
        }
    }

    //-------------------------------------------------go back to welcome page
    private class reserveBackButton implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            CardLayout cL = (CardLayout)pane.getLayout();

            if(event.getActionCommand().equals("BackToWelcome")) {
                cL.show(pane, "welcomeForm");
            }
        }
    }

    //-----------------------------------------------to go to pick a a CarForm
    private class customerNextButton implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            CardLayout cL = (CardLayout)pane.getLayout();

            if(event.getActionCommand().equals("NextToPickCar")) {
                String firstName, lastName, gender, location, contact;

                firstName = firstNameTF.getText();
                lastName = lastNameTF.getText();
                gender = genderTF.getText();
                location = locationTF.getText();
                contact = contactTF.getText();

                aCustomer.setCustomer(firstName, lastName, gender,location, contact);

                if(aCustomer.getFirstName().equals("")) { // checker for firstname if empty , its invalid
                    firstNameChecker.setForeground(Color.RED);
                    firstNameChecker.setText("Invalid!");
                }

                else {
                    firstNameChecker.setForeground(Color.GREEN);
                    firstNameChecker.setText("Valid");
                }

                if(aCustomer.getLastName().equals("")) { //for last name
                    lastNameChecker.setForeground(Color.RED);
                    lastNameChecker.setText("Invalid!");
                }

                else {
                    lastNameChecker.setForeground(Color.GREEN);
                    lastNameChecker.setText("Valid");
                }

                if(aCustomer.getGender().equals("")) { //for gender
                    genderChecker.setForeground(Color.RED);
                    genderChecker.setText("Invalid!");
                }

                else {
                    genderChecker.setForeground(Color.GREEN);
                    genderChecker.setText("Valid");
                }


                if(aCustomer.getLocation().equals("")) { //for location
                    locationChecker.setForeground(Color.RED);
                    locationChecker.setText("Invalid!");
                }

                else  {
                    locationChecker.setForeground(Color.GREEN);
                    locationChecker.setText("Valid");
                }

                if(aCustomer.getContact().equals("")) { //for contact
                    contactChecker.setForeground(Color.RED);
                    contactChecker.setText("Invalid!");
                }

                else  {
                    contactChecker.setForeground(Color.GREEN);
                    contactChecker.setText("Valid");
                }

                if(!aCustomer.getFirstName().equals("") && !aCustomer.getLastName().equals("") && !aCustomer.getGender().equals("") && !aCustomer.getLocation().equals("") && !aCustomer.getContact().equals("")){
                    firstNameTF.setText(""); //if all of the above is valid , proceed to Car Form
                    lastNameTF.setText("");
                    genderTF.setText("");
                    locationTF.setText("");
                    contactTF.setText("");
                    cL.show(pane, "pickCarForm");
                }

                else { //if invalid, pop out window for warning
                    if(openWarningCustomer == null){
                        openWarningCustomer = new WarningCustomer();
                        ImageIcon warningCustomerImage = new ImageIcon("../images/warning_top_icon.GIF");
                        openWarningCustomer.setIconImage(warningCustomerImage.getImage());
                        openWarningCustomer.setTitle("WARNING INVALID!!");
                        openWarningCustomer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        openWarningCustomer.setSize(300, 150);
                        openWarningCustomer.setVisible(true);
                        openWarningCustomer.setResizable(false);
                    }

                    else{
                        openWarningCustomer.setVisible(!openWarningCustomer.isVisible());
                    }
                }
            }
        }
    }

    //----------------------------------------------go back to reserveForm
    private class customerBackButton implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            CardLayout cL = (CardLayout)pane.getLayout();

            if(event.getActionCommand().equals("backToReserve")) {
                cL.show(pane, "reserveForm");
            }
        }
    }

    //--------------------------------------------car
    //----------------------------------------------------go back to customerForm
    private class firstCarButton implements ActionListener{ //when its clicked show up the car detail of Van
        public void actionPerformed(ActionEvent event) {

            carDetailsTA.setText("");
            carDetailsTA.setText("Brand: Hyundai\nPlate #: KA 19P 8488\nType: SUV\nModel: 2015\nColor: Black\nRental Price: ₹1199.00\n\n-Creta is a compact SUV, known for its stylish design, spacious interior,and a range of features typically \nfound in higher-end vehicles.It offers good fuel efficiency, comfortable ride quality, and a choice of petrol,\ndiesel, and turbocharged engine options.It's popular for its reliability and competitive \npricing in its segment,making it a strong contender in the compact SUV market.");
            carNextB.setActionCommand("Car1");
        }
    }

    private class secondCarButton implements ActionListener{ //button to show pick-up details
        public void actionPerformed(ActionEvent event) {

            carDetailsTA.setText("");
            carDetailsTA.setText("Brand: Maruti Suzuki\nPlate #: KA 09G 2673\nType: Van\nModel: 2017\nColor: Dark Blue\nRental Price: ₹699.00\n\n-The Maruti Suzuki Omni is a versatile and affordable multipurpose van produced by the Indian automaker.\nKnown for its practical design,spacious interior,and sliding rear door, it can seat up to eight people.The Omni is\npowered by a fuel-efficient petrol engine and is available with both manual and automatic transmissions.It's widely \nused for various purposes including family transportation,commercial applications, and as a base for custom modifications,\nmaking it a popular choice in the Indian automotive market. ");
            carNextB.setActionCommand("Car2");
        }
    }

    private class thirdCarButton implements ActionListener{ //button to show Taxi details
        public void actionPerformed(ActionEvent event) {

            carDetailsTA.setText("");
            carDetailsTA.setText("Brand: Ford\nPlate #: KA 12C 4728\nType: 7 Seater SUV\nModel: 2017\nColor: Crimson Red\nRental Price: ₹1299\n\n-The Endeavour offers a spacious and well-equipped interior with three rows of seating,\nmaking it suitable for families and adventure seekers alike. \nIt comes with a range of powerful engine options, including diesel and petrol variants,\npaired with automatic transmissions for smooth driving.\nThe Endeavour is appreciated for its towing capacity, advanced features like terrain management system,\nand overall versatility, positioning it as a formidable contender in the midsize SUV segment.");
            carNextB.setActionCommand("Car3");
        }
    }

    private class forthCarButton implements ActionListener{ //button to show Bus details
        public void actionPerformed(ActionEvent event) {

            carDetailsTA.setText("");
            carDetailsTA.setText("Brand: Mahindra\nPlate #: KA 16E 6721 \nType: Off Road SUV\nModel: 2019\nColor: Apple Red\nRental Price: ₹1499.00\n\n-Mahindra Thar is renowned for its rugged design, robust build, and exceptional off-road capabilities.\nThe Thar maintains a classic Jeep-like appearance with its boxy silhouette and removable roof and doors,\noffering a true open-air driving experience. It features a range of off-road enhancements like \nhigh ground clearance and low-range transfer case.It is available with petrol and diesel engine options, \nproviding ample power for both on-road and off-road driving.It's highly regarded among enthusiasts for\nits go-anywhere attitude and remains a popular choice for off-road enthusiasts and adventure seekers.");
            carNextB.setActionCommand("Car4");
        }
    }

    private class finalStepButton implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            CardLayout cL = (CardLayout)pane.getLayout();

            if(event.getActionCommand().equals("Car1")) { //passing the pick-up details to car details class and final transactions.

                String carType = "SUV", carModel = "2015";
                double carPrice = 1199.00;

                aCar.setCar(carType, carModel, carPrice); //kani

                try { //to save in file Transaction_Inventory
                    aTrans.saveToFile();
                }catch (IOException e) {} //

                receiptA.setText(aTrans.toStringReceipt());
                cL.show(pane, "receipt");
            }

            else if(event.getActionCommand().equals("Car2")) { //pass the details of Jepp to car details

                String carType = "Sedan", carModel = "2017";
                double carPrice = 699.00;

                aCar.setCar(carType, carModel, carPrice);

                try { //to save in file Transaction_Inventory
                    aTrans.saveToFile();
                }catch (IOException e) {}

                receiptA.setText(aTrans.toStringReceipt());
                cL.show(pane, "receipt");
            }

            else if(event.getActionCommand().equals("Car3")) { //pass Van details to car details

                String carType = "7 Seater SUV", carModel = "2017";
                double carPrice = 1299.00;

                aCar.setCar(carType, carModel, carPrice);

                try { //to save in file Transaction_Inventory
                    aTrans.saveToFile();
                }catch (IOException e) {}

                receiptA.setText(aTrans.toStringReceipt());
                cL.show(pane, "receipt");
            }

            else if(event.getActionCommand().equals("Car4")) { //pass mr.bean car details to car details

                String carType = "Off Road SUV", carModel = "2019";
                double carPrice = 1499.00;

                aCar.setCar(carType, carModel, carPrice);

                try { //to save in file Transaction_Inventory
                    aTrans.saveToFile();
                }catch (IOException e) {}

                receiptA.setText(aTrans.toStringReceipt());
                cL.show(pane, "receipt");
            }

            else { //if customer have not yet pick a car, show's the warning information
                if(openWarningCar == null){
                    openWarningCar = new WarningCar();
                    ImageIcon warningCarImage = new ImageIcon("../images/warning_top_icon.GIF");
                    openWarningCar.setIconImage(warningCarImage.getImage());
                    openWarningCar.setTitle("WARNING INVALID!!");
                    openWarningCar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    openWarningCar.setSize(300, 150);
                    openWarningCar.setVisible(true);
                    openWarningCar.setResizable(false);
                }

                else{
                    openWarningCar.setVisible(!openWarningCar.isVisible());
                }
            }
        }
    }

    private class carBackButton implements ActionListener{ //go back to customer form when it is clicked and performed
        public void actionPerformed(ActionEvent event) {
            CardLayout cL = (CardLayout)pane.getLayout();

            if(event.getActionCommand().equals("BackToCustomer")) {
                cL.show(pane, "customerForm");
            }
        }
    }
}