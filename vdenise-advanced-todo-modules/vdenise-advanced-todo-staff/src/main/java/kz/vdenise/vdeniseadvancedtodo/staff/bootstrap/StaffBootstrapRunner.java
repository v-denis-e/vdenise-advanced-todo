package kz.vdenise.vdeniseadvancedtodo.staff.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("bootstrapStaffRunner")
public class StaffBootstrapRunner implements Runnable {

    @Override
    public void run() {
        log.info("Staff runner started!");

        log.info("Staff runner finished!");
    }

}
