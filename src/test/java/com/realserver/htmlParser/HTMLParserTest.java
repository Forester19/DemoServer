package com.realserver.htmlParser;

import com.htmlParser.CustomNode;
import com.htmlParser.SimpleHtmlParser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class HTMLParserTest {
    @Test
    public void testSearchByIdInMap(){
        Map<String, String> map = new HashMap<>();
        map.put("id", "aaa");
        map.put("ee", "agg");
        map.put("irr", "ggg");

        System.out.println(SimpleHtmlParser.searchCustomNodeById(map));
    }


    @Test
    public void handleHtmlTest(){
        ArrayList<CustomNode> listOfNodes = new ArrayList<CustomNode>(){{add(new CustomNode("div",new HashMap<String, String>(){{put("id","aaa");}}, false, true));
            add(new CustomNode("title",new HashMap<String, String>(){{put("id","bbb"); put("style", "ccc");}}, false, true));
            add(new CustomNode("body",new HashMap<String, String>(){{put("id","FFF");}}, false, true));}};

        SimpleHtmlParser.handleHtml(listOfNodes);


    }
}
