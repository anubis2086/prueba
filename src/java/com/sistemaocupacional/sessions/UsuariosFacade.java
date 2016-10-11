/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemaocupacional.sessions;

import com.sistemaocupacional.entities.Usuarios;
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
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "Sistema_OcupacionalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }

    public Usuarios findBylogin(String numeroDoc, String password, boolean estado) {
       
        Query q = getEntityManager().createNamedQuery("Usuarios.findByLogin");
        q.setParameter("numeroDoc", numeroDoc);
        q.setParameter("password", password);
        q.setParameter("estado", estado);
        try {
            return (Usuarios) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex) {
            return null;
        }
    }
    
     public Usuarios findByPassword(String password, Integer idUsuario) {

        Query q = getEntityManager().createNamedQuery("Usuarios.findByPassword");       
        q.setParameter("password", password);
        q.setParameter("idUsuario", idUsuario);
        try {
            return (Usuarios) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex) {
            return null;
        }
    }

}


