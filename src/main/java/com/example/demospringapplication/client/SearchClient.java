package com.example.demospringapplication.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "search-client", url = "10.177.68.40:8983")
public interface SearchClient {

    @RequestMapping(method = RequestMethod.GET, path = "/solr/productCollection/select")
    Map<String, Object> getProducts(@RequestParam("q") String query);

}
