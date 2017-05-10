package net.ycahyadi.groceries.xenon;

import java.util.logging.Logger;

import com.vmware.xenon.common.FactoryService;
import com.vmware.xenon.common.ServiceDocument;
import com.vmware.xenon.common.StatefulService;

public class StoreService extends StatefulService {
    private static final Logger logger = Logger.getLogger(StoreService.class.getName());

    public static final String FACTORY_LINK = "/groceries/stores";

    public static FactoryService createFactory() {
        // this creates a StoreService factory that generates services whose self links
        // are based on the docs' nicknames
        return FactoryUtils.createFactory(StoreService.class,
                (document) -> document.documentSelfLink = ((StoreState) document).nickname);
    }

    public static class StoreState extends ServiceDocument {
        public String nickname;
        public String actualname;
    }

    public StoreService() {
        super(StoreState.class);
        super.toggleOption(ServiceOption.PERSISTENCE, true);
    }
}
