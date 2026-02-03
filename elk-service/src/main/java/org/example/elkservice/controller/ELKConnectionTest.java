package org.example.elkservice.controller;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.cluster.health.ClusterHealthStatus;

public class ELKConnectionTest {
    public static void main(String[] args) {
        RestHighLevelClient client = null;

        try {
            // 创建客户端连接
            client = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost("localhost", 9200, "http") // 修改为你的ELK地址
                    )
            );

            // 测试集群健康状态
            ClusterHealthRequest request = new ClusterHealthRequest();
            ClusterHealthResponse response = client.cluster().health(request, RequestOptions.DEFAULT);

            String clusterName = response.getClusterName();
            ClusterHealthStatus status = response.getStatus();
            int numberOfNodes = response.getNumberOfNodes();

            System.out.println("✅ 连接成功！");
            System.out.println("集群名称: " + clusterName);
            System.out.println("集群状态: " + status);
            System.out.println("节点数量: " + numberOfNodes);

        } catch (Exception e) {
            System.err.println("❌ 连接失败: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
