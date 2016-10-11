/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemaocupacional.sessions;

import com.sistemaocupacional.entities.Empresas;
import com.sistemaocupacional.entities.Pacientes;
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
public class PacientesFacade extends AbstractFacade<Pacientes> {
    @PersistenceContext(unitName = "Sistema_OcupacionalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PacientesFacade() {
        super(Pacientes.class);
    }
    
    public List<Pacientes> findByEmpresa(Empresas selected) {
        Query q = getEntityManager().createNamedQuery("Pacientes.findByIdClem");
        q.setParameter("idClem", selected);
        return q.getResultList();
    }    
    
    public List<Pacientes> findByNumeroDoc(String numeroDocumento) {
        Query q = getEntityManager().createNamedQuery("Pacientes.findByNumDocumento");
        q.setParameter("numDoc", numeroDocumento);
        return q.getResultList();
    }    

     public Pacientes finByNumeroDocumento(String numDoc) {
        Query q = getEntityManager().createNamedQuery("Pacientes.findByNumDoc");
        q.setParameter("numDoc", numDoc);
        try{
        return (Pacientes) q.getSingleResult();
        }catch (NonUniqueResultException ex) {
            throw ex;
        }catch (NoResultException ex){
            return null;
        }
    }
}

    

