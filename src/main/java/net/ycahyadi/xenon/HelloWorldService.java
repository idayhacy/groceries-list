package net.ycahyadi.xenon;

import com.vmware.xenon.common.Operation;
import com.vmware.xenon.common.StatelessService;

public class HelloWorldService extends StatelessService {
    public static final String SELF_LINK = "helloworld";

    /* (non-Javadoc)
     * @see com.vmware.xenon.common.StatelessService#handleGet(com.vmware.xenon.common.Operation)
     */
    @Override
    public void handleGet(Operation get) {
        // TODO Auto-generated method stub
        get.setBody("Hello World!");
        super.handleGet(get);
    }
}
