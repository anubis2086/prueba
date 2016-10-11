/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemaocupacional.sessions;

import com.sistemaocupacional.entities.CuentaCobro;
import com.sistemaocupacional.entities.Pacientes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author santi
 */
@Stateless
public class CuentaCobroFacade extends AbstractFacade<CuentaCobro> {
    @PersistenceContext(unitName = "Sistema_OcupacionalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentaCobroFacade() {
        super(CuentaCobro.class);
    }

   
    
       public CuentaCobro finByNumeroCuenta(String numeroCuenta) {
        Query q = getEntityManager().createNamedQuery("CuentaCobro.findByNumeroCuenta");
        q.setParameter("numeroCuenta", numeroCuenta);
        try{
        return (CuentaCobro) q.getSingleResult();
        }catch (NonUniqueResultException ex) {
            throw ex;
        }catch (NoResultException ex){
            return null;
        }
    }
    
}