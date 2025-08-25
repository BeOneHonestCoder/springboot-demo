package com.net.interview;


import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;

public class HttpMain {

    public static void main(String[] args) {
        int page = 1;
        boolean hasMore = true;
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();
        Gson gson = new Gson();

        while (hasMore) {
            String encodedName = URLEncoder.encode("Zhang San", StandardCharsets.UTF_8);
            System.out.println(encodedName);
            String url = String.format("https://jsonmock.hackerrank.com/api/v1?name=%s&page=%d", encodedName, page);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(10))
                    .GET()
                    .build();

//            String jsonBody = gson.toJson(user);
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(URI.create(url))
//                    .timeout(Duration.ofSeconds(10))
//                    .header("Content-Type", "application/json")
//                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
//                    .build();

            try {
                HttpResponse<String> response = client.send(request,
                        HttpResponse.BodyHandlers.ofString());

                ApiResponse apiResponse = gson.fromJson(response.body(), ApiResponse.class);
                apiResponse.setTotalPages(2);

//                ObjectMapper mapper = new ObjectMapper();
//                String jsonString = mapper.writeValueAsString(user);
//                String jsonArray = mapper.writeValueAsString(users);
//                List<User> users = mapper.readValue(jsonArray, new TypeReference<List<User>>() {});
//                User user = mapper.readValue(jsonString, User.class);

//                Gson gson = new Gson();
//                String jsonString = gson.toJson(user);
//                String jsonArray = gson.toJson(users);
//                List<User> users = gson.fromJson(jsonArray, new TypeToken<List<User>>() {});
//                User parsedUser = gson.fromJson(jsonString, User.class);

                hasMore = page++ < apiResponse.getTotalPages();

            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private static class ApiResponse {
        private int page;
        private int totalPages;
        private List<Object> data;

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public List<Object> getData() {
            return data;
        }

        public void setData(List<Object> data) {
            this.data = data;
        }
    }
}
