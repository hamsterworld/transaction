package com.example.demo.member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    @Transactional
    public void save(Member member) {
        log.info("member 저장");
        em.persist(member);
        try{
            throw new RuntimeException("킥킥");
        } catch (Exception e){
            log.info("에러잡기");
        }
    }

    @Transactional
    public void save2(Member member) throws RuntimeException{
        log.info("member 저장");
        em.persist(member);
//        try{
//        throw new RuntimeException("킥킥");
//        } catch (Exception e){
//            log.info("에러잡기");
//        }
    }


    public Optional<Member> find(String username) {
        return em.createQuery("select m from Member m where m.username=:username", Member.class)
                .setParameter("username", username)
                .getResultList().stream().findAny();
    }

}