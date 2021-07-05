package org.code.sample.hjxz;

import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;

/**
 * @author hjxz
 * @date 2021/7/6
 * @title 2、实现TokenFilter工厂
 * @description 词项过滤器工厂的实现
 */
public class CustomFilterFactory extends AbstractTokenFilterFactory {

    /**
     *
     * @param indexSettings 封装了所有索引级别的配置
     * @param name
     * @param settings
     * */
    public CustomFilterFactory(IndexSettings indexSettings, @Assisted String name, @Assisted Settings settings){
        super(indexSettings, name, settings);
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        return new CustomFilter(tokenStream);
    }
}
