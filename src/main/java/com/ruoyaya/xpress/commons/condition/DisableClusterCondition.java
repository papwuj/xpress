package com.ruoyaya.xpress.commons.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Deprecated
public class DisableClusterCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return !"true".equals(context.getEnvironment().getProperty("opensite.cluster.enabled"));
    }
}
