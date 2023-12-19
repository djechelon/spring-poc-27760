package org.zighinetto.springpoc;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc(print = MockMvcPrint.LOG_DEBUG)
class FilterArgumentResolverWebTestPost {

    private final FilteringArgumentResolverTestController controller;

    private final MockMvc mockMvc;

    @AfterEach
    void tearDown() {
        controller.setFilter(null);
    }

    @Test
    @Disabled
    void resolveArgument_dateTime() {
        String propertyStringValue = "1986-06-02T15:55:24Z";
        OffsetDateTime dateTime = OffsetDateTime.parse(propertyStringValue);
        assertDoesNotThrow(() -> getMockMvc().perform(post("/exampleFiltering")
                        .content("{\"exampleOffsetDateTime\":{\"eq\":\"1986-06-02T15:55:24Z\"}}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
        );
        assertAll(
                () -> assertThat(controller.getFilter(), is(notNullValue())),
                () -> assertThat(controller.getFilter(), is(instanceOf(ExampleTestFilter.class))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleOffsetDateTime", is(notNullValue()))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleOffsetDateTime", hasProperty("eq", is(notNullValue())))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleOffsetDateTime", hasProperty("eq", comparesEqualTo(dateTime))))
        );

    }

    @Test
    void resolveArgument_string() {
        String value = "foo";
        assertDoesNotThrow(() -> getMockMvc().perform(post("/exampleFiltering")
                        .content("{\"exampleString\":{\"eq\":\"foo\"}}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
        );

        assertAll(
                () -> assertThat(controller.getFilter(), is(notNullValue())),
                () -> assertThat(controller.getFilter(), is(instanceOf(ExampleTestFilter.class))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleString", is(notNullValue()))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleString", hasProperty("eq", is(notNullValue())))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleString", hasProperty("eq", comparesEqualTo(value))))
        );
    }

    @Test
    void resolveArgument_stringArray() {
        String value1 = "foo";
        String value2 = "bar";
        assertDoesNotThrow(() -> getMockMvc().perform(post("/exampleFiltering")
                        .content("{\"exampleString\":{\"in\":[\"foo\",\"bar\"]}}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
        );

        assertAll(
                () -> assertThat(controller.getFilter(), is(notNullValue())),
                () -> assertThat(controller.getFilter(), is(instanceOf(ExampleTestFilter.class))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleString", is(notNullValue()))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleString", hasProperty("in", is(notNullValue())))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleString", hasProperty("in", arrayContaining(value1, value2))))
        );
    }

    @Test
    @Disabled
    void resolveArgument_integer() {
        String value1 = "256";

        assertDoesNotThrow(() -> getMockMvc().perform(post("/exampleFiltering")
                        .content("{\"exampleInteger\":{\"eq\":256}}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
        );


        assertAll(
                () -> assertThat(controller.getFilter(), is(notNullValue())),
                () -> assertThat(controller.getFilter(), is(instanceOf(ExampleTestFilter.class))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleInteger", is(notNullValue()))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleInteger", hasProperty("eq", is(notNullValue())))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleInteger", hasProperty("eq", equalTo(256))))
        );
    }

    @Test
    @Disabled
    void resolveArgument_integerArray() {
        String value1 = "256";
        String value2 = "-512";

        assertDoesNotThrow(() -> getMockMvc().perform(post("/exampleFiltering")
                        .content("{\"exampleInteger\":{\"in\":[256,-512]}}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
        );

        assertAll(
                () -> assertThat(controller.getFilter(), is(notNullValue())),
                () -> assertThat(controller.getFilter(), is(instanceOf(ExampleTestFilter.class))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleInteger", is(notNullValue()))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleInteger", hasProperty("in", is(notNullValue())))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleInteger", hasProperty("in", arrayContaining(256, -512))))
        );
    }

    @Test
    void resolveArgument_amlObjectTypeEnum_ok() {
        String value = "CONTROL";

        assertDoesNotThrow(() -> getMockMvc().perform(post("/exampleFiltering")
                        .content("{\"exampleObjectType\":{\"eq\":\"CONTROL\"}}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
        );

        assertAll(
                () -> assertThat(controller.getFilter(), is(notNullValue())),
                () -> assertThat(controller.getFilter(), is(instanceOf(ExampleTestFilter.class))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleObjectType", is(notNullValue()))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleObjectType", hasProperty("eq", equalTo(AmlObjectType.CONTROL))))
        );
    }


    @Test
    void resolveArgument_isNull() {
        assertDoesNotThrow(() -> getMockMvc().perform(post("/exampleFiltering")
                        .content("{\"exampleString\":{\"isNull\":true}}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
        );

        assertAll(
                () -> assertThat(controller.getFilter(), is(notNullValue())),
                () -> assertThat(controller.getFilter(), is(instanceOf(ExampleTestFilter.class))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleString", is(notNullValue()))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleString", hasProperty("isNull", is(notNullValue())))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleString", hasProperty("isNull", equalTo(true))))
        );
    }

    @Test
    void resolveArgument_isNotNull() {
        assertDoesNotThrow(() -> getMockMvc().perform(post("/exampleFiltering")
                        .content("{\"exampleString\":{\"isNull\":false}}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
        );
        assertAll(
                () -> assertThat(controller.getFilter(), is(notNullValue())),
                () -> assertThat(controller.getFilter(), is(instanceOf(ExampleTestFilter.class))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleString", is(notNullValue()))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleString", hasProperty("isNull", is(notNullValue())))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleString", hasProperty("isNull", equalTo(false))))
        );
    }


}