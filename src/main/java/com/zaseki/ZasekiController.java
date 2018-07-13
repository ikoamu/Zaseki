package com.zaseki;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZasekiController {
  @RequestMapping("/{furigana}")
  public ArrayList<Member> index(@PathVariable String furigana) {
    ArrayList<Member> memberList = makeMemberList();
    ArrayList<Member> qualifiedMember = new ArrayList<Member>();

    for (Member member : memberList) {
      if (member.getFurigana().equals(furigana)) {
        qualifiedMember.add(member);
      }
    }

    return qualifiedMember;
  }

  @RequestMapping("/{furigana}/{keyWord}")
  public ArrayList<Member> index(@PathVariable String furigana, @PathVariable String keyWord) {
    ArrayList<Member> memberList = makeMemberList();
    ArrayList<Member> qualifiedMember = new ArrayList<Member>();

    for (Member member : memberList) {
      if (member.getFurigana().equals(furigana) && member.getDivision().equals(Division.from(keyWord).toString())) {
        qualifiedMember.add(member);
      }
    }

    return qualifiedMember;
  }

  private ArrayList<Member> makeMemberList() {
    ArrayList<Member> memberList = new ArrayList<Member>();

    memberList.add(new Member(0, "青木A", "あおき", "ITS", "1F東", "1111"));
    memberList.add(new Member(1, "青木B", "あおき", "ETEC", "2F南", "1111"));
    memberList.add(new Member(2, "青木C", "あおき", "ITS", "3F西", "2222"));
    memberList.add(new Member(3, "青木D", "あおき", "ETEC", "4F北", "2222"));
    memberList.add(new Member(4, "三浦A", "みうら", "ITS", "5F東", "3333"));
    memberList.add(new Member(5, "三浦B", "みうら", "金融", "6F西", "4444"));
    memberList.add(new Member(6, "三浦C", "みうら", "ITS", "7北", "5555"));
    memberList.add(new Member(7, "三浦D", "みうら", "金融", "8F東", "6666"));

    return memberList;
  }
}
