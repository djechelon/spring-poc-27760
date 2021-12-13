package org.zighinetto.springpoc.filter;

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

    public T getGte() {
        return gte;
    }

    public void setGte(T gte) {
        this.gte = gte;
    }

    public T getGt() {
        return gt;
    }

    public void setGt(T gt) {
        this.gt = gt;
    }

    public T getLte() {
        return lte;
    }

    public void setLte(T lte) {
        this.lte = lte;
    }

    public T getLt() {
        return lt;
    }

    public void setLt(T lt) {
        this.lt = lt;
    }

}
