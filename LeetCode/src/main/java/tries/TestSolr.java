package tries;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.ArrayList;

public class TestSolr {

    public static void main(String[] args) {
        // Solr 服务器的地址，替换成你自己的 Solr 地址
        String solrUrl = "http://localhost:8983/solr/CDataCore";

        // 创建 SolrClient 对象
        SolrClient solr = new HttpSolrClient.Builder(solrUrl).build();

        // 构建 Solr 查询
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*"); // 查询所有文档，可以替换成你的具体查询条件
        query.setRows(10); // 设置返回结果的行数

        try {
            // 执行查询
            QueryResponse response = solr.query(query);

            // 获取查询结果
            SolrDocumentList results = response.getResults();

            // 处理查询结果
            for (SolrDocument document : results) {
                // 可以根据需要获取字段值
                String id = (String) document.getFieldValue("id");
                String title = "";

//                if (document.getFieldValue("title").getClass() == String.class)
                title = (String) document.getFieldValue("sname");

//                else

                // 在这里处理获取到的字段值
                System.out.println("ID: " + id + ", Title: " + title);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭 SolrClient 连接
            try {
                solr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 创建文档
    private static SolrInputDocument createDocument() {
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "1");
        document.addField("title", "Java项目中使用Solr");
        document.addField("content", "Solr是一个强大的全文搜索平台");
        return document;
    }



}
