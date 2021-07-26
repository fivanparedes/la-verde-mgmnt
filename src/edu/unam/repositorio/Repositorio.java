package edu.unam.repositorio;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.SingularAttribute;

public class Repositorio {

    private final EntityManager em;

    public Repositorio(EntityManagerFactory emf) {
        this.em = emf.createEntityManager();
    }

    public void iniciarTransaccion() {
        em.getTransaction().begin();
    }

    public void confirmarTransaccion() {
        em.getTransaction().commit();
    }

    public void descartarTransaccion() {
        em.getTransaction().rollback();
    }

    public void insertar(Object o) {
        this.em.persist(o);
    }

    public void modificar(Object o) {
        this.em.merge(o);
    }

    public void eliminar(Object o) {
        this.em.remove(o);
    }

    public void refrescar(Object o) {
        this.em.refresh(o);
    }

    public <T extends Object> T buscar(Class<T> clase, Object id) {
        return (T) this.em.find(clase, id);
    }

    public <T extends Object> List<T> buscarTodos(Class<T> clase) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<T> consulta = cb.createQuery(clase);
        consulta.select(consulta.from(clase));
        return em.createQuery(consulta).getResultList();
    }

    public <T extends Object, P extends Object> List<T> buscarOrdenAsc(Class<T> clase, SingularAttribute<T, P> orden) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<T> consulta = cb.createQuery(clase);
        Root<T> origen = consulta.from(clase);
        consulta.select(origen).orderBy(cb.asc(origen.get(orden)));
        return em.createQuery(consulta).getResultList();
    }

    public <T extends Object, P extends Object> List<T> buscarOrdenDesc(Class<T> clase, SingularAttribute<T, P> orden) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<T> consulta = cb.createQuery(clase);
        Root<T> origen = consulta.from(clase);
        consulta.select(origen).orderBy(cb.desc(origen.get(orden)));
        return em.createQuery(consulta).getResultList();
    }
}