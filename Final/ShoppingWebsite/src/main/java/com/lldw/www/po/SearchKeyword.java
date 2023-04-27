package com.lldw.www.po;

/**
 * @author
 * @date
 */
public class SearchKeyword {
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "{" +
                "keyword='" + keyword + '\'' +
                '}';
    }
}
