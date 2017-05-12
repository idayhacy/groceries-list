package net.ycahyadi.groceries.xenon;

import com.vmware.xenon.common.Operation;
import com.vmware.xenon.common.ServiceDocument;
import com.vmware.xenon.common.StatelessService;
import com.vmware.xenon.services.common.QueryTask;
import com.vmware.xenon.services.common.ServiceUriPaths;
import net.ycahyadi.groceries.xenon.ItemRequestService.ItemRequestState;

/**
 * note: xenon's GatewayService a good example code for URI_NAMESPACE_OWNER
 */
public class GroceriesListService extends StatelessService {

    public static final String SELF_LINK = "/groceries/list/";

    public GroceriesListService() {
        super();
        // allows this service to own the SELF_LINK URI and handle all calls to such URI prefixes
        super.toggleOption(ServiceOption.URI_NAMESPACE_OWNER, true);
    }

    @Override
    public void handleGet(Operation op) {
        String path = op.getUri().toString();
        
        if (!path.contains(SELF_LINK)) {
            op.fail(new IllegalArgumentException("this misrouting shouldn't happen"));
        }
        
        String subpath = path.substring(path.lastIndexOf(SELF_LINK) + SELF_LINK.length());
        
        // it is expected the segments will be /stores/:storeid or /users/:userid
        String[] subpathSegments = subpath.split("/");
        
        String qualifier = subpathSegments[0];
        String identifier = subpathSegments[1];
        
        // assert subpath is 2-piece
        // assert subpath[0] is stores, or users
        switch (qualifier) {
        case UserService.USERS_SUFFIX :
            queryByQualifier(op, ItemRequestState.class, "requestingusername", identifier);
            break;
        case StoreService.STORES_SUFFIX :
            queryByQualifier(op, ItemRequestState.class, "storenickname", identifier);
            break;
        default:
            op.fail(new IllegalStateException("something went wrong"));
        }
        
        // do not do op.complete() here, since we want the call to be synchronous
    }
    
    private void queryByQualifier(Operation parentOp, Class<? extends ServiceDocument> documentType, String field, String fieldValue) {
        QueryTask.Query query = QueryTask.Query.Builder.create()
                .addKindFieldClause(documentType)
                .addFieldClause(field, fieldValue)
                .build();

        QueryTask queryTask = QueryTask.Builder.createDirectTask()
                .addOption(QueryTask.QuerySpecification.QueryOption.EXPAND_CONTENT)
                // .setResultLimit(QueryTaskClientHelper.DEFAULT_QUERY_RESULT_LIMIT) // THIS WILL PAGINATE RESULT, AND the expanded content will not appear in the first page
                .setQuery(query)
                .build();
        
        Operation.createPost(this, ServiceUriPaths.CORE_QUERY_TASKS)
        .setBody(queryTask)
        .setCompletion((o, e) -> { 
            // when this operation returns, parse the response body back as a QueryTask
            QueryTask rsp = o.getBody(QueryTask.class);
            // which will contain, among other things, the expanded document contents (because we used QueryOption.EXPAND_CONTENT)
            parentOp.setBody(rsp.results.documents);
            // then complete the call so the op returns to client
            parentOp.complete();
        })
        .sendWith(this);
    }
    
}
