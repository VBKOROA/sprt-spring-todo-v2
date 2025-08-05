package indiv.abko.todo.member.adapter.in.rest;

import indiv.abko.todo.global.dto.ApiResp;
import indiv.abko.todo.member.adapter.in.rest.dto.MemberResp;
import indiv.abko.todo.member.application.port.in.GetMemberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {
    private final GetMemberUseCase getMemberUseCase;

    @GetMapping("/{id}")
    public ApiResp<MemberResp> getMember(@PathVariable long id) {
        var member = getMemberUseCase.get(id);
        return ApiResp.ok(MemberResp.of(member));
    }
}
