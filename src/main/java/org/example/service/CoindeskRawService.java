package org.example.service;

import org.example.dto.coindesk.CoindeskRawDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CoindeskRawService {

    private static final String COINDESK_URL = "https://kengp3.github.io/blog/coindesk.json";

    @Autowired
    private RestTemplate restTemplate;

    @Cacheable("coindeskRaw")
    public CoindeskRawDto getRawData() {
        return restTemplate.getForObject(COINDESK_URL, CoindeskRawDto.class);
    }
}
