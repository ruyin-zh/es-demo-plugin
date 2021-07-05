package org.code.sample.hjxz;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;

/**
 * @author hjxz
 * @date 2021/7/6
 * @title 1、实现TokenFilter
 * @description 该filter用于反转token
 */
public class CustomFilter extends TokenFilter {
    //获取目前正在处理的token的文本内容
    private final CharTermAttribute termAttribute = addAttribute(CharTermAttribute.class);

    protected CustomFilter(TokenStream input){
        super(input);
    }

    @Override
    public boolean incrementToken() throws IOException {
        //若token流中还有未处理的token,该方法将返回true,否则返回false
        if (input.incrementToken()){
            //获取词项文本
            char[] originalTerm = termAttribute.buffer();
            if (originalTerm.length > 0){
                //反转词项内容
                StringBuilder builder = new StringBuilder(new String(originalTerm).trim()).reverse();
                //清空缓冲区内容
                termAttribute.setEmpty();
                termAttribute.append(builder.toString());
            }
            return true;
        }
        return false;
    }
}
