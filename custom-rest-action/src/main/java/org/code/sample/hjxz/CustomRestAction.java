package org.code.sample.hjxz;

import org.elasticsearch.action.admin.cluster.node.info.NodeInfo;
import org.elasticsearch.action.admin.cluster.node.info.NodesInfoResponse;
import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.rest.BaseRestHandler;
import org.elasticsearch.rest.BytesRestResponse;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.RestStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hjxz
 * @date 2021/7/5
 * @title
 * @description
 */
public class CustomRestAction extends BaseRestHandler {

    @Inject
    public CustomRestAction(Settings settings, RestController controller){
        super();
        controller.registerHandler(RestRequest.Method.GET,"/_akka/nodes",this);
    }


    @Override
    public String getName() {
        return CustomRestAction.class.getSimpleName();
    }

    @Override
    protected RestChannelConsumer prepareRequest(RestRequest restRequest, NodeClient nodeClient) throws IOException {
        String prefix = restRequest.param("prefix","");
        return channel -> {
            XContentBuilder builder = channel.newBuilder();
            builder.startObject();
            List<String> nodes = new ArrayList<>();
            NodesInfoResponse response = nodeClient.admin().cluster().prepareNodesInfo().setThreadPool(true).get();
            for (NodeInfo nodeInfo : response.getNodes()){
                String nodeName = nodeInfo.getNode().getName();
                if (prefix.isEmpty()){
                    nodes.add(nodeName);
                }else if (nodeName.startsWith(prefix)){
                    nodes.add(nodeName);
                }
            }
            builder.field("nodes",nodes);
            builder.endObject();

            channel.sendResponse(new BytesRestResponse(RestStatus.OK,builder));
        };
    }
}
