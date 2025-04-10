package com.example.gateway.predicate;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class VipRoutePredicateFactory extends AbstractRoutePredicateFactory<VipRoutePredicateFactory.Config>  {

    public static final String PARAM_KEY = "param";
    public static final String VALUE_KEY = "value";

    public VipRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(PARAM_KEY,VALUE_KEY);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                //localhost/search?q=haha&user=leifengyang
                ServerHttpRequest request = serverWebExchange.getRequest();
                String first = request.getQueryParams().getFirst(config.param);
                return StringUtils.hasText(first) && first.equals(config.value);
            }
        };
    }

    @Validated
    public static class Config{

        @NotEmpty
        private String param;
        @NotEmpty
        private String value;

        public void setParam(@NotEmpty String param) {
            this.param = param;
        }

        public void setValue(@NotEmpty String value) {
            this.value = value;
        }

        public @NotEmpty String getParam() {
            return param;
        }

        public @NotEmpty String getValue() {
            return value;
        }
    }
}
