import models.Car;
import models.CarDealer;
import models.DealershipList;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class
DealerShipGUI extends JDialog {
    public DealershipList dealershipList = new DealershipList();
    private String XMLDBFILEPATH = "./CarDealerDB.xml";
    private JPanel contentPane;
    private JButton readFromXMLButton;
    private JButton generateXMLFileButton;
    private JButton deleteXMLButton;
    private JTextArea textArea1;
    private JComboBox dealerComboBox1;
    private JButton newCarButton;
    private JTextField hpTextField;
    private JTextField yearTextField;
    private JTextField modelTextField;
    private JTextField brandTextField;
    private JTextField doorsTextField;
    private JLabel Model;
    private JLabel Year;
    private JLabel HP;
    private JLabel Doors;

    public DealerShipGUI() {
        setContentPane(contentPane);
        setModal(true);
        setTitle("DealerShips CRUD Repository");


        if (new File(XMLDBFILEPATH).exists()) {
            try{
                indata();
            }catch (Exception ex){
                ex.printStackTrace();
                System.out.println(ex);
            }
            generateXMLFileButton.setEnabled(false);
        } else {
            readFromXMLButton.setEnabled(false);
            deleteXMLButton.setEnabled(false);
            newCarButton.setEnabled(false);
        }

        this.setMinimumSize(new Dimension(500, 500));

        this.generateXMLFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    outdata();
                }catch (Exception ex){
                    ex.printStackTrace();
                    System.out.println(ex);
                }
            }
        });
        this.readFromXMLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    indata();
                }catch (Exception ex){
                    ex.printStackTrace();
                    System.out.println(ex);
                }
            }
        });

        this.newCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dealer = dealerComboBox1.getSelectedIndex();
                //List <CarDealer> cdList = dealershipList.getList();

                dealershipList.getList().forEach((x)->{
                    if(x.getid() == dealer){
                        int hpInt = Integer.parseInt(hpTextField.getText());
                        int doorsInt = Integer.parseInt(doorsTextField.getText());

                        Car newCar = new Car();
                        newCar.setBrand(brandTextField.getText());
                        newCar.setModel(modelTextField.getText());
                        newCar.setModelYear(yearTextField.getText());
                        newCar.setHp((hpInt));
                        newCar.setnDoors(doorsInt);
                        x.cnCar(newCar);

                        try{
                            readXML();
                        }catch (Exception ex){
                            ex.printStackTrace();
                            System.out.println(ex);
                        }
                    }
                });
            }
        });

        this.deleteXMLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new File(XMLDBFILEPATH).delete();
                textArea1.setText("");
                dealerComboBox1.removeAllItems();
                generateXMLFileButton.setEnabled(true);
                readFromXMLButton.setEnabled(false);
                deleteXMLButton.setEnabled(false);
                newCarButton.setEnabled(false);
            }
        });
    }



    public static void main(String[] args) {
        DealerShipGUI dialog = new DealerShipGUI();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);

    }
    public void indata() throws JAXBException, FileNotFoundException {
        //lectura del xml
        JAXBContext context = JAXBContext.newInstance(DealershipList.class);
        Unmarshaller um = context.createUnmarshaller();
        DealershipList deallistRead = (DealershipList) um.unmarshal(new FileReader(XMLDBFILEPATH));
        List<CarDealer> carsdealers = deallistRead.getList();
        dealershipList.setCarDealerList(carsdealers);
        textArea1.setText("");
        dealerComboBox1.removeAllItems();

        carsdealers.forEach((dealer)->{
            textArea1.append("Dealer {"+" Tlf: "+dealer.gettfn()+" - Location: "+dealer.getlocation()+" }\n");
            dealerComboBox1.addItem(dealer.getname());
            dealer.getcars().forEach((car -> {

                textArea1.append("<==3 "+car.getBrand()+" "+car.getModel()+" - "+car.getModelYear()+"\n");

            }));
        });
        generateXMLFileButton.setEnabled(false);
        readFromXMLButton.setEnabled(true);
        deleteXMLButton.setEnabled(true);
        newCarButton.setEnabled(true);
    }
    public void outdata() throws JAXBException, FileNotFoundException {
        List<Car> carList = new ArrayList<Car>();
        List<CarDealer> carDealerList = new ArrayList<CarDealer>();
        //test de relacion
        Car car1 = new Car();
        car1.setBrand("ford");
        car1.setModel("fiesta");
        car1.setHp(100);
        car1.setModelYear("2019");
        car1.setnDoors(3);


        Car car2 = new Car();
        car2.setBrand("ford");
        car2.setModel("escort");
        car2.setHp(90);
        car2.setModelYear("1996");
        car2.setnDoors(5);

        carList.add(car1);
        carList.add(car2);


        CarDealer Dealer = new CarDealer();
        Dealer.setname("Macaroni");
        Dealer.setLocation("Las palmas");
        Dealer.setTfn("605537464");
        Dealer.setCarInStock(carList);
        Dealer.setid(0);

        CarDealer Dealer2 = new CarDealer();
        Dealer2.setname("SilboCars");
        Dealer2.setLocation("Telde");
        Dealer2.setTfn("65419864");
        Dealer2.setCarInStock(carList);
        Dealer2.setid(1);


        carDealerList.add(Dealer);
        carDealerList.add(Dealer2);


        dealershipList.setCarDealerList(carDealerList);

        readXML();
    }
    public void readXML() throws JAXBException, FileNotFoundException{
        //write xml file
        JAXBContext context = JAXBContext.newInstance(DealershipList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
        System.out.println("Escirbiendo XML");
        //creacion del archivo
        marshaller.marshal(dealershipList, new File(XMLDBFILEPATH));
        indata();
    }
}
