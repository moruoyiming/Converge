package com.example.arouterapi;

import com.example.annotations.RouterBean;

import java.util.Map;

public interface ARouterPath {

    Map<String, RouterBean> getPathMap();
}