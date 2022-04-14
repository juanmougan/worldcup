package com.github.juanmougan.worldcup.helpers;

import com.github.juanmougan.worldcup.draft.Group;
import java.util.Set;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Draft {

  public static Group mockHomeGroup() {
    return Group.builder()
        .id('A')
        .teams(Set.of(QualifiedTeams.QATAR.team, QualifiedTeams.NETHERLANDS.team,
            QualifiedTeams.SENEGAL.team, QualifiedTeams.ECUADOR.team))
        .build();
  }

  // TODO remove hardcoded teams in order to write better assertions
  public static Group mockGroup(final int index) {
    return Group.builder()
        .id(Group.resolveId(index))
        .teams(Set.of(QualifiedTeams.ARGENTINA.team, QualifiedTeams.MEXICO.team,
            QualifiedTeams.POLAND.team, QualifiedTeams.SAUDI_ARABIA.team))
        .build();
  }
}
