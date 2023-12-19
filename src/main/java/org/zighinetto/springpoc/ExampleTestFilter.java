package org.zighinetto.springpoc;

import lombok.Data;
import org.zighinetto.springpoc.filter.*;

@Data
public class ExampleTestFilter {

    private StringFilter exampleString;


    private SimpleFilter<AmlObjectType> exampleObjectType;

}
