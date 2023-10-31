package com.workintech.bankauth.service;

import com.workintech.bankauth.dao.MemberDao;
import com.workintech.bankauth.dao.RoleDao;
import com.workintech.bankauth.entity.Member;
import com.workintech.bankauth.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {

    private MemberDao memberDao;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(MemberDao memberDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.memberDao = memberDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    public Member register(String email, String password) {
        Optional<Member> foundMember = memberDao.findByEmail(email);
        if (foundMember.isPresent()) {
            throw new RuntimeException("User with given email already exist, please login: " + email);
        }

        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleDao.findByAuthority("USER").get();
        //Role adminRole = roleDao.findByAuthority("ADMIN").get();

        List<Role> roleList = new ArrayList<>();
        roleList.add(userRole);
        //roleList.add(adminRole);

        Member member = new Member();
        member.setEmail(email);
        member.setPassword(encodedPassword);
        member.setAuthorities(roleList);
        return memberDao.save(member);
    }
}
