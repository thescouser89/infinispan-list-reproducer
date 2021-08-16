import org.infinispan.commons.api.BasicCache;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationChildBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    public static String getUserHome() {
        String userHome = System.getProperty("user.home");
        return userHome;
    }

    public static void main(String[] args) {

        EmbeddedCacheManager cacheManager;
        GlobalConfigurationChildBuilder globalConfig = new GlobalConfigurationBuilder();

        String location = new File(getUserHome() + "/.infinispan-reproducer").getAbsolutePath();

        // Setting up embedded cache
        globalConfig.globalState()
                .persistentLocation(location)
                .serialization()
                .addContextInitializer(new SerializerImpl())
                .allowList()
                .addRegexp(".*")
                .create();

        GlobalConfiguration globalConfiguration = globalConfig.build();
        Long duration = TimeUnit.HOURS.toMillis(1L);
        Configuration configuration = new ConfigurationBuilder().expiration()
                .lifespan(duration)
                .maxIdle(duration)
                .wakeUpInterval(-1L)
                .persistence()
                .passivation(false)
                .addSingleFileStore()
                .segmented(true)
                .shared(false)
                .preload(true)
                .fetchPersistentState(true)
                .purgeOnStartup(false)
                .location(location)
                .build();

        cacheManager = new DefaultCacheManager(globalConfiguration);
        // defining the builds cache
        cacheManager.defineConfiguration("builds", configuration);


        if (args[0].equals("fail")) {
            // get the basic cache class with as values a list of LocalFile
            BasicCache<String, List<LocalFile>> cache = cacheManager.getCache("builds");

            // insert data
            if (args.length > 1 && args[1].equals("insert")) {
                List<LocalFile> test = new ArrayList<>();
                test.add(new LocalFile("haa", "hoo"));
                test.add(new LocalFile("noo", "need"));
                cache.put("test", test);
            }

            // read data from cache
            cache.get("test").forEach(a -> System.out.println(a.getA()));

        } else if (args[0].equals("success")) {
            // get the basic cache class with as values a ListWrapper object
            BasicCache<String, ListWrapper> cache = cacheManager.getCache("builds");

            // insert data
            if (args.length > 1 && args[1].equals("insert")) {
                List<LocalFile> test = new ArrayList<>();
                test.add(new LocalFile("haa", "hoo"));
                test.add(new LocalFile("noo", "need"));
                ListWrapper wrapper = new ListWrapper(test);
                cache.put("test", wrapper);
            }

            // read data from cache
            cache.get("test").getData().forEach(a -> System.out.println(a.getA()));
        }
    }
}
