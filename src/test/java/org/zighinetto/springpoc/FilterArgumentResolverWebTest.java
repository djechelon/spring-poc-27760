package org.zighinetto.springpoc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ComponentScan(basePackageClasses = FilterArgumentResolverWebTest.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc(print = MockMvcPrint.LOG_DEBUG)
class FilterArgumentResolverWebTest {

    private final FilteringArgumentResolverTestController controller;

    private final MockMvc mockMvc;

    @Autowired
    public FilterArgumentResolverWebTest(FilteringArgumentResolverTestController controller, MockMvc mockMvc) {
        this.controller = controller;
        this.mockMvc = mockMvc;
    }

    public FilteringArgumentResolverTestController getController() {
        return controller;
    }

    public MockMvc getMockMvc() {
        return mockMvc;
    }

    @AfterEach
    void tearDown() {
        controller.setFilter(null);
    }

    @Test
    void resolveArgument_dateTime() {
        String propertyStringValue = "1986-06-02T15:55:24Z";
        OffsetDateTime dateTime = OffsetDateTime.parse(propertyStringValue);
        assertDoesNotThrow(() -> getMockMvc().perform(get("/exampleFiltering")
                        .queryParam("exampleOffsetDateTime.eq", propertyStringValue))
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
        assertDoesNotThrow(() -> getMockMvc().perform(get("/exampleFiltering")
                        .queryParam("exampleString.eq", value))
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
        assertDoesNotThrow(() -> getMockMvc().perform(get("/exampleFiltering")
                        .queryParam("exampleString.in", value1, value2))
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
    void resolveArgument_integer() {
        String value1 = "256";

        assertDoesNotThrow(() -> getMockMvc().perform(get("/exampleFiltering")
                        .queryParam("exampleInteger.eq", value1))
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
    void resolveArgument_integerArray() {
        String value1 = "256";
        String value2 = "-512";

        assertDoesNotThrow(() -> getMockMvc().perform(get("/exampleFiltering")
                        .queryParam("exampleInteger.in", value1, value2))
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

        assertDoesNotThrow(() -> getMockMvc().perform(get("/exampleFiltering")
                        .queryParam("exampleObjectTypeEnum.eq", value))
                .andExpect(status().isNoContent())
        );

        assertAll(
                () -> assertThat(controller.getFilter(), is(notNullValue())),
                () -> assertThat(controller.getFilter(), is(instanceOf(ExampleTestFilter.class))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleObjectTypeEnum", is(notNullValue()))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleObjectTypeEnum", hasProperty("eq", equalTo(AmlObjectType.CONTROL))))
        );
    }

    @Test
    void resolveArgument_lookAtTheCast() {
        String value = "CONTROL";

        assertDoesNotThrow(() -> getMockMvc().perform(get("/exampleFiltering")
                        .queryParam("exampleObjectTypeSimple.eq", value))
                .andExpect(status().isNoContent())
        );
        AmlObjectType eq = controller.getFilter().getExampleObjectTypeSimple().getEq();
        assertNotNull(eq);
    }


    @Test
    void resolveArgument_amlObjectTypeSimple_ok() {
        String value = "CONTROL";

        assertDoesNotThrow(() -> getMockMvc().perform(get("/exampleFiltering")
                        .queryParam("exampleObjectTypeSimple.eq", value))
                .andExpect(status().isNoContent())
        );
        assertAll(
                () -> assertThat(controller.getFilter(), is(notNullValue())),
                () -> assertThat(controller.getFilter(), is(instanceOf(ExampleTestFilter.class))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleObjectTypeSimple", is(notNullValue()))),
                () -> assertThat(controller.getFilter(), hasProperty("exampleObjectTypeSimple", hasProperty("eq", equalTo(AmlObjectType.CONTROL))))
        );
    }

    @Test
    void resolveArgument_isNull() {
        assertDoesNotThrow(() -> getMockMvc().perform(get("/exampleFiltering")
                        .queryParam("exampleString.isNull", "true"))
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
        assertDoesNotThrow(() -> getMockMvc().perform(get("/exampleFiltering")
                        .queryParam("exampleString.isNull", "false"))
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