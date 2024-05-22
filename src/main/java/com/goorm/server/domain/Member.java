package com.goorm.server.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email", "platform"})
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "platform")
    private Platform platform;

    @Column(name = "platform_id")
    private String platformId;

    public Member(String email, String password, Platform platform, String platformId) {
        this.email = email;
        this.password = password;
        this.platform = platform;
        this.platformId = platformId;
    }

    public Member(String email, String password) {
        this(email, password, Platform.GOORM, null);
    }

    public Member(String email, Platform platform, String platformId) {
        this.email = email;
        this.platform = platform;
        this.platformId = platformId;
    }

    public void registerOAuthMember(String email) {
        if (email != null) {
            this.email = email;
        }
    }
}
