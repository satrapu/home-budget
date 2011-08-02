/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.satrapu.homebudget.services.persistence;

import java.io.Serializable;

/**
 * 
 * @author satrapu
 * @param <T>
 */
public interface EntityPage<T extends Serializable>
        extends Iterable<T> {

    public long getTotalCount();

    public int getCount();

    public PageDetails getDetails();
}
