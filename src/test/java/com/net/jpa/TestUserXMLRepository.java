package com.net.jpa;

import com.net.domain.UserXML;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@SpringBootTest
public class TestUserXMLRepository {
    private Logger logger = LoggerFactory.getLogger(TestUserXMLRepository.class);
    private String fileName = "src\\test\\resources\\com\\net\\jpa\\UserXML.xml";

    @Autowired
    private UserXMLRepository userXMLRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    public void testFindOne() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:com/net/jpa/UserXML.xml");
        byte[] bdata = FileCopyUtils.copyToByteArray(resource.getInputStream());
        UserXML newUser = new UserXML();
        newUser.setId(1L);
        newUser.setXmlBytes(bdata);
        userXMLRepository.save(newUser);
        Optional<UserXML> optionalUser = userXMLRepository.findById((long) 1);
        UserXML user = optionalUser.get();
        logger.info(user.toString());
    }

    @Test
    public void testSave1() throws Exception {
        Resource resource = resourceLoader.getResource("classpath:com/net/jpa/UserXML.xml");
        byte[] bdata = FileCopyUtils.copyToByteArray(resource.getInputStream());
        String xml = new String(bdata, StandardCharsets.UTF_8);
        UserXML userXML = new UserXML();
        userXML.setId((long) 2);
        userXML.setXmlBytes(xml.getBytes());
        UserXML user = userXMLRepository.save(userXML);
        logger.info(user.toString());
    }

    @Test
    public void testSave2() throws Exception {
        StringBuffer sb = new StringBuffer();
        Files.lines(Paths.get(fileName)).forEach(line-> sb.append(line));
        String xml = sb.toString();
        UserXML userXML = new UserXML();
        userXML.setId((long) 2);
        userXML.setXmlBytes(xml.getBytes());
        UserXML user = userXMLRepository.save(userXML);
        logger.info(user.toString());
    }
}
