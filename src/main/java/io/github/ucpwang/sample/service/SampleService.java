package io.github.ucpwang.sample.service;

import org.springframework.stereotype.Service;

@Service
public class SampleService {

    private String sampleMsg = "sample message!";

    public String getSampleMsg() {
        return this.sampleMsg;
    }
}
