package org.zighinetto.springpoc.filter;

import lombok.Data;

@Data
public class SimpleComparableFilter<T extends Comparable<? super T>> extends SimpleFilter<T> {

    /**
     * Requires the property to be greater than or equal to the value
     */
    protected T gte;
    /**
     * Requires the property to be greater than the value
     */
    protected T gt;
    /**
     * Requires the property to be less than or equal to the value
     */
    protected T lte;
    /**
     * Requires the property to be less than the value
     */
    protected T lt;



}
