package models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Car")
public class Car {
    private int carid;
    //branding
    private String Brand;
    private String Model;
    private String modelYear;//para espesificar el año de salida
    //technical
    private int hp; //caballos
    //esthetic
    private int nDoors; //numero de puertas
    public String getBrand() {
        return Brand;
    }
    public String getModel() {
        return Model;
    }

    public int getid(){
        return this.carid;
    }
    public void setid(int id){
        this.carid = id;
    }

    public int getHp() {
        return hp;
    }

    public int getnDoors() {
        return nDoors;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public void setModel(String model) {
        Model = model;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setnDoors(int nDoors) {
        this.nDoors = nDoors;
    }
}
