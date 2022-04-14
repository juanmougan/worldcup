package com.github.juanmougan.worldcup.repositories;

import com.github.juanmougan.worldcup.draft.Confederation;
import com.github.juanmougan.worldcup.draft.Team;
import java.util.List;
import java.util.Set;

public interface TeamRepository {

  Team popHomeTeam();

  void delete(final Team team);

  List<Team> getTeamsByRank(final int rank);

  List<Team> getTeamsByRankExcludingConfederations(final int rank,
      final Set<Confederation> confederations);

}
