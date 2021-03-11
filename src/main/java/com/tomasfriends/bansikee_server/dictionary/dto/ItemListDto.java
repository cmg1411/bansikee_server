package com.tomasfriends.bansikee_server.dictionary.dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name="body")
public class ItemListDto {

    private List<ItemDto> items;


    @XmlElementWrapper(name="items")
    @XmlElement(name="item")
    public List<ItemDto> getItems(){
        return items;
    }

    public void setItems(List<ItemDto> items){
        this.items = items;
    }

}
