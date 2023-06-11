package pl.michal.atiperatask.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import pl.michal.atiperatask.model.Owner;

public record ReceivedRepoDto(@JsonAlias("name") String repoName,
                              Owner owner,
                              Boolean fork,
                              @JsonAlias("branches_url") String branchUrl) {
}
