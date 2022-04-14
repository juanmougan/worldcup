package com.github.juanmougan.worldcup.services;

import com.github.juanmougan.worldcup.draft.Group;
import com.github.juanmougan.worldcup.draft.Team;
import com.github.juanmougan.worldcup.services.TeamService;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService {

  private final TeamService teamService;

  public Group createHomeTeamGroup() {
    final Supplier<Team> homeTeamSupplier = teamService::getHomeTeam;
    return createGroup(0, homeTeamSupplier);
  }

  public Group createGroup(final int index) {
    final Supplier<Team> firstRankSupplier = () -> teamService.getValidTeamForRank(1);
    return createGroup(index, firstRankSupplier);
  }

  private Group createGroup(final int index, final Supplier<Team> teamSupplier) {
    final Team firstRankTeam = teamSupplier.get();
    final Set<Team> teams = new HashSet<>();
    teams.add(firstRankTeam);

    final Group partialGroup = Group.builder()
        .id(Group.resolveId(index))
        .teams(teams)
        .build();

    IntStream.rangeClosed(2, 4)
        .forEach(i -> partialGroup.addTeam(teamService.getValidTeamForRank(i, partialGroup)));
    return partialGroup;
  }
}
