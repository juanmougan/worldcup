package com.github.juanmougan.worldcup.helpers;

import static com.github.juanmougan.worldcup.draft.Confederation.AFC;
import static com.github.juanmougan.worldcup.draft.Confederation.CAF;
import static com.github.juanmougan.worldcup.draft.Confederation.CONCACAF;
import static com.github.juanmougan.worldcup.draft.Confederation.CONMEBOL;
import static com.github.juanmougan.worldcup.draft.Confederation.UEFA;

import com.github.juanmougan.worldcup.draft.Team;
import java.util.List;
import lombok.RequiredArgsConstructor;

// This should probably be retrieved from a YAML file
@RequiredArgsConstructor
public enum QualifiedTeams {

  QATAR(
      Team.builder().name("Qatar").rank(1).confederation(AFC).homeTeam(true).build()),
  BELGIUM(
      Team.builder().name("Belgium").rank(1).confederation(UEFA).homeTeam(false).build()),
  BRAZIL(
      Team.builder().name("Brazil").rank(1).confederation(CONMEBOL).homeTeam(false).build()),
  FRANCE(
      Team.builder().name("France").rank(1).confederation(UEFA).homeTeam(false).build()),
  ARGENTINA(
      Team.builder().name("Argentina").rank(1).confederation(CONMEBOL).homeTeam(false).build()),
  ENGLAND(
      Team.builder().name("England").rank(1).confederation(UEFA).homeTeam(false).build()),
  SPAIN(
      Team.builder().name("Spain").rank(1).confederation(UEFA).homeTeam(false).build()),
  PORTUGAL(
      Team.builder().name("Portugal").rank(1).confederation(UEFA).homeTeam(false).build()),

  DENMARK(
      Team.builder().name("Denmark").rank(2).confederation(UEFA).homeTeam(false).build()),
  NETHERLANDS(
      Team.builder().name("Netherlands").rank(2).confederation(UEFA).homeTeam(false).build()),
  GERMANY(
      Team.builder().name("Germany").rank(2).confederation(UEFA).homeTeam(false).build()),
  SWITZERLAND(
      Team.builder().name("Switzerland").rank(2).confederation(UEFA).homeTeam(false).build()),
  CROATIA(
      Team.builder().name("Croatia").rank(2).confederation(UEFA).homeTeam(false).build()),
  URUGUAY(
      Team.builder().name("Uruguay").rank(2).confederation(UEFA).homeTeam(false).build()),
  MEXICO(
      Team.builder().name("Mexico").rank(2).confederation(CONCACAF).homeTeam(false).build()),
  USA(
      Team.builder().name("USA").rank(2).confederation(CONCACAF).homeTeam(false).build()),

  IRAN(
      Team.builder().name("Iran").rank(3).confederation(AFC).homeTeam(false).build()),
  JAPAN(
      Team.builder().name("Japan").rank(3).confederation(AFC).homeTeam(false).build()),
  SERBIA(
      Team.builder().name("Serbia").rank(3).confederation(UEFA).homeTeam(false).build()),
  SOUTH_KOREA(
      Team.builder().name("South Korea").rank(3).confederation(AFC).homeTeam(false).build()),
  CANADA(
      Team.builder().name("Canada").rank(3).confederation(CONCACAF).homeTeam(false).build()),
  POLAND(
      Team.builder().name("Poland").rank(3).confederation(UEFA).homeTeam(false).build()),
  MOROCCO(
      Team.builder().name("Morocco").rank(3).confederation(CAF).homeTeam(false).build()),
  SENEGAL(
      Team.builder().name("Senegal").rank(3).confederation(CAF).homeTeam(false).build()),

  SAUDI_ARABIA(
      Team.builder().name("Saudi Arabia").rank(4).confederation(AFC).homeTeam(false).build()),
  ECUADOR(
      Team.builder().name("Ecuador").rank(4).confederation(CONMEBOL).homeTeam(false).build()),
  GHANA(
      Team.builder().name("Ghana").rank(4).confederation(CAF).homeTeam(false).build()),
  TUNISIA(
      Team.builder().name("Tunisia").rank(4).confederation(CAF).homeTeam(false).build()),
  CAMEROON(
      Team.builder().name("Cameroon").rank(4).confederation(CAF).homeTeam(false).build()),
  // TODO using placeholders here
  CONMEBOL_OR_AFC(
      Team.builder().name("CONMEBOL or AFC team").rank(4).confederation(CONMEBOL).homeTeam(false)
          .build()),
  CONCACAF_OR_OFC_TEAM(
      Team.builder().name("CONCACAF or OFC team").rank(4).confederation(CONCACAF).homeTeam(false)
          .build()),
  UEFA_TEAM(
      Team.builder().name("UEFA team").rank(4).confederation(CONCACAF).homeTeam(false).build());

  public final Team team;

  public static List<Team> getFirstRankTeams() {
    return List.of(QATAR.team, BELGIUM.team, BRAZIL.team, FRANCE.team, ARGENTINA.team, ENGLAND.team,
        SPAIN.team, PORTUGAL.team);
  }

  public static List<Team> getSecondRankTeams() {
    return List.of(DENMARK.team,
        NETHERLANDS.team,
        GERMANY.team,
        SWITZERLAND.team,
        CROATIA.team,
        URUGUAY.team,
        MEXICO.team,
        USA.team);
  }

  public static List<Team> getThirdRankTeams() {
    return List.of(IRAN.team,
        JAPAN.team,
        SERBIA.team,
        SOUTH_KOREA.team,
        CANADA.team,
        POLAND.team,
        MOROCCO.team,
        SENEGAL.team);
  }

  public static List<Team> getFourthRankTeams() {
    return List.of(SAUDI_ARABIA.team,
        ECUADOR.team,
        GHANA.team,
        TUNISIA.team,
        CAMEROON.team,
        CONMEBOL_OR_AFC.team,
        CONCACAF_OR_OFC_TEAM.team,
        UEFA_TEAM.team);
  }
}
