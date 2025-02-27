package com.fdm.pmsuibackend.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdm.pmsuibackend.model.Account;
import com.fdm.pmsuibackend.model.User;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    List<Account> findByOwner(User curUser);

}
