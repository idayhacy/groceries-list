package net.ycahyadi.groceries.xenon;

import com.vmware.xenon.common.ServiceHost;

public class GroceriesListHost extends ServiceHost {
    public static void main(String[] args) throws Throwable {
        GroceriesListHost h = new GroceriesListHost();
        h.initialize(args);
        h.start();
    }
    
    @Override
    public ServiceHost start() throws Throwable {
        super.start();
        startDefaultCoreServicesSynchronously();
        super.startFactory(UserService.class, UserService::createFactory);
        super.startFactory(StoreService.class, StoreService::createFactory);
        super.startFactory(ItemRequestService.class, ItemRequestService::createFactory);
        super.startService(new GroceriesListService());
        return this;
    }
}
