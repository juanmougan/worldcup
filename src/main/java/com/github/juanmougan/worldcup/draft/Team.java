package com.github.juanmougan.worldcup.draft;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {

  private String name;
  private Confederation confederation;
  private int rank;
  private boolean homeTeam;

}
