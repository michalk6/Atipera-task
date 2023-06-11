package pl.michal.atiperatask.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.michal.atiperatask.model.Repo;
import pl.michal.atiperatask.model.dto.ReceivedRepoDto;
import pl.michal.atiperatask.webclient.GitHubWebClientService;

@Component
@AllArgsConstructor
public
class RepoMapper {
    private GitHubWebClientService gitHubWebClientService;

    public Repo mapReceivedRepoDtoToRepo(ReceivedRepoDto receivedRepoDto) {
        String branchUrl = receivedRepoDto.branchUrl();
        return Repo.builder()
                .repoName(receivedRepoDto.repoName())
                .owner(receivedRepoDto.owner())
                .isFork(receivedRepoDto.fork())
                .branches(gitHubWebClientService.getBranches(branchUrl))
                .build();
    }
}
