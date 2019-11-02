package com.olehpodolin.springmvcwithsecurity.services;

import com.olehpodolin.springmvcwithsecurity.domain.Order;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class OrderServiceImpl extends AbstractJpaDaoService implements OrderService {

    @Override
    public List<Order> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Order", Order.class).getResultList();
    }

    @Override
    public Order getById(Long id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Order.class, id);
    }

    @Override
    public Order saveOrUpdate(Order domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Order savedProduct = em.merge(domainObject);
        em.getTransaction().commit();

        return savedProduct;
    }

    @Override
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Order.class, id));
        em.getTransaction().commit();
    }
}
