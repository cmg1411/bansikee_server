package com.tomasfriends.bansikee_server.dictionary.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "response")
public class PlantDataResDto {

    private List<PlantDto> plants;

    @XmlElementWrapper(name="body")
    @XmlElement(name="item")
    public List<PlantDto> getPlants(){
        return plants;
    }

    public void setPlants(List<PlantDto> plants){
        this.plants = plants;
    }

}
