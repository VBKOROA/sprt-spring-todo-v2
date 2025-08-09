package indiv.abko.todo.member.adapter.in.rest;

import indiv.abko.todo.global.dto.ApiResp;
import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.global.exception.GlobalExceptionEnum;
import indiv.abko.todo.global.security.JwtAuthFilter;
import indiv.abko.todo.member.adapter.in.rest.dto.*;
import indiv.abko.todo.member.domain.Member;
import indiv.abko.todo.member.domain.port.in.*;
import indiv.abko.todo.member.domain.port.in.command.SignUpCommand;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    private final ReadMemberInfoUseCase readMemberInfoUseCase;
    private final SignUpUseCase signUpUseCase;
    private final LoginUseCase loginUseCase;
    private final UpdateMyInfoUseCase updateMyInfoUseCase;
    private final DeleteMeUseCase deleteMeUseCase;

    @GetMapping("/{id}")
    public ApiResp<MemberResp> getMember(@PathVariable long id) {
        final Member member = readMemberInfoUseCase.execute(id);
        return ApiResp.ok(MemberResp.from(member));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResp<SignUpResp> signUp(@RequestBody SignUpReq signUpReq) {
        final SignUpCommand command = signUpReq.toCommand();
        final Member createdMember = signUpUseCase.execute(command);
        final var response = SignUpResp.from(createdMember);
        return ApiResp.created(response);
    }

    @PostMapping("/login")
    public ApiResp<String> login(@RequestBody LoginReq loginReq, HttpServletResponse response) {
        final String token = loginUseCase.execute(loginReq.email(), loginReq.password());
        final var apiResponse = ApiResp.ok(token);
        final var cookie = new Cookie(JwtAuthFilter.AUTH_HEADER, token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return apiResponse;
    }

    @PatchMapping("/{id}")
    public ApiResp<MemberResp> update(@RequestBody UpdateMemberReq updateReq,
                                      @RequestAttribute(name = "memberId", required = false)
                                      Long requesterId) {
        if(requesterId == null) {
            throw new BusinessException(GlobalExceptionEnum.UNAUTHORIZED);
        }
        final Member member = updateMyInfoUseCase.execute(requesterId, updateReq.name());
        final var responseMember = MemberResp.from(member);
        return ApiResp.ok(responseMember);
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestAttribute(name = "memberId", required = false)
                                      Long requesterId) {
        if(requesterId == null) {
            throw new BusinessException(GlobalExceptionEnum.UNAUTHORIZED);
        }
        deleteMeUseCase.execute(requesterId);
    }
}
