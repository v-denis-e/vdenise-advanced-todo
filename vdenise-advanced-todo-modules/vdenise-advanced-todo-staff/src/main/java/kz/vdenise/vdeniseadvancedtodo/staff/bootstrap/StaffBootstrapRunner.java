package kz.vdenise.vdeniseadvancedtodo.staff.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component("bootstrapStaffRunner")
public class StaffBootstrapRunner implements Runnable {

    private final Map<String, StaffBootstrap> bootstraps;

    @Override
    public void run() {
        log.info("Staff runner started!");
        bootstraps.forEach((k, v) -> v.load());
        log.info("Staff runner finished!");
    }

}
