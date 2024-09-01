package com.ruoyaya.xpress.commons.log.annotation;

import com.ruoyaya.xpress.commons.log.annotation.enums.LogLevel;
import com.ruoyaya.xpress.commons.log.annotation.enums.LogOperatorType;
import com.ruoyaya.xpress.commons.log.annotation.enums.LogTarget;

import java.lang.annotation.*;

/**
 * 日志注解
 * @author WuJing
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 日志内容
     */
    String content();

    /**
     * 功能模块名
     */
    String module();

    /**
     * 操作类型
     */
    LogOperatorType operType() default LogOperatorType.SELECT;

    /**
     * 日志级别
     */
    LogLevel level() default LogLevel.INFO;

    /**
     * 日志记录到哪
     */
    LogTarget target() default LogTarget.ALL;


}
