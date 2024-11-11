package com.wj.updatecenter.shared;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestParametersExtractor {

    public static Map<String, String> extractWantedParameters(
            Map<String, String> requestParameters,
            Collection<String> wantedParameterNames) {
        if (requestParameters == null || requestParameters.isEmpty()) return new HashMap<>();
        if (wantedParameterNames == null || wantedParameterNames.isEmpty()) return requestParameters;
        return requestParameters.entrySet().stream()
                .filter(entry -> wantedParameterNames.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<String, String> removeUnwantedParameters(
            Map<String, String> requestParameters,
            Collection<String> unwantedParameterNames) {
        if (requestParameters == null || requestParameters.isEmpty()) return new HashMap<>();
        if (unwantedParameterNames == null || unwantedParameterNames.isEmpty()) return requestParameters;
        return requestParameters.entrySet().stream()
                .filter(entry -> !unwantedParameterNames.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
