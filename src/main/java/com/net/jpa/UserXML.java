package com.net.jpa;

import javax.persistence.*;

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
}
