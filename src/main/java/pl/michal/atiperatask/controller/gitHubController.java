package pl.michal.atiperatask.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.michal.atiperatask.mapper.RepoService;
import pl.michal.atiperatask.model.Repo;

import java.util.List;

@RestController
@AllArgsConstructor
class gitHubController {

    private RepoService repoService;

    @GetMapping("/getRepos")
    public ResponseEntity<List<Repo>> getRepos(@RequestParam String userName) {
        List<Repo> nonForkRepos = repoService.getNonForkRepos(userName);
        return ResponseEntity.ok(nonForkRepos);
    }
}
