package org.zighinetto.springpoc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exampleFiltering")
public class FilteringArgumentResolverTestController {

    private ExampleTestFilter filter;

    public ExampleTestFilter getFilter() {
        return filter;
    }

    public void setFilter(ExampleTestFilter filter) {
        this.filter = filter;
    }

    /**
     * Sample no-op function
     */
    @GetMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleExampleTestFilter(@ModelAttribute("filter") ExampleTestFilter filter) {
        this.filter = filter;
    }
}
