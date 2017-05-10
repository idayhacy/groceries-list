package net.ycahyadi.groceries.xenon;

import com.vmware.xenon.common.FactoryService;
import com.vmware.xenon.common.Service;
import com.vmware.xenon.common.ServiceDocument;
import com.vmware.xenon.common.StatefulService;

public class UserService extends StatefulService {
    public static final String FACTORY_LINK = "/groceries/users";

    public static FactoryService createFactory() {
        // this creates a UserService factory that generates services whose self links
        // are based on the docs' nicknames
        return FactoryUtils.createFactory(UserService.class,
                (document) -> document.documentSelfLink = ((UserState) document).username);
    }

    public static class UserState extends ServiceDocument {
        public String username;
        public String firstname;
        public String lastname;
    }

    public UserService() {
        super(UserState.class);
        super.toggleOption(ServiceOption.PERSISTENCE, true);
    }
}
