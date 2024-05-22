package com.goorm.server.service;

import com.goorm.server.domain.Member;
import com.goorm.server.domain.Platform;
import com.goorm.server.dto.request.MemberSignUpRequest;
import com.goorm.server.dto.request.OAuthMemberSignUpRequest;
import com.goorm.server.dto.response.MemberSignUpResponse;
import com.goorm.server.dto.response.OAuthMemberSignUpResponse;
import com.goorm.server.exception.badrequest.DuplicateMemberException;
import com.goorm.server.exception.badrequest.InvalidPasswordException;
import com.goorm.server.exception.notfound.NotFoundMemberException;
import com.goorm.server.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class MemberService {

    private static final Pattern PASSWORD_REGEX = Pattern.compile("^(?=.*[a-z])(?=.*\\d)[a-z\\d]{8,20}$");

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberSignUpResponse signUp(MemberSignUpRequest request) {
        validatePassword(request.getPassword());
        validateDuplicateMember(request);

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        try {
            Member member = new Member(request.getEmail(), encodedPassword);
            return new MemberSignUpResponse(memberRepository.save(member).getId());
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateMemberException();
        }
    }

    private void validateDuplicateMember(MemberSignUpRequest memberSignUpRequest) {
        if (memberRepository.existsByEmailAndPlatform(memberSignUpRequest.getEmail(), Platform.GOORM)) {
            throw new DuplicateMemberException();
        }
    }

    private void validatePassword(String password) {
        if (!PASSWORD_REGEX.matcher(password).matches()) {
            throw new InvalidPasswordException();
        }
    }

    @Transactional
    public OAuthMemberSignUpResponse signUpByOAuthMember(OAuthMemberSignUpRequest request) {
        Platform platform = Platform.from(request.getPlatform());
        Member member = memberRepository.findByPlatformAndPlatformId(platform, request.getPlatformId())
                .orElseThrow(NotFoundMemberException::new);

        member.registerOAuthMember(request.getEmail());
        return new OAuthMemberSignUpResponse(member.getId());
    }
}
