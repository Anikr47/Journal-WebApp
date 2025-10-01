package com.anish.journalApp.cache;

import com.anish.journalApp.entity.ConfigJournalApp;
import com.anish.journalApp.repository.ConfigJournalAppRepo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }
    @Autowired
    ConfigJournalAppRepo configJournalAppRepo;
    public Map<String, String> appCache;
     @PostConstruct
    public void init(){
         appCache = new HashMap<>();
         List<ConfigJournalApp> all = configJournalAppRepo.findAll();
         for(ConfigJournalApp configJournalApp : all ){
             appCache.put(configJournalApp.getKey(), configJournalApp.getValue());
         }
    }

}
