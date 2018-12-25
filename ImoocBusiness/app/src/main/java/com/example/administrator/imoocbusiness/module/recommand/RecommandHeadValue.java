package com.example.administrator.imoocbusiness.module.recommand;

import com.example.administrator.imoocbusiness.module.BaseModel;

import java.util.ArrayList;

/**
 * Created by ly on 2018/12/25.
 */
public class RecommandHeadValue extends BaseModel {
    public ArrayList<String> ads;
    public ArrayList<String> middle;
    public ArrayList<RecommandFooterValue> footer;
}
