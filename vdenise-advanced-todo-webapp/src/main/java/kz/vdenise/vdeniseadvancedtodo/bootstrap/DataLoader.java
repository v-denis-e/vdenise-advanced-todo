package kz.vdenise.vdeniseadvancedtodo.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Profile({"default", "local"})
@Slf4j
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final Map<String, Runnable> runners;

    @Override
    public void run(String... args) {
        log.info("Running runners ...");

        runners.entrySet().stream().filter(entry -> entry.getKey().startsWith("bootstrap"))
                .map(Map.Entry::getValue)
                .forEach(Runnable::run);

        log.info("Runners were ran!");
    }
}
