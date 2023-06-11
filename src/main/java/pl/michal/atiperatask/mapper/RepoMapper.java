package pl.michal.atiperatask.mapper;

import lombok.AllArgsConstructor;
import pl.michal.atiperatask.model.Repo;
import pl.michal.atiperatask.model.dto.ReceivedRepoDto;
import pl.michal.atiperatask.webclient.GitHubWebClientService;

@AllArgsConstructor
public
class RepoMapper {

    public static Repo mapReceivedRepoDtoToRepo(ReceivedRepoDto receivedRepoDto, GitHubWebClientService gitHubWebClientService) {
        String branchUrl = receivedRepoDto.branchUrl();
        return Repo.builder()
                .repoName(receivedRepoDto.repoName())
                .owner(receivedRepoDto.owner())
                .isFork(receivedRepoDto.fork())
                .branches(gitHubWebClientService.getBranches(branchUrl))
                .build();
    }
}
