package org.example;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class ElasticsearchIndexer {

    private static final String ELASTICSEARCH_HOST = "localhost";
    private static final int ELASTICSEARCH_PORT = 9200;
    private static final String INDEX_NAME = "https://example.com/";

    public static void main(String[] args) {
        try (RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(ELASTICSEARCH_HOST, ELASTICSEARCH_PORT, "http")))) {

            // Specify the directory containing your downloaded data
            String dataDirectory = "https://example.com/";

            // Index data into Elasticsearch
            indexData(client, dataDirectory);

            System.out.println("Data indexing completed.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void indexData(RestHighLevelClient client, String dataDirectory) throws IOException {
        // Iterate through files in the data directory
        Files.list(Paths.get(dataDirectory))
                .filter(Files::isRegularFile)
                .forEach(filePath -> {
                    try {
                        // Read data from the file
                        List<Map<String, Object>> webpageData = readDataFromFile(filePath.toString());

                        // Index data into Elasticsearch
                        indexToElasticsearch(client, webpageData);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    private static List<Map<String, Object>> readDataFromFile(String filePath) throws IOException {
        // Implement your logic to read data from JSON file and return a list of maps
        // Adjust this method based on your data format
        // Example: use Jackson ObjectMapper to read JSON
        // ObjectMapper objectMapper = new ObjectMapper();
        // return objectMapper.readValue(new File(filePath), new TypeReference<>() {});
        return null;
    }

    private static void indexToElasticsearch(RestHighLevelClient client, List<Map<String, Object>> data) throws IOException {
        // Index each document
        for (int docId = 0; docId < data.size(); docId++) {
            Map<String, Object> document = data.get(docId);

            // Create an index request
            IndexRequest indexRequest = new IndexRequest(INDEX_NAME)
                    .id(Integer.toString(docId + 1)) // Use a unique identifier for each document
                    .source(document);

            // Execute the index request
            client.index(indexRequest, RequestOptions.DEFAULT);
        }
    }
}
