/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemaocupacional.sessions;

import com.sistemaocupacional.entities.Laboratorio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author D4MN4710N
 */
@Stateless
public class LaboratorioFacade extends AbstractFacade<Laboratorio> {
    @PersistenceContext(unitName = "Sistema_OcupacionalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LaboratorioFacade() {
        super(Laboratorio.class);
    }
    
}
