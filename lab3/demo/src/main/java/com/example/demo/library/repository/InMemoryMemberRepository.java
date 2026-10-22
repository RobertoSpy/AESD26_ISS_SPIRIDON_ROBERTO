package com.example.demo.library.repository;

import com.example.demo.library.model.Member;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryMemberRepository implements MemberRepository {

    private final Map<String, Member> members = new ConcurrentHashMap<>();

    @Override
    public Member save(Member member) {
        members.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(String id) {
        return Optional.ofNullable(members.get(id));
    }

    @Override
    public Collection<Member> findAll() {
        return List.copyOf(members.values());
    }
}
