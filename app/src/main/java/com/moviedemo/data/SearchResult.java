package com.moviedemo.data;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moviedemo.protocol.DataArrayBase;

/**
 * Created by Administrator on 2015/9/2.
 * 不区分类型，搜索的结果列表包括：图书，电视剧，电影
 */
public class SearchResult extends DataArrayBase<SearchResultItem>{


    public SearchResult() {
        super();
    }

    public SearchResult(JSONArray jsonArray) {
        super(jsonArray);
    }


    @Override
    public SearchResultItem get(int id) {
        JSONObject jsonObject = this.getJsonItem(id);
        return new SearchResultItem(jsonObject);
    }

}
