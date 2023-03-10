package models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class CarDealer {
    @XmlAttribute
    private String location;
    @XmlAttribute
    private String tfn;
    @XmlElement(name = "car")
    private List<Car> carInStock;//lista de coches de ese consecionario
    private int dealerID;
    private String name;

    public String getname(){
        return this.name;
    }
    public void setname(String name){
        this.name = name;
    }

    public void setCarInStock(List<Car> carStock) {
        this.carInStock = carStock;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTfn(String tfn) {
        this.tfn = tfn;
    }

    public String gettfn() {
        return this.tfn;
    }

    public String getlocation() {
        return this.location;
    }

    public List<Car> getcars() {
        return this.carInStock;
    }

    public int getid() {
        return this.dealerID;
    }

    public void setid(int id) {
        this.dealerID = id;
    }

    //Cars crud controls
    public void cnCar(Car car) {//create new car
        carInStock.add(car);
    }
}
