package com.example.models.game

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Schedule(
    @SerialName("leagueSchedule") val leagueSchedule: LeagueSchedule?
) {
    @Serializable
    data class LeagueSchedule(
        @SerialName("gameDates")
        val gameDates: List<GameDate?>?,
        @SerialName("leagueId")
        val leagueId: String?, // 聯盟id, e.g. 00
        @SerialName("seasonYear")
        val seasonYear: String?, // 賽季年份, e.g. 2022-23
    ) {

        @Serializable
        data class GameDate(
            @SerialName("gameDate")
            val gameDate: String?, // 比賽日期，時間都12:00, e.g. 10/30/2022 12:00:00 AM
            @SerialName("games")
            val games: List<Game?>?
        ) {
            @Serializable
            data class Game(
                @SerialName("awayTeam")
                val awayTeam: AwayTeam?,
                @SerialName("day")
                val day: String?, // 星期幾(縮寫), e.g. Sun
                @SerialName("gameCode")
                val gameCode: String?, // 日期與對戰隊伍, e.g. 20221030/WASBOS
                @SerialName("gameId")
                val gameId: String?, // 比賽id, e.g. 0022200089
                @SerialName("gameStatus")
                val gameStatus: Int?,
                @SerialName("gameStatusText")
                val gameStatusText: String?, // 比賽狀態(Final或開始時間), e.g. Final or 3:00 pm ET
                @SerialName("gameSequence")
                val gameSequence: Int?, // 今天的第幾場比賽(起始為1), e.g. 1
                @SerialName("homeTeam")
                val homeTeam: HomeTeam?,
                @SerialName("gameDateEst")
                val gameDateEst: String?, // 比賽開始日期(固定為00:00), e.g. 2022-11-20T00:00:00Z
                @SerialName("gameDateTimeEst")
                val gameDateTimeEst: String?, // 比賽開始時間, e.g. 2022-10-30T12:00:00Z
                @SerialName("monthNum")
                val monthNum: Int?, // 月份, e.g. 10
                @SerialName("pointsLeaders")
                val pointsLeaders: List<PointsLeader?>?,
                @SerialName("weekNumber")
                val weekNumber: Int? // 系列賽第幾週, e.g. 3
            ) {
                @Serializable
                data class AwayTeam(
                    @SerialName("losses")
                    val losses: Int?, // 敗場場次(從這場之前), e.g. 2
                    @SerialName("score")
                    val score: Int?, // 比分, e.g. 100
                    @SerialName("teamCity")
                    val teamCity: String?, // 城市名, e.g. LA
                    @SerialName("teamId")
                    val teamId: Int?, // 隊伍id, e.g. 1610612746
                    @SerialName("teamName")
                    val teamName: String?, // 隊伍名稱, e.g. Clippers
                    @SerialName("teamTricode")
                    val teamTricode: String?, // 隊伍縮寫名稱, e.g. LAC
                    @SerialName("wins")
                    val wins: Int? // 勝場場次(從這場之前), e.g. 2
                )

                @Serializable
                data class HomeTeam(
                    @SerialName("losses")
                    val losses: Int?,
                    @SerialName("score")
                    val score: Int?,
                    @SerialName("teamCity")
                    val teamCity: String?,
                    @SerialName("teamId")
                    val teamId: Int?,
                    @SerialName("teamName")
                    val teamName: String?,
                    @SerialName("teamTricode")
                    val teamTricode: String?,
                    @SerialName("wins")
                    val wins: Int?
                )

                @Serializable
                data class PointsLeader(
                    @SerialName("firstName")
                    val firstName: String?, // 球員名稱(姓), e.g. Shai
                    @SerialName("lastName")
                    val lastName: String?, // 球員名稱(名), e.g. Gilgeous-Alexander
                    @SerialName("personId")
                    val personId: Int?, // 球員id, e.g. 1628983
                    @SerialName("points")
                    val points: Double?, // 球員當場得分, e.g. 38.0
                    @SerialName("teamId")
                    val teamId: Int?, // 球員所屬球隊id, e.g. 1610612760
                    @SerialName("teamName")
                    val teamName: String?, // 球員所屬球隊名稱, e.g. Thunder
                    @SerialName("teamTricode")
                    val teamTricode: String? // 球員所屬球隊縮寫名稱, e.g. OKC
                )
            }
        }
    }
}