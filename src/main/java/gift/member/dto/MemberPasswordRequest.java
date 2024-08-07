package gift.member.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;

public record MemberPasswordRequest(
    @NotBlank
    String oldPassword,

    @NotBlank
    String password,

    @NotBlank
    String passwordConfirm
) {

    @AssertTrue(message = "비밀번호 확인이 일치하지 않습니다.")
    public boolean isNewPasswordMatching() {
        return password.equals(passwordConfirm);
    }
}
