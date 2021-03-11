package com.tomasfriends.bansikee_server.dictionary.dto;


import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name="item")
public class ItemDto {

    private Integer cntntsNo;

    private String cntntsSj;

    private String rtnStreFileNm;

//    @Override
//    public String toString(){
//        return "번호: " + cntntsNo + "  식물명: " + cntntsSj;
//    }
}
