package com.example.hotfix.note.class10.myjson;

import com.zero.serializabledemo.myjson.parser.Parser;
import com.zero.serializabledemo.myjson.tokenizer.CharReader;
import com.zero.serializabledemo.myjson.tokenizer.TokenList;
import com.zero.serializabledemo.myjson.tokenizer.Tokenizer;

import java.io.IOException;
import java.io.StringReader;


public class JSONParser {

    private Tokenizer tokenizer = new Tokenizer();//词法分析

    private Parser parser = new Parser();//语法解析

    public Object fromJSON(String json) throws IOException {
        CharReader charReader = new CharReader(new StringReader(json));
        TokenList tokens = tokenizer.tokenize(charReader);
        return parser.parse(tokens);
    }
}
