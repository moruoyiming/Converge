package com.example.hotfix.note.class10.myjson.tokenizer;

import java.util.ArrayList;
import java.util.List;


public class TokenList {

    private List<Token> tokens = new ArrayList<Token>();

    private int pos = 0;

    public void add(Token token) {
        tokens.add(token);
    }

    public Token peek() {
        return pos < tokens.size() ? tokens.get(pos) : null;
    }

    public Token peekPrevious() {
        return pos - 1 < 0 ? null : tokens.get(pos - 2);
    }

    public Token next() {
        return tokens.get(pos++);
    }

    public boolean hasMore() {
        return pos < tokens.size();
    }

    @Override
    public String toString() {
        return "TokenList{" +
                "tokens=" + tokens +
                '}';
    }
}
