package indiv.abko.todo.member.adapter.in.rest;

import indiv.abko.todo.global.dto.ApiResp;
import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.global.exception.GlobalExceptionEnum;
import indiv.abko.todo.global.security.JwtAuthFilter;
import indiv.abko.todo.member.adapter.in.rest.dto.*;
import indiv.abko.todo.member.application.port.in.GetMemberUseCase;
import indiv.abko.todo.member.application.port.in.LoginUseCase;
import indiv.abko.todo.member.application.port.in.SignUpUseCase;
import indiv.abko.todo.member.application.port.in.UpdateMemberUseCase;
import indiv.abko.todo.member.domain.Member;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    private final GetMemberUseCase getMemberUseCase;
    private final SignUpUseCase signUpUseCase;
    private final LoginUseCase loginUseCase;
    private final UpdateMemberUseCase updateMemberUseCase;

    @GetMapping("/{id}")
    public ApiResp<MemberResp> getMember(@PathVariable long id) {
        var member = getMemberUseCase.get(id);
        return ApiResp.ok(MemberResp.of(member));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResp<SignUpResp> signUp(@RequestBody SignUpReq signUpReq) {
        var command = signUpReq.toCommand();
        var createdMember = signUpUseCase.signUp(command);
        var response = SignUpResp.from(createdMember);
        return ApiResp.created(response);
    }

    @PostMapping("/login")
    public ApiResp<String> login(@RequestBody LoginReq loginReq, HttpServletResponse response) {
        String token = loginUseCase.login(loginReq.email(), loginReq.password());
        var apiResponse = ApiResp.ok(token);
        Cookie cookie = new Cookie(JwtAuthFilter.AUTH_HEADER, token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return apiResponse;
    }

    @PostMapping("/{id}")
    public ApiResp<MemberResp> update(@RequestBody UpdateMemberReq updateReq,
                                      @RequestAttribute(name = "memberId", required = false)
                                      Long requesterId) {
        if(requesterId == null) {
            throw new BusinessException(GlobalExceptionEnum.UNAUTHORIZED);
        }
        Member member = updateMemberUseCase.update(requesterId, updateReq.name());
        var responseMember = MemberResp.of(member);
        return ApiResp.ok(responseMember);
    }
}
