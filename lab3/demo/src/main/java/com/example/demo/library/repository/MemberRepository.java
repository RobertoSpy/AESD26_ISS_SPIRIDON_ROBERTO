package com.example.demo.library.repository;

import com.example.demo.library.model.Member;
import java.util.Collection;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findById(String id);

    Collection<Member> findAll();
}
