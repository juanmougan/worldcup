package com.github.juanmougan.worldcup.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.github.juanmougan.worldcup.draft.Team;
import com.github.juanmougan.worldcup.helpers.QualifiedTeams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {

  @Mock
  private TeamService teamService;

  private GroupService groupService;

  @BeforeEach
  void setUp() {
    when(teamService.getHomeTeam()).thenReturn(QualifiedTeams.QATAR.team);
    // TODO need a matcher for Group matching the id to write tests for createGroup()
    when(teamService.getValidTeamForRank(eq(2), any())).thenReturn(QualifiedTeams.NETHERLANDS.team);
    when(teamService.getValidTeamForRank(eq(3), any())).thenReturn(QualifiedTeams.SENEGAL.team);
    when(teamService.getValidTeamForRank(eq(4), any())).thenReturn(QualifiedTeams.ECUADOR.team);
    groupService = new GroupService(teamService);
  }

  @Test
  void givenTeams_whenCreateHomeTeamGroup_thenReturnGroupZeroWithAllValidTeams() {
    final var homeTeamGroup = groupService.createHomeTeamGroup();
    final var teams = homeTeamGroup.getTeams();
    assertThat(teams).extracting(Team::isHomeTeam).containsOnlyOnce(true);
    assertThat(teams).extracting(Team::getRank).containsOnly(1, 2, 3, 4);
  }
}
