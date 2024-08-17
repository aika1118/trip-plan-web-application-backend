package com.wskang.trip.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * User
 *
 * User 관련된 Entity 정의
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {

    // 기본키 정의
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    // Plan -> User | user_id : 외래키
    // CascadeType.ALL : User 엔티티에 대한 모든 변경이 Post 엔티티에 전파
    // orphanRemoval : User 엔티티에서 Post 엔티티를 제거할 때, 데이터베이스에서도 해당 Post 엔티티가 삭제
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Plan> plans;

    // FetchType.EAGER : 주 엔티티가 로드될 때 관련 엔티티도 함께 로드
    // CascadeType.ALL : 부모 엔티티에서 수행된 작업을 자식 엔티티에도 전파
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "Users_Roles",
        joinColumns = @JoinColumn(name = "user_id"), // 현재 엔터티를 기준으로 조인 테이블에서 사용되는 외래 키를 정의
        inverseJoinColumns = @JoinColumn(name = "role_id") // 연관된 엔터티를 기준으로 조인 테이블에서 사용되는 외래 키를 정의
    )
    private Set<Role> roles = new HashSet<>();
}

