package io.github.ucpwang.sample.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SampleService {

    public String getSampleMsg() {
        return "sample message!";
    }

    public List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("test1");
        list.add("test2");
        list.add("test3");
        return list;
    }
}
