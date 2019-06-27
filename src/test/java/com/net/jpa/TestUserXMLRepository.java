package com.net.jpa;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.InputStream;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserXMLRepository {
    private String fileName = "com/net/jpa/UserXML.xml";

    @Autowired
    private UserXMLRepository userXMLRepository;

    @Test
    public void testFindOne(){
        Optional<UserXML> optionalUser = userXMLRepository.findById((long) 1);
        UserXML user = optionalUser.get();
    }

    @Test
    public void testSave() throws Exception {
        InputStream input = this.getClass().getClassLoader().getResourceAsStream(fileName);
        String xml = IOUtils.toString(input, "UTF-8");
        UserXML userXML = new UserXML();
        userXML.setId((long) 1);
        userXML.setXmlBytes(xml.getBytes());
        UserXML user = userXMLRepository.save(userXML);
    }
}
