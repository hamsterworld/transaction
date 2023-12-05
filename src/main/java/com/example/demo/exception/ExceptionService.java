package com.example.demo.exception;

import com.example.demo.member.Member;
import com.example.demo.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExceptionService {

    private final MemberRepository memberRepository;

    @Transactional
    public void save() throws Exception {
        Member member = new Member();
        member.setUsername("hamster");
        memberRepository.save(member);

//        throw new Exception("킥킥");
    }

    @Transactional
    public void notSave() {
        Member member = new Member();
        member.setUsername("hamster");
//        memberRepository.save(member);
        try{
            memberRepository.save(member);
        } catch (Exception e){
            log.info("에러잡기");
        }
        memberRepository.save2(member);
//        try{
//            throw new RuntimeException("킥킥");
//        } catch (Exception e){
//            log.info("에러잡기");
//        }
    }

    @Transactional(readOnly = true)
    public void findUser(){
        Optional<Member> hamster = memberRepository.find("hamster");
        if(hamster.isEmpty()){
            log.info("비여있네요");
        } else {
            log.info("{}",hamster);
        }

    }

}
