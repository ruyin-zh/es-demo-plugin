package org.code.sample.hjxz;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;

/**
 * @author hjxz
 * @date 2021/7/6
 * @title 3、实现自定义分析器类
 * @description
 */
public class CustomAnalyzer extends Analyzer {

    public CustomAnalyzer(){}

    /**
     *
     * 创建分析器类型组件
     * @param fieldName 字段名称
     * */
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        Tokenizer tokenizer = new WhitespaceTokenizer();
        return new TokenStreamComponents(tokenizer,new CustomFilter(tokenizer));
    }
}
