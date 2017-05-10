package net.ycahyadi.groceries.xenon;

import com.vmware.xenon.common.FactoryService;
import com.vmware.xenon.common.ServiceDocument;
import com.vmware.xenon.common.StatefulService;

public class ItemRequestService extends StatefulService {
    public static final String FACTORY_LINK = "/groceries/requests";

    public static FactoryService createFactory() {
        return FactoryService.createIdempotent(ItemRequestService.class);
    }
    
    public static class ItemRequestState extends ServiceDocument {
        public String requestingusername;
        public String storenickname;
        public String item;
        public String quantity;
        public String units;
    }

    public ItemRequestService() {
        super(ItemRequestState.class);
        super.toggleOption(ServiceOption.PERSISTENCE, true);
    }
}
