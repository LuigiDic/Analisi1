package com.alibaba.json.bvt.parser;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import junit.framework.TestCase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class JSONArrayParseTestParameterized extends TestCase {

    /* public void test_array() throws Exception {
        String text = "[{id:123}]";
        List<Map<String, Integer>> array = JSON.parseObject(text, new TypeReference<List<Map<String, Integer>>>() {});
        Assert.assertEquals(1, array.size());
        Map<String, Integer> map  = array.get(0);
        Assert.assertEquals(123, map.get("id").intValue());
    }*/


    private String text;
    private TypeReference<List<Map<String, Integer>>> typeReference;

    public JSONArrayParseTestParameterized(String inputString, TypeReference<List<Map<String, Integer>>> typeReference) {
        configure(inputString,typeReference);
    }

    private void configure(String inputString,  TypeReference<List<Map<String, Integer>>> typeReference) {
       this.text = inputString;
       this.typeReference = typeReference;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(new Object[][] {
                {"[{id:123}, {}]", new TypeReference<List<Map<String, Integer>>>() {}}
                // inputString		//TypeReference
        });
    }

    @Test
    public void test_array() {
        List<Map<String, Integer>> array = JSON.parseObject(text, typeReference);

        String[] tokens = text.split("}");
        String[] tokens2= tokens[0].split(":");

        Assert.assertEquals( tokens.length - 1, array.size());
        Map<String, Integer> map  = array.get(0);
        Assert.assertEquals(Integer.parseInt(tokens2[tokens2.length - 1]), map.get("id").intValue());
    }
}
