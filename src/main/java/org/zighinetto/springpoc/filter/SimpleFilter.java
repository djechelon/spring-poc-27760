package org.zighinetto.springpoc.filter;

public class SimpleFilter<T> {

    /**
     * Requires the property to be equal to the value
     */
    protected T eq;
    /**
     * Requires the property to be different from the value
     */
    protected T ne;
    /**
     * Requires the property to be any of the supplied values
     */
    protected T[] in;
    /**
     * Requires the property not to be any of the supplied values
     */
    protected T[] notIn;

    /**
     * Requires the property to be either not null or null.
     * The property can be used as:
     * <p>
     * - Unspecified: null check is ignored
     * - True: the property must be null
     * - False: the property must be not null
     */
    protected Boolean isNull;

    public T getEq() {
        return eq;
    }

    public void setEq(T eq) {
        this.eq = eq;
    }

    public T getNe() {
        return ne;
    }

    public void setNe(T ne) {
        this.ne = ne;
    }

    public T[] getIn() {
        return in;
    }

    public void setIn(T[] in) {
        this.in = in;
    }

    public T[] getNotIn() {
        return notIn;
    }

    public void setNotIn(T[] notIn) {
        this.notIn = notIn;
    }

    public Boolean getIsNull() {
        return isNull;
    }

    public void setIsNull(Boolean aNull) {
        isNull = aNull;
    }
}
