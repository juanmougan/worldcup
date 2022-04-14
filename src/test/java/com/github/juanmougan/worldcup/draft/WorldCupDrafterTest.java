package com.github.juanmougan.worldcup.draft;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.github.juanmougan.worldcup.helpers.Draft;
import com.github.juanmougan.worldcup.services.GroupService;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WorldCupDrafterTest {

  private WorldCupDrafter drafter;

  @BeforeEach
  void setUp() {
    final GroupService groupService = mock(GroupService.class);
    mockHomeTeamGroup(groupService);
    mockRestOfGroups(groupService);
    drafter = new WorldCupDrafter(groupService);
  }

  private void mockHomeTeamGroup(final GroupService groupService) {
    when(groupService.createHomeTeamGroup()).thenReturn(Draft.mockHomeGroup());
  }

  private void mockRestOfGroups(final GroupService groupService) {
    IntStream.rangeClosed(1, 7).forEach(i -> {
      final var group = Draft.mockGroup(i);
      when(groupService.createGroup(eq(i))).thenReturn(group);
    });
  }

  @Test
  void givenTeams_whenGenerateDraft_thenGenerateValidDraft() {
    final var groups = drafter.generateDraft();
    Assertions.assertThat(groups).extracting(Group::getId)
        .containsOnly('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H');
  }
}
