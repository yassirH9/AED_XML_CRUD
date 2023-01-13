package models;

import javax.lang.model.element.Name;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name="DealerShips", namespace = "com.yassir.xml.jaxb.gradle.model")
public class DealershipList {
//    @XmlElementWrapper(name = "DealerShip")
    @XmlElement(name = "DealerShip")
    private List<CarDealer> CarsInStock;
    public void setCarDealerList(List<CarDealer> carDealerList) {
        this.CarsInStock = carDealerList;
    }
    public List<CarDealer> getList(){
        return this.CarsInStock;
    }



    //CarDealer crud controls
    public void cnDealer(CarDealer cardealer){//create new dealer
        CarsInStock.add(cardealer);
    }

}
