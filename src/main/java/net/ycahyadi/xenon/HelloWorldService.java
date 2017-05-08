/*
 * Copyright (c) 2016 VMware, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, without warranties or
 * conditions of any kind, EITHER EXPRESS OR IMPLIED.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

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
