/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.irfin.cafe.app.exception;

/**
 *
 * @author Hansen
 */
public class CafeAppException extends Exception {
    
    public CafeAppException() {
    }
    
    public CafeAppException(Throwable t) {
        super(t);
    }
    
    public CafeAppException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
