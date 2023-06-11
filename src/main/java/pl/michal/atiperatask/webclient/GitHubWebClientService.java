package pl.michal.atiperatask.webclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import pl.michal.atiperatask.exception.UserNotFoundException;
import pl.michal.atiperatask.model.Branch;
import pl.michal.atiperatask.model.dto.ReceivedRepoDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GitHubWebClientService {
    private WebClient gitHubWebClient;
    private ObjectMapper objectMapper;


    public List<ReceivedRepoDto> getRepos(String userName) {
        String uri = String.format("/users/%s/repos", userName);
        String repoJson;
        try {
            repoJson = gitHubWebClient
                    .method(HttpMethod.GET)
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientResponseException e) {
            throw new UserNotFoundException("Cannot found user with given name");
        }
        List<ReceivedRepoDto> repos;
        try {
            repos = objectMapper.readValue(repoJson, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return repos.stream()
                .filter(repo -> !repo.fork())
                .collect(Collectors.toList());
    }

    public List<Branch> getBranches(String branchUrl) {
        String substring = branchUrl.substring(22, branchUrl.length()-9);
        String branchJson = gitHubWebClient
                .method(HttpMethod.GET)
                .uri(substring)
                .retrieve()
                .bodyToMono(String.class)
                .doOnError(throwable -> System.out.println("error"))
                .block();
        List<Branch> branches;
        try {
            branches = objectMapper.readValue(branchJson, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return branches;
    }
}
