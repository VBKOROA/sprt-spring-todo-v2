package indiv.abko.todo.member.adapter.in.rest;

import indiv.abko.todo.global.dto.ApiResp;
import indiv.abko.todo.member.adapter.in.rest.dto.MemberResp;
import indiv.abko.todo.member.adapter.in.rest.dto.SignUpMemberReq;
import indiv.abko.todo.member.adapter.in.rest.dto.SignUpMemberResp;
import indiv.abko.todo.member.application.port.in.GetMemberUseCase;
import indiv.abko.todo.member.application.port.in.SignUpMemberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {
    private final GetMemberUseCase getMemberUseCase;
    private final SignUpMemberUseCase signUpMemberUseCase;

    @GetMapping("/{id}")
    public ApiResp<MemberResp> getMember(@PathVariable long id) {
        var member = getMemberUseCase.get(id);
        return ApiResp.ok(MemberResp.of(member));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResp<SignUpMemberResp> signUp(@RequestBody SignUpMemberReq signUpMemberReq) {
        var command = signUpMemberReq.toCommand();
        var createdMember = signUpMemberUseCase.signUp(command);
        var response = SignUpMemberResp.from(createdMember);
        return ApiResp.created(response);
    }
}
