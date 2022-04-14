package com.github.juanmougan.worldcup.repositories;

import static com.github.juanmougan.worldcup.draft.Confederation.CONCACAF;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.github.juanmougan.worldcup.draft.Team;
import com.github.juanmougan.worldcup.helpers.QualifiedTeams;
import java.util.List;
import java.util.Set;

public class TeamRepositoryHelper {

  public static TeamRepository mockTeamRepository() {
    final TeamRepository teamRepository = mock(TeamRepository.class);
    mockHomeTeam(teamRepository);
    mockTeamsByRank(teamRepository);
    mockDeleteTeam(teamRepository);
    mockGetTeamsByRankExcludingConfederations(teamRepository);
    return teamRepository;
  }

  private static void mockHomeTeam(final TeamRepository teamRepository) {
    when(teamRepository.popHomeTeam()).thenReturn(QualifiedTeams.QATAR.team);
  }

  private static void mockTeamsByRank(final TeamRepository teamRepository) {
    when(teamRepository.getTeamsByRank(1)).thenReturn(QualifiedTeams.getFirstRankTeams());
    when(teamRepository.getTeamsByRank(2)).thenReturn(QualifiedTeams.getSecondRankTeams());
    when(teamRepository.getTeamsByRank(3)).thenReturn(QualifiedTeams.getThirdRankTeams());
    when(teamRepository.getTeamsByRank(4)).thenReturn(QualifiedTeams.getFourthRankTeams());
  }

  private static void mockDeleteTeam(final TeamRepository teamRepository) {
    doAnswer(invocation -> {
      final var teamToDelete = (Team) invocation.getArgument(0);
      final int teamRank = teamToDelete.getRank();
      final var originalList = teamRepository.getTeamsByRank(teamRank);
      when(teamRepository.getTeamsByRank(teamRank)).thenReturn(
          getListWithoutElement(teamToDelete, originalList));
      return null;
    }).when(teamRepository).delete(any(Team.class));
  }

  private static List<Team> getListWithoutElement(final Team teamToDelete,
      final List<Team> newList) {
    return newList.stream().filter(
        not(t -> t.equals(teamToDelete))).collect(toList());
  }

  private static void mockGetTeamsByRankExcludingConfederations(
      final TeamRepository teamRepository) {
    // TODO generify this
    final var filteredList = QualifiedTeams.getThirdRankTeams().stream()
        .filter(not(t -> t.getConfederation().equals(CONCACAF)))
        .collect(toList());
    when(teamRepository.getTeamsByRankExcludingConfederations(3, Set.of(CONCACAF)))
        .thenReturn(filteredList);
  }
}
