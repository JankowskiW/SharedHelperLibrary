package com.wj.updatecenter.shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class RequestParametersExtractorTest {

    private Set<String> wantedParameterNames;
    private Set<String> unwantedParameterNames;
    private Map<String, Object> requestParameters;

    @BeforeEach
    void setUp() {
        wantedParameterNames = Set.of(
                "wantedParam1", "wantedParam2", "wantedParam3"
        );
        unwantedParameterNames = Set.of(
                "unwantedParam1", "unwantedParam2", "unwantedParam3"
        );
        requestParameters = Map.of(
                "wantedParam1", "wantedValue1",
                "unwantedParam1", "unwantedValue1",
                "wantedParam2", "wantedValue2",
                "wantedParam3", "wantedValue3",
                "unwantedParam2", "unwantedValue2"
        );
    }

    @Test
    void shouldReturnOnlyWantedParametersWhenAnyExistsInWantedParameterNamesCollection() {
        // given && when
        Map<String, Object> result = RequestParametersExtractor
                .extractWantedParameters(requestParameters, wantedParameterNames);

        // then
        assertAll(
                () -> assertThat(result.keySet())
                        .containsAll(wantedParameterNames),
                () -> assertThat(result.values())
                        .allMatch(value -> ((String) value).startsWith("wantedValue"))
        );
    }

    @Test
    void shouldReturnUnchangedMapWhenWantedParameterNamesCollectionIsNull() {
        // given && when
        Map<String, Object> result = RequestParametersExtractor
                .extractWantedParameters(requestParameters, null);

        // then
        assertThat(result.entrySet())
                .usingRecursiveFieldByFieldElementComparator()
                .containsAll(requestParameters.entrySet());
    }

    @Test
    void shouldReturnUnchangedMapWhenWantedParameterNamesCollectionIsEmpty() {
        // given && when
        Map<String, Object> result = RequestParametersExtractor
                .extractWantedParameters(requestParameters, new HashSet<>());

        // then
        assertThat(result.entrySet())
                .usingRecursiveFieldByFieldElementComparator()
                .containsAll(requestParameters.entrySet());
    }

    @Test
    void shouldReturnEmptyMapOfWantedParametersWhenGivenRequestParametersMapIsEmpty() {
        // given && when
        Map<String, Object> result = RequestParametersExtractor
                .extractWantedParameters(new HashMap<>(), wantedParameterNames);

        // then
        assertThat(result)
                .isEmpty();
    }

    @Test
    void shouldReturnEmptyMapOfWantedParametersWhenGivenRequestParametersMapIsNull() {
        // given && when
        Map<String, Object> result = RequestParametersExtractor
                .extractWantedParameters(null, wantedParameterNames);

        // then
        assertThat(result)
                .isEmpty();
    }

    @Test
    void shouldReturnOnlyParametersThatAreNotInUnwantedParameterNamesCollection() {
        // given && when
        Map<String, Object> result = RequestParametersExtractor
                .removeUnwantedParameters(requestParameters, unwantedParameterNames);

        // then
        assertAll(
                () -> assertThat(result.keySet())
                        .containsAll(wantedParameterNames),
                () -> assertThat(result.values())
                        .allMatch(value -> ((String) value).startsWith("wantedValue"))
        );
    }   @Test
    void shouldReturnUnchangedMapWhenUnwantedParameterNamesCollectionIsNull() {
        // given && when
        Map<String, Object> result = RequestParametersExtractor
                .removeUnwantedParameters(requestParameters, null);

        // then
        assertThat(result.entrySet())
                .usingRecursiveFieldByFieldElementComparator()
                .containsAll(requestParameters.entrySet());
    }

    @Test
    void shouldReturnUnchangedMapWhenUnwantedParameterNamesCollectionIsEmpty() {
        // given && when
        Map<String, Object> result = RequestParametersExtractor
                .removeUnwantedParameters(requestParameters, new HashSet<>());

        // then
        assertThat(result.entrySet())
                .usingRecursiveFieldByFieldElementComparator()
                .containsAll(requestParameters.entrySet());
    }

    @Test
    void shouldReturnEmptyMapOfParametersWithoutUnwantedOnesWhenGivenRequestParametersMapIsEmpty() {
        // given && when
        Map<String, Object> result = RequestParametersExtractor
                .removeUnwantedParameters(new HashMap<>(), wantedParameterNames);

        // then
        assertThat(result)
                .isEmpty();
    }

    @Test
    void shouldReturnEmptyMapOfParametersWithoutUnwantedOnesWhenGivenRequestParametersMapIsNull() {
        // given && when
        Map<String, Object> result = RequestParametersExtractor
                .removeUnwantedParameters(null, wantedParameterNames);

        // then
        assertThat(result)
                .isEmpty();
    }
}