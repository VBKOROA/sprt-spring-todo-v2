package indiv.abko.todo.member.application.port.in;

import indiv.abko.todo.member.application.port.in.dto.GetAuthorDto;

public interface GetAuthorUseCase {
    GetAuthorDto getAuthor(long id);
}
