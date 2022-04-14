package com.github.juanmougan.worldcup.draft;

import com.github.juanmougan.worldcup.services.GroupService;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WorldCupDrafter {

  private final GroupService groupService;

  public Set<Group> generateDraft() {
    final Stream<Group> homeTeamGroup = Stream.of(groupService.createHomeTeamGroup());
    final Stream<Group> otherGroups = IntStream.rangeClosed(1, 7)
        .mapToObj(groupService::createGroup);
    return Stream.concat(homeTeamGroup, otherGroups).collect(Collectors.toSet());
  }
}
