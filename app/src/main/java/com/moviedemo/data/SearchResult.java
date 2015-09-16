package com.moviedemo.data;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moviedemo.protocol.DataArrayBase;

/**
 * Created by Administrator on 2015/9/2.
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
