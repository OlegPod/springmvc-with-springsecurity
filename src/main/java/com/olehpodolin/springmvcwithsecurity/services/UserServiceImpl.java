package com.olehpodolin.springmvcwithsecurity.services;

import com.olehpodolin.springmvcwithsecurity.domain.User;
import com.olehpodolin.springmvcwithsecurity.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class UserServiceImpl extends AbstractJpaDaoService implements UserService {

    private EncryptionService encryptionService;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Override
    public List<?> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getById(Long id) {
        EntityManager em = emf.createEntityManager();

        return em.find(User.class, id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        if (domainObject.getPassword() != null) {
            domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getEncryptedPassword()));
        }

        User savedUser = em.merge(domainObject);
        em.getTransaction().commit();

        return savedUser;
    }

    @Override
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(User.class, id));
        em.getTransaction().commit();
    }
}
