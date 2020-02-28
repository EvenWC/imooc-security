package com.imooc.web.api;

import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author WangCheng
 * @version $ Id: ApiVersionRequestMappingHandlerMapping.java, v0.1 2020/2/28 21:20 WangCheng Exp $$
 */
public class ApiVersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {


    private static final String API_VERSION_PREFIX = "/api/v";

    private String getApiVersionPrefix = API_VERSION_PREFIX;

    private RequestMappingInfo.BuilderConfiguration config = new RequestMappingInfo.BuilderConfiguration();

    @Override
    public void afterPropertiesSet() {
        this.config = new RequestMappingInfo.BuilderConfiguration();
        this.config.setUrlPathHelper(super.getUrlPathHelper());
        this.config.setPathMatcher(super.getPathMatcher());
        this.config.setSuffixPatternMatch(super.useSuffixPatternMatch());
        this.config.setTrailingSlashMatch(super.useTrailingSlashMatch());
        this.config.setRegisteredSuffixPatternMatch(super.useRegisteredSuffixPatternMatch());
        this.config.setContentNegotiationManager(super.getContentNegotiationManager());
        super.afterPropertiesSet();
    }

    @Override
    protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
        RequestMappingInfo apiVersionMapping = rebuildMapping(mapping, method);
        super.registerHandlerMethod(handler, method, apiVersionMapping);
    }

    @Override
    public void registerMapping(RequestMappingInfo mapping, Object handler, Method method) {
        RequestMappingInfo apiVersionMapping = rebuildMapping(mapping, method);
        super.registerMapping(apiVersionMapping, handler, method);
    }


    /**
     * 重新构建 mapping
     *
     * @param mapping
     * @param method
     * @return
     */
    private RequestMappingInfo rebuildMapping(RequestMappingInfo mapping, Method method) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        if (Objects.nonNull(apiVersion)) {
            return rebuildMapping(mapping, apiVersion);
        }
        return mapping;
    }

    /**
     * @param mapping
     * @param apiVersion
     * @return
     */
    private RequestMappingInfo rebuildMapping(RequestMappingInfo mapping, ApiVersion apiVersion) {
        PatternsRequestCondition patternsRequestCondition = mapping.getPatternsCondition();
        String[] apiPaths = spellApiPaths(apiVersion, patternsRequestCondition);
        PatternsRequestCondition patternsCondition = new PatternsRequestCondition(
            apiPaths, this.config.getUrlPathHelper(), this.config.getPathMatcher(),
            this.config.useSuffixPatternMatch(), this.config.useTrailingSlashMatch(),
            this.config.getFileExtensions());

        return new RequestMappingInfo(mapping.getName(), patternsCondition,
            mapping.getMethodsCondition(),
            mapping.getParamsCondition(),
            mapping.getHeadersCondition(),
            mapping.getConsumesCondition(),
            mapping.getProducesCondition(),
            mapping.getCustomCondition());
    }

    /**
     * 拼接 api 路径
     *
     * @param apiVersion
     * @param patternsRequestCondition
     * @return
     */
    private String[] spellApiPaths(ApiVersion apiVersion, PatternsRequestCondition patternsRequestCondition) {
        Set<String> patterns = patternsRequestCondition.getPatterns();
        Set<String> nps = new LinkedHashSet<>();
        for (String pattern : patterns) {
            String np = getApiVersionPrefix + apiVersion.value() + pattern;
            nps.add(np);
        }
        String[] npArr = new String[nps.size()];
        nps.toArray(npArr);
        return npArr;
    }

    public void setGetApiVersionPrefix(String getApiVersionPrefix) {
        this.getApiVersionPrefix = getApiVersionPrefix;
    }
}
