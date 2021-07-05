package org.code.sample.hjxz;

import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractIndexAnalyzerProvider;

/**
 * @author hjxz
 * @date 2021/7/6
 * @title 4、实现AnalyzerProvider
 * @description
 */
public class CustomAnalyzerProvider extends AbstractIndexAnalyzerProvider<CustomAnalyzer> {

    private final CustomAnalyzer customAnalyzer;

    /**
     * 构建analyzer
     *
     * @param indexSettings 索引配置
     * @param name          分析器名称
     * @param settings      公共配置
     */
    @Inject
    public CustomAnalyzerProvider(IndexSettings indexSettings, Environment environment,@Assisted String name,@Assisted Settings settings) {
        super(indexSettings, name, settings);
        customAnalyzer = new CustomAnalyzer();
    }

    @Override
    public CustomAnalyzer get() {
        return this.customAnalyzer;
    }
}
