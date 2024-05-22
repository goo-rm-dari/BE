package com.goorm.server.service;

import com.goorm.server.domain.Member;
import com.goorm.server.domain.Platform;
import com.goorm.server.dto.request.AuthLoginRequest;
import com.goorm.server.dto.request.KakaoLoginRequest;
import com.goorm.server.dto.response.OAuthTokenResponse;
import com.goorm.server.dto.response.TokenResponse;
import com.goorm.server.exception.badrequest.PasswordMismatchException;
import com.goorm.server.exception.notfound.NotFoundMemberException;
import com.goorm.server.repository.MemberRepository;
import com.goorm.server.security.auth.JwtTokenProvider;
import com.goorm.server.security.auth.OAuthPlatformMemberResponse;
import com.goorm.server.security.auth.kakao.KakaoOAuthUserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final KakaoOAuthUserProvider kakaoOAuthUserProvider;

    public TokenResponse login(AuthLoginRequest request) {
        Member findMember = memberRepository.findByEmailAndPlatform(request.getEmail(), Platform.GOORM)
                .orElseThrow(NotFoundMemberException::new);
        validatePassword(findMember, request.getPassword());

        String token = issueToken(findMember);
        return TokenResponse.from(findMember.getId(), token);
    }

    private String issueToken(final Member findMember) {
        return jwtTokenProvider.createToken(findMember.getId());
    }

    private void validatePassword(final Member findMember, final String password) {
        if (!passwordEncoder.matches(password, findMember.getPassword())) {
            throw new PasswordMismatchException();
        }
    }

    public OAuthTokenResponse kakaoOAuthLogin(KakaoLoginRequest request) {
        OAuthPlatformMemberResponse kakaoPlatformMember =
                kakaoOAuthUserProvider.getKakaoPlatformMember(request.getToken());
        return generateOAuthTokenResponse(
                Platform.KAKAO,
                kakaoPlatformMember.getEmail(),
                kakaoPlatformMember.getPlatformId()
        );
    }

    private OAuthTokenResponse generateOAuthTokenResponse(Platform platform, String email, String platformId) {
        return memberRepository.findIdByPlatformAndPlatformId(platform, platformId)
                .map(memberId -> {
                    Member findMember = memberRepository.findById(memberId)
                            .orElseThrow(NotFoundMemberException::new);
                    String token = issueToken(findMember);
                    return new OAuthTokenResponse(memberId, token, findMember.getEmail(), true, platformId);
                })
                .orElseGet(() -> {
                    Member oauthMember = new Member(email, platform, platformId);
                    Member savedMember = memberRepository.save(oauthMember);
                    String token = issueToken(savedMember);
                    return new OAuthTokenResponse(savedMember.getId(), token, email, false, platformId);
                });
    }
}

