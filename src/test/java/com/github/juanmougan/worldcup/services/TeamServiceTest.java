package com.github.juanmougan.worldcup.services;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.juanmougan.worldcup.draft.Group;
import com.github.juanmougan.worldcup.draft.Team;
import com.github.juanmougan.worldcup.helpers.QualifiedTeams;
import com.github.juanmougan.worldcup.repositories.TeamRepositoryHelper;
import java.util.Set;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TeamServiceTest {

  private TeamService teamService;

  @BeforeEach
  void setUp() {
    teamService = new TeamService(TeamRepositoryHelper.mockTeamRepository());
  }

  @Test
  void givenHomeTeamExists_whenGetHomeTeam_thenReturnIt() {
    final var homeTeam = teamService.getHomeTeam();
    assertThat(homeTeam).isEqualTo(QualifiedTeams.QATAR.team);
  }

  @Test
  void givenFirstRankTeams_whenGetValidTeamForRankOne_thenReturnValidTeamThatIsNotHomeTeam() {
    final var validTeamForRankOne = teamService.getValidTeamForRank(1);
    assertThat(validTeamForRankOne).extracting(Team::getRank).isEqualTo(1);
    assertThat(validTeamForRankOne).extracting(Team::isHomeTeam).isEqualTo(false);
  }

  @Test
  void givenSecondRankTeams_whenGetValidTeamEightTimes_thenGetEightDifferentTeams() {
    final var eightValidTeams = IntStream.rangeClosed(1, 8)
        .mapToObj(i -> teamService.getValidTeamForRank(2))
        .collect(toSet());
    assertThat(eightValidTeams.size()).isEqualTo(8);
  }

  @Test
  void givenThirdRankTeams_whenGetValidTeamForRankAndMexicoIsPresent_thenDoNotGetCanada() {
    final var partialGroup = Group.builder()
        .teams(Set.of(QualifiedTeams.MEXICO.team))
        .build();
    final var sevenValidTeams = IntStream.rangeClosed(1, 7)
        .mapToObj(i -> teamService.getValidTeamForRank(3, partialGroup))
        .collect(toSet());
    assertThat(sevenValidTeams).doesNotContain(QualifiedTeams.CANADA.team);
  }
}
