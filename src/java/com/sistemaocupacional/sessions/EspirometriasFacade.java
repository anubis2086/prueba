/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemaocupacional.sessions;

import com.sistemaocupacional.entities.Espirometrias;
import com.sistemaocupacional.entities.Pacientes;
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
public class EspirometriasFacade extends AbstractFacade<Espirometrias> {
    @PersistenceContext(unitName = "Sistema_OcupacionalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EspirometriasFacade() {
        super(Espirometrias.class);
    }
    
     public List<Espirometrias> findByEmpresa(Pacientes selected) {
        Query q = getEntityManager().createNamedQuery("Espirometrias.findByIdPaciente");
        q.setParameter("idPaciente", selected);
        return q.getResultList();
    }    
    
}
