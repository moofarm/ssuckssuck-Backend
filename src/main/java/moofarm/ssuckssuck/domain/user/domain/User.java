package moofarm.ssuckssuck.domain.user.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moofarm.ssuckssuck.domain.oauth.domain.OauthServerType;
import moofarm.ssuckssuck.domain.user.domain.vo.UserInfoVO;
import moofarm.ssuckssuck.global.common.MainCategory;
import moofarm.ssuckssuck.global.common.SubCategory;
import moofarm.ssuckssuck.global.database.BaseEntity;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String email;

    @Column(unique = true)
    private String nickname;

    @Enumerated(STRING)
    private OauthServerType oauthServerType;

    @Enumerated(STRING)
    private MainCategory mainCategory;

    @Enumerated(STRING)
    private SubCategory subCategory;

    @Builder
    public User(String name, String email, String nickname, OauthServerType oauthServerType, MainCategory mainCategory, SubCategory subCategory) {
        this.name = name;
        this.email = email;
        this.nickname = nickname;
        this.oauthServerType = oauthServerType;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }

    public static User createUser(String name, String email, String nickname, OauthServerType oauthServerType, MainCategory mainCategory, SubCategory subCategory) {
        return builder()
                .name(name)
                .email(email)
                .nickname(nickname)
                .oauthServerType(oauthServerType)
                .mainCategory(mainCategory)
                .subCategory(subCategory)
                .build();
    }

    public UserInfoVO getUserInfo() {
        return new UserInfoVO(
                id,
                name,
                email,
                nickname,
                oauthServerType,
                mainCategory,
                subCategory
        );
    }

    public void updateCategory(MainCategory mainCategory, SubCategory subCategory) {
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }
}
