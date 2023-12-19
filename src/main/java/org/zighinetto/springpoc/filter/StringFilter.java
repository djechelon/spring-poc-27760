package org.zighinetto.springpoc.filter;

import lombok.Data;

@Data
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


}
