package com.github.juanmougan.worldcup.services;

import com.github.juanmougan.worldcup.draft.Confederation;
import com.github.juanmougan.worldcup.draft.Group;
import com.github.juanmougan.worldcup.draft.Team;
import com.github.juanmougan.worldcup.repositories.TeamRepository;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService {

  private final TeamRepository teamRepository;

  private static Team getRandomElementFromList(final List<Team> teamsForRank) {
    final Random rand = new Random();
    return teamsForRank.get(rand.nextInt(teamsForRank.size()));
  }

  private Team chooseRandomTeam(final List<Team> candidateTeams) {
    final var chosenTeam = getRandomElementFromList(candidateTeams);
    teamRepository.delete(chosenTeam);
    return chosenTeam;
  }

  public Team getHomeTeam() {
    return teamRepository.popHomeTeam();
  }

  public Team getValidTeamForRank(final int rank) {
    final var teamsForRank = teamRepository.getTeamsByRank(rank);
    return chooseRandomTeam(teamsForRank);
  }

  public Team getValidTeamForRank(final int rank, final Group group) {
    final Set<Confederation> alreadyPresentConfederations = group.getTeams().stream()
        .map(Team::getConfederation)
        .collect(Collectors.toSet());
    final var teamsForRank = teamRepository.getTeamsByRankExcludingConfederations(
        rank, alreadyPresentConfederations);
    return chooseRandomTeam(teamsForRank);
  }
}
