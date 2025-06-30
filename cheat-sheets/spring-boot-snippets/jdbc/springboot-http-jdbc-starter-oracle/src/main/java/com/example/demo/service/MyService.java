
package com.example.demo.service;

import com.example.demo.repository.MyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MyService
{
    private final MyRepository myRepository;
    private final RestTemplate restTemplate;

    public MyService(MyRepository myRepository) {
        this.myRepository = myRepository;
        this.restTemplate = new RestTemplate();
    }

    public String callExternalApi() {
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        return restTemplate.getForObject(url, String.class);
    }

    public String getUsers() {
        return myRepository.readUsers();
    }

    public String updateUser() {
        myRepository.updateUser();
        return "Updated user OK";
    }
}
