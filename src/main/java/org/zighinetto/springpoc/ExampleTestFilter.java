package org.zighinetto.springpoc;

import org.zighinetto.springpoc.filter.*;

public class ExampleTestFilter {

    private OffsetDateTimeFilter exampleOffsetDateTime;

    private LocalDateFilter exampleLocalDate;

    private StringFilter exampleString;

    private IntegerFilter exampleInteger;

    private AmlObjectTypeFilter exampleObjectTypeEnum;

    private SimpleFilter<AmlObjectType> exampleObjectTypeSimple;

    public OffsetDateTimeFilter getExampleOffsetDateTime() {
        return exampleOffsetDateTime;
    }

    public void setExampleOffsetDateTime(OffsetDateTimeFilter exampleOffsetDateTime) {
        this.exampleOffsetDateTime = exampleOffsetDateTime;
    }

    public LocalDateFilter getExampleLocalDate() {
        return exampleLocalDate;
    }

    public void setExampleLocalDate(LocalDateFilter exampleLocalDate) {
        this.exampleLocalDate = exampleLocalDate;
    }

    public StringFilter getExampleString() {
        return exampleString;
    }

    public void setExampleString(StringFilter exampleString) {
        this.exampleString = exampleString;
    }

    public IntegerFilter getExampleInteger() {
        return exampleInteger;
    }

    public void setExampleInteger(IntegerFilter exampleInteger) {
        this.exampleInteger = exampleInteger;
    }

    public AmlObjectTypeFilter getExampleObjectTypeEnum() {
        return exampleObjectTypeEnum;
    }

    public void setExampleObjectTypeEnum(AmlObjectTypeFilter exampleObjectTypeEnum) {
        this.exampleObjectTypeEnum = exampleObjectTypeEnum;
    }

    public SimpleFilter<AmlObjectType> getExampleObjectTypeSimple() {
        return exampleObjectTypeSimple;
    }

    public void setExampleObjectTypeSimple(SimpleFilter<AmlObjectType> exampleObjectTypeSimple) {
        this.exampleObjectTypeSimple = exampleObjectTypeSimple;
    }

    public static class AmlObjectTypeFilter extends SimpleFilter<AmlObjectType> {

    }
}
