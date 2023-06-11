package pl.michal.atiperatask.model;

import lombok.Builder;

import java.util.List;

@Builder
public record Repo(String repoName,
                   Owner owner,
                   boolean isFork,
                   List<Branch> branches) {
}
