package com.olehpodolin.springmvcwithsecurity.repositories;

import com.olehpodolin.springmvcwithsecurity.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
