package com.solvd.socialNetwork;

import com.solvd.socialNetwork.dao.jdbcMySQLImpl.UserDaoImpl;
import com.solvd.socialNetwork.model.order.Product;
import com.solvd.socialNetwork.model.user.User;
import com.solvd.socialNetwork.utils.Deadlock;
import com.solvd.socialNetwork.utils.Jackson;
import com.solvd.socialNetwork.utils.JaxBHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.sql.SQLException;

@SpringBootApplication
public class SocialNetworkApplication {

    final static Logger LOGGER = LogManager.getLogger(SocialNetworkApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SocialNetworkApplication.class, args);
        LOGGER.info("Hello, springboot");
//        // deserializer test
//        try {
//            User test = Jackson.deserialize(new File("src/main/resources/jsonObjects/user.json"),User.class);
//            LOGGER.info(test.getUsername());
//            LOGGER.info(test.getPassword());
//        } catch (Exception e) {
//            LOGGER.error(e);
//        }
//
//        //serializer test
//        try {
//            User test = new User("ethanjolles", "solvdIntern123");
//            Jackson.serialize("test", test);
//        } catch (Exception e) {
//            LOGGER.error(e);
//        }
//
//       //database query test for 'i' amount of queries
//        for (int i = 1; i <= 1; i++) {
//            try {
//                UserDaoImpl userDao = new UserDaoImpl();
//                User test = new User("test" + i,"" + i);
//                userDao.create(test);
//                LOGGER.info("Entry:" + i + " completed");
//            } catch (SQLException e) {
//                LOGGER.error(e);
//            }
//        }
//
//        //JaxB unmarshaller
//        try {
//            LOGGER.info(JaxBHandler.unmarshal("product.xml", Product.class));
//        } catch (JAXBException e) {
//            LOGGER.error(e);
//        }
//
//        try {
//            User userObj = new User("Test2", "Test2");
//            LOGGER.info("Attempting to marshal");
//            JaxBHandler.marshal(userObj, User.class, "userObj");
//            LOGGER.info("Marshal completed");
//        } catch (JAXBException e) {
//            LOGGER.error(e);
//        }
//        Deadlock multi = new Deadlock();
//
////        Deadlock
//        multi.t1.start();
//        multi.t2.start();
    }
}
