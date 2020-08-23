package com.net.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserXMLRepository {
    private Logger logger = LoggerFactory.getLogger(TestUserXMLRepository.class);
    private String fileName = "\\src\\test\\resources\\com\\net\\jpa\\UserXML.xml";

    @Autowired
    private UserXMLRepository userXMLRepository;

    @Test
    public void testFindOne(){
        Optional<UserXML> optionalUser = userXMLRepository.findById((long) 1);
        UserXML user = optionalUser.get();
        logger.info(user.toString());
    }

    @Test
    public void testSave() throws Exception {
        StringBuffer sb = new StringBuffer();
        Files.lines(Paths.get(fileName)).forEach(line-> sb.append(line));
        String xml = sb.toString();
        UserXML userXML = new UserXML();
        userXML.setId((long) 2);
        userXML.setXmlBytes(xml.getBytes());
        UserXML user = userXMLRepository.save(userXML);
        logger.info(userXML.toString());
    }
}
