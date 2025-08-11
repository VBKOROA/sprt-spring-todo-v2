package indiv.abko.todo.member.adapter.in.rest;

import indiv.abko.todo.global.dto.ApiResp;
import indiv.abko.todo.global.security.AuthUser;
import indiv.abko.todo.global.security.JwtAuthFilter;
import indiv.abko.todo.member.adapter.in.rest.dto.*;
import indiv.abko.todo.member.domain.Member;
import indiv.abko.todo.member.domain.port.in.*;
import indiv.abko.todo.member.domain.port.in.command.SignUpCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/members")
@Tag(name = "Member API", description = "회원 관련 API")
public class MemberController {
    private final ReadMemberInfoUseCase readMemberInfoUseCase;
    private final SignUpUseCase signUpUseCase;
    private final LoginUseCase loginUseCase;
    private final UpdateMyInfoUseCase updateMyInfoUseCase;
    private final DeleteMeUseCase deleteMeUseCase;

    @GetMapping("/{id}")
    @Operation(summary = "회원 단건 조회", description = "회원 ID로 회원을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 조회 성공"),
            @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ApiResp.class),
                            examples = @ExampleObject(value = "{\"status\":\"NOT_FOUND\",\"message\":\"해당 ID의 Member가 없습니다.\",\"data\":null}")))
    })
    public ApiResp<MemberResp> getMember(@Parameter(description = "회원 ID", example = "1") @PathVariable long id) {
        final Member member = readMemberInfoUseCase.execute(id);
        return ApiResp.ok(MemberResp.from(member));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "회원가입", description = "새로운 회원을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "파라미터가 유효하지 않음",
                    content = @Content(schema = @Schema(implementation = ApiResp.class),
                            examples = @ExampleObject(value = "{\"status\":\"BAD_REQUEST\",\"message\":\"요청이 잘못되었습니다.\",\"data\":null}"))),
            @ApiResponse(responseCode = "409", description = "이미 사용 중인 이메일",
                    content = @Content(schema = @Schema(implementation = ApiResp.class),
                            examples = @ExampleObject(value = "{\"status\":\"CONFLICT\",\"message\":\"이미 사용 중인 이메일입니다.\",\"data\":null}")))
    })
    public ApiResp<SignUpResp> signUp(@RequestBody @Valid SignUpReq signUpReq) {
        final SignUpCommand command = signUpReq.toCommand();
        final Member createdMember = signUpUseCase.execute(command);
        final var response = SignUpResp.from(createdMember);
        return ApiResp.created(response);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "이메일과 비밀번호로 로그인하여 JWT 토큰을 발급받습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공, 쿠키에 토큰 저장"),
            @ApiResponse(responseCode = "401", description = "로그인 실패",
                    content = @Content(schema = @Schema(implementation = ApiResp.class),
                            examples = @ExampleObject(value = "{\"status\":\"UNAUTHORIZED\",\"message\":\"로그인에 실패하였습니다.\",\"data\":null}")))
    })
    public ApiResp<String> login(@RequestBody @Valid LoginReq loginReq, HttpServletResponse response) {
        final String token = loginUseCase.execute(loginReq.email(), loginReq.password());
        final var apiResponse = ApiResp.ok("로그인 성공");
        final var cookie = new Cookie(JwtAuthFilter.AUTH_HEADER, token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return apiResponse;
    }

    @PatchMapping("/me")
    @Operation(summary = "내 정보 수정", description = "자신의 정보를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정보 수정 성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자",
                    content = @Content(schema = @Schema(implementation = ApiResp.class),
                            examples = @ExampleObject(value = "{\"status\":\"UNAUTHORIZED\",\"message\":\"인증이 필요합니다.\",\"data\":null}"))),
            @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ApiResp.class),
                            examples = @ExampleObject(value = "{\"status\":\"NOT_FOUND\",\"message\":\"해당 ID의 Member가 없습니다.\",\"data\":null}")))
    })
    public ApiResp<MemberResp> update(@RequestBody @Valid UpdateMemberReq updateReq,
                                      @AuthUser
                                      @Parameter(hidden = true) long requesterId) {
        final Member member = updateMyInfoUseCase.execute(requesterId, updateReq.name());
        final var responseMember = MemberResp.from(member);
        return ApiResp.ok(responseMember);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "회원 탈퇴", description = "자신의 계정을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "회원 탈퇴 성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자",
                    content = @Content(schema = @Schema(implementation = ApiResp.class),
                            examples = @ExampleObject(value = "{\"status\":\"UNAUTHORIZED\",\"message\":\"인증이 필요합니다.\",\"data\":null}"))),
            @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ApiResp.class),
                            examples = @ExampleObject(value = "{\"status\":\"NOT_FOUND\",\"message\":\"해당 ID의 Member가 없습니다.\",\"data\":null}")))
    })
    public void delete(@AuthUser
                       @Parameter(hidden = true) long requesterId) {
        deleteMeUseCase.execute(requesterId);
    }
}