package pl.michal.atiperatask.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.michal.atiperatask.model.Repo;
import pl.michal.atiperatask.model.dto.ReceivedRepoDto;
import pl.michal.atiperatask.webclient.GitHubWebClientService;

import java.util.List;

@Service
@AllArgsConstructor
public class RepoService {
    private GitHubWebClientService gitHubWebClientService;
    private RepoMapper repoMapper;

    public List<Repo> getNonForkRepos(String userName) {
        List<ReceivedRepoDto> repos = gitHubWebClientService.getRepos(userName);
        return repos.stream()
                .map(dto -> repoMapper.mapReceivedRepoDtoToRepo(dto))
                .filter(repo -> !repo.isFork())
                .toList();
    }

}
