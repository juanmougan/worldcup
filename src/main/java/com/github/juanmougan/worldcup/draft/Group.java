package com.github.juanmougan.worldcup.draft;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Group {

  private char id;

  @Builder.Default
  private Set<Team> teams = new HashSet<>();

  public static char resolveId(final int index) {
    return (char) (index + 'A');
  }

  public void addTeam(final Team team) {
    this.teams.add(team);
  }
}
