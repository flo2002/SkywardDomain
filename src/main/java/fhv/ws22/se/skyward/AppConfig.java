package fhv.ws22.se.skyward;

import com.google.inject.AbstractModule;
import fhv.ws22.se.skyward.domain.DataService;
import fhv.ws22.se.skyward.domain.Session;
import fhv.ws22.se.skyward.domain.SessionService;
import fhv.ws22.se.skyward.persistence.DatabaseFacade;

public class AppConfig extends AbstractModule {
    @Override
    protected void configure() {
        bind(DataService.class).to(DatabaseFacade.class);
        bind(SessionService.class).to(Session.class);
    }
}
