package com.net.jpa;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "user_xml")
public class UserXML {

    @Id
    private Long id;
    @Lob
    @Column(name = "xml")
    private byte[] xmlBytes;

    public void setId(Long id) {
        this.id = id;
    }

    public void setXmlBytes(byte[] xmlBytes) {
        this.xmlBytes = xmlBytes;
    }

    public Long getId() {
        return id;
    }

    public byte[] getXmlBytes() {
        return xmlBytes;
    }

    @Override
    public String toString() {
        return "UserXML{" +
                "id=" + id +
                ", xmlBytes=" + new String(xmlBytes) +
                '}';
    }
}
