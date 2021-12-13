package org.zighinetto.springpoc.filter;

public class StringFilter extends SimpleComparableFilter<String> {
    /**
     * Requires the property to begin with the value
     */
    protected String startsWith;
    /**
     * Requires the property to begin with the value. Case insensitive
     * <p>
     * If database collation is already case insensitive, these filters have no <b>additional</b> effect
     *
     * @see #startsWith
     */
    protected String startsWithIgnoreCase;
    /**
     * Requires the property to be equal to the value. Case insensitive
     * If database collation is already case insensitive, these filters have no <b>additional</b> effect
     *
     * @see #eq
     */
    protected String eqIgnoreCase;
    /**
     * Requires the property to end with the value
     */
    protected String endsWith;
    /**
     * Requires the property to end with the value. Case insensitive
     * If database collation is already case insensitive, these filters have no <b>additional</b> effect
     *
     * @see #endsWith
     */
    protected String endsWithIgnoreCase;
    /**
     * Requires the property to contain the value
     */
    protected String contains;
    /**
     * Requires the property to contain the value. Case insensitive
     * If database collation is already case insensitive, these filters have no <b>additional</b> effect
     *
     * @see #contains
     */
    protected String containsIgnoreCase;

    public String getStartsWith() {
        return startsWith;
    }

    public void setStartsWith(String startsWith) {
        this.startsWith = startsWith;
    }

    public String getStartsWithIgnoreCase() {
        return startsWithIgnoreCase;
    }

    public void setStartsWithIgnoreCase(String startsWithIgnoreCase) {
        this.startsWithIgnoreCase = startsWithIgnoreCase;
    }

    public String getEqIgnoreCase() {
        return eqIgnoreCase;
    }

    public void setEqIgnoreCase(String eqIgnoreCase) {
        this.eqIgnoreCase = eqIgnoreCase;
    }

    public String getEndsWith() {
        return endsWith;
    }

    public void setEndsWith(String endsWith) {
        this.endsWith = endsWith;
    }

    public String getEndsWithIgnoreCase() {
        return endsWithIgnoreCase;
    }

    public void setEndsWithIgnoreCase(String endsWithIgnoreCase) {
        this.endsWithIgnoreCase = endsWithIgnoreCase;
    }

    public String getContains() {
        return contains;
    }

    public void setContains(String contains) {
        this.contains = contains;
    }

    public String getContainsIgnoreCase() {
        return containsIgnoreCase;
    }

    public void setContainsIgnoreCase(String containsIgnoreCase) {
        this.containsIgnoreCase = containsIgnoreCase;
    }

}
