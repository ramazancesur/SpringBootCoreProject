package com.stok.ramazan.jaxb.sms;

import javax.xml.bind.annotation.*;
import java.io.Serializable;


/**
 * Created by LocalAdmin on 09.06.2017.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mainbody", propOrder = {"header", "body"})
@XmlRootElement(name = "mainbody")
public class MainBody implements Serializable {
    @XmlElement(name = "header")
    private Header header;
    @XmlElement(name = "body")
    private Body body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}