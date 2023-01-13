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


    public void cnDealer(CarDealer cardealer){//create new dealer

    }
    public void rmDealer(int id){//remove dealer

    }
    public void edDealer(CarDealer cardealer,int id){//edit dealer

    }
    public void gDealer(int id){//get dealer

    }

    public void cnCar(Car car, int idCarDealer){//create new car

    }
    public void rmCar(int id){//remove car

    }
    public void edCar(Car car, int id){//edit car

    }
    public void gCar(int id){//get car

    }


}
