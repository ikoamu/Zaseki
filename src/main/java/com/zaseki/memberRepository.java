package com.zaseki;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface memberRepository extends JpaRepository<Member, Integer> {

}
