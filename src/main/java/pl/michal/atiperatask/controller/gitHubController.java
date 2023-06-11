package pl.michal.atiperatask.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.michal.atiperatask.exception.UserNotFoundException;
import pl.michal.atiperatask.mapper.RepoService;
import pl.michal.atiperatask.model.Error;
import pl.michal.atiperatask.model.Repo;

import java.util.List;

@RestController
@AllArgsConstructor
class GitHubController {

    private RepoService repoService;

    @GetMapping("/repos")
    public ResponseEntity<?> repos(@RequestParam String userName) {
        List<Repo> nonForkRepos;
        try {
            nonForkRepos = repoService.getNonForkRepos(userName);
        } catch (UserNotFoundException e) {
            Error error = new Error(404, e.getMessage());
            return ResponseEntity.status(404).body(error);
        }
        return ResponseEntity.ok(nonForkRepos);
    }
}
