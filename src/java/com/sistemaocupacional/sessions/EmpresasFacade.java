/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemaocupacional.sessions;

import com.sistemaocupacional.entities.Empresas;

import java.util.List;
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
public class EmpresasFacade extends AbstractFacade<Empresas> {
    @PersistenceContext(unitName = "Sistema_OcupacionalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpresasFacade() {
        super(Empresas.class);
    }
    
        public List<Empresas> findByNombreEmpresa(String nomClem) {
        Query q = getEntityManager().createNamedQuery("Empresas.findByNomClem");
        q.setParameter("nomClem", nomClem + "%");
        q.setMaxResults(10);
        return q.getResultList();
    }

      public Empresas finByNumeroNit(String nitClem) {
        Query q = getEntityManager().createNamedQuery("Empresas.findByNitClem");
        q.setParameter("nitClem", nitClem);
        try{
        return (Empresas) q.getSingleResult();
        }catch (NonUniqueResultException ex) {
            throw ex;
        }catch (NoResultException ex){
            return null;
        }
    }
    
}
