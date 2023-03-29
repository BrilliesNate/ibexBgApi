/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myPower24.ibexBgApi.ejb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author natha
 */
public class DaoAbstractFacade<T> {

    private Class<T> entityClass;

    @PersistenceContext(unitName = "ibex_PU")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void create(T entity) {
        em.persist(entity);
    }

    public T createAndFlush(T entity) {
        em.persist(entity);
        em.flush();
        return entity;
    }

    public T edit(T entity) {
        return em.merge(entity);
    }

    public void remove(T entity) {
        em.remove(em.merge(entity));
    }

    public T find(Object id) {
        return em.find(entityClass, id);
    }

}
