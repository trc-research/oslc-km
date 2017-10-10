
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reusecompany.oslc.km.common.exceptions;

/**
 *
 * This class models an exception occured during the access to a resource.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
