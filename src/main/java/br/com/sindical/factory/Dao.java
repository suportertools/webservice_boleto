/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sindical.factory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author Claudemir Rtools
 */
public class Dao extends ConnectionDB{
    
    public void begin() {
        getEntityManager().getTransaction().begin();
    }

    public void commit() {
        getEntityManager().getTransaction().commit();
    }

    public void rollback() {
        getEntityManager().getTransaction().rollback();
    }

    public boolean remove(Object object) {
        try {
            getEntityManager().remove(object);
            getEntityManager().flush();
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    public boolean save(Object object) {
        try {
            getEntityManager().persist(object);
            getEntityManager().flush();
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    public boolean update(Object object) {
        try {
            getEntityManager().merge(object);
            getEntityManager().flush();
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }
    
    public Object find(Object object) {
        try {
            Class classe = object.getClass();
            Method metodo = classe.getMethod("getId", new Class[]{});
            Integer id = (Integer) metodo.invoke(object, (Object[]) null);
            return getEntityManager().find(object.getClass(), id);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.getMessage();
        }
        return null;
    }   
    
    public Object find(Object object, Object id) {
        try {
            return getEntityManager().find(object.getClass(), id);
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }    
}
