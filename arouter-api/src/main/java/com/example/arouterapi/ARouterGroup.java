package com.example.arouterapi;

import com.example.annotations.RouterBean;

import java.util.Map;

public interface ARouterGroup {

    Map<String, Class<? extends RouterBean>> getPathMap();
}