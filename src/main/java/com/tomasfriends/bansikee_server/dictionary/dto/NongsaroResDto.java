package com.tomasfriends.bansikee_server.dictionary.dto;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "response")
public class NongsaroResDto {

    private ItemListDto body;

    public ItemListDto getBody() {return body;}
    public void setBody(ItemListDto body) {this.body = body;}

}
