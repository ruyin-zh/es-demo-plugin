package org.code.sample.hjxz;

import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.index.analysis.AnalyzerProvider;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

import java.util.Collections;
import java.util.Map;

/**
 * @author hjxz
 * @date 2021/7/6
 * @title 5、实现分析器插件
 * @description 继承Plugin类并实现AnalysisPlugin接口,这是Plugin的一个扩展点
 */
public class CustomAnalyzerPlugin extends Plugin implements AnalysisPlugin {


    @Override
    public Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> getAnalyzers() {
        return Collections.singletonMap("akka_analyzer", CustomAnalyzerProvider::new);
    }
}
