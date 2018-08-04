package hu.krisztn.common;

import hu.krisztn.entity.user.User;
import hu.krisztn.interfaces.common.CommonService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.time.LocalDate;

/**
 * Created by Norbert Kriszt (norbert.kriszt@gmail.com) on 2018. 08. 03..
 */
@Startup
@Singleton
public class TestServiceImpl {

    @Inject
    private CommonService commonService;

    @PostConstruct
    public void init() {
        User user = new User();
        user.setPassword("admin123");
        user.setBirthDate(LocalDate.of(1989, 05, 29));
        user.setEmail("norbert.kriszt@gmail.com");
        user.setLastName("Admin");
        user.setFirstName("Admin");
        user.setUserName("admin");

        commonService.save(user);
    }
}
