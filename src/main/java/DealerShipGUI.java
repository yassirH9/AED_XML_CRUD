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
    private String XMLDBFILEPATH = "./CarDealerDB.xml";
    private JPanel contentPane;
    private JButton readFromXMLButton;
    private JButton generateXMLFileButton;
    private JTextArea textArea1;
    private JComboBox dealerComboBox1;
    private JButton editButton;


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
        textArea1.setText("");

        carsdealers.forEach((dealer)->{
            textArea1.append("Dealer {"+" Tlf: "+dealer.gettfn()+" - Location: "+dealer.getlocation()+" }\n");
//            dealerComboBox1.add(dealer.)
            dealer.getcars().forEach((car -> {

                textArea1.append("<=3 "+car.getBrand()+" "+car.getModel()+" - "+car.getModelYear()+"\n");

            }));
        });

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
        Dealer.setLocation("Las palmas");
        Dealer.setTfn("605537464");
        Dealer.setCarInStock(carList);

        CarDealer Dealer2 = new CarDealer();
        Dealer2.setLocation("Telde");
        Dealer2.setTfn("65419864");
        Dealer2.setCarInStock(carList);


        carDealerList.add(Dealer);
        carDealerList.add(Dealer2);


        DealershipList dealershipList = new DealershipList();
        dealershipList.setCarDealerList(carDealerList);

        //write xml file
        JAXBContext context = JAXBContext.newInstance(DealershipList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
        System.out.println("Escirbiendo XML");
        //creacion del archivo
        marshaller.marshal(dealershipList, new File(XMLDBFILEPATH));
    }
}
