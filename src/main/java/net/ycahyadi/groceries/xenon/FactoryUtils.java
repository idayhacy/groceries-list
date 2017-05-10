package net.ycahyadi.groceries.xenon;

import java.util.function.Consumer;
import java.util.logging.Logger;

import com.vmware.xenon.common.FactoryService;
import com.vmware.xenon.common.Operation;
import com.vmware.xenon.common.Service;
import com.vmware.xenon.common.ServiceDocument;
import com.vmware.xenon.common.Utils;

public class FactoryUtils {
    private static final Logger logger = Logger.getLogger(FactoryUtils.class.getName());

    // create factories that will produce services with custom URIs
    public static <T extends ServiceDocument, U extends Service> FactoryService createFactory(
            final Class<U> serviceType, final Consumer<T> consumer) {
        try {
            Service s = serviceType.newInstance();
            final Class<T> serviceDocumentType = (Class<T>) s.getStateType();
            FactoryService factoryService = new FactoryService(serviceDocumentType) {
                @Override
                public Service createServiceInstance() throws Throwable {
                    return serviceType.newInstance();
                }

                @Override
                public void handlePost(Operation post) {
                    // on post to the factory...
                    T postBody = post.getBody(serviceDocumentType);
                    
                    if (consumer != null) {
                        // allow the Consumer to do additional processing 
                        // given postBody information
                        consumer.accept(postBody);
                    }
                    
                    post.setBody(postBody).complete();
                }
            };
            return factoryService;
        } catch (Throwable e) {
            logger.severe(String.format("Failure creating factory for %s: %s", serviceType,
                    Utils.toString(e)));
            return null;
        }
    }

}
