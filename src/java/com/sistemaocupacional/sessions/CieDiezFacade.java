/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemaocupacional.sessions;

import com.sistemaocupacional.entities.CieDiez;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author D4MN4710N
 */
@Stateless
public class CieDiezFacade extends AbstractFacade<CieDiez> {
    @PersistenceContext(unitName = "Sistema_OcupacionalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CieDiezFacade() {
        super(CieDiez.class);
    }
    
    public List<CieDiez> findByDescripcion(String descripcion) {
        Query q = getEntityManager().createNamedQuery("CieDiez.findByDescripcion");
        q.setParameter("descripcion", descripcion + "%");
        q.setMaxResults(10);
        return q.getResultList();
    }

    
}
