/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemaocupacional.sessions;

import com.sistemaocupacional.entities.Contratos;
import com.sistemaocupacional.entities.Pacientes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author D4MN4710N
 */
@Stateless
public class ContratosFacade extends AbstractFacade<Contratos> {
    @PersistenceContext(unitName = "Sistema_OcupacionalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratosFacade() {
        super(Contratos.class);
    }

    public Object finByNumeroContrato(String numCont) {
       Query q = getEntityManager().createNamedQuery("Contratos.findByNumCont");
        q.setParameter("numCont", numCont);
        try{
        return (Contratos) q.getSingleResult();
        }catch (NonUniqueResultException ex) {
            throw ex;
        }catch (NoResultException ex){
            return null;
        }
    }
    
}
