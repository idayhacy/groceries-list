package net.ycahyadi.xenon;

import com.vmware.xenon.common.ServiceHost;

public class HelloWorldHost extends ServiceHost {
    public static void main(String[] args) throws Throwable {
        HelloWorldHost h = new HelloWorldHost();
        h.initialize(args);
        h.start();
    }
    
    @Override
    public ServiceHost start() throws Throwable {
        super.start();
        super.startService(new HelloWorldService());
        return this;
    }
}
