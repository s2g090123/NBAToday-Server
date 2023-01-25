package com.example.models.game

import kotlinx.serialization.Serializable

@Serializable
data class Game(
    val game: Game?
) {
    @Serializable
    data class Game(
        val gameId: String?,
        val gameEt: String?, // e.g. 2022-12-15T20:00:00-05:00
        val gameCode: String?,
        val gameStatusText: String?,
        val gameStatus: Int?,
        val homeTeam: Team?,
        val awayTeam: Team?
    ) {
        @Serializable
        data class Team(
            val teamId: Int?,
            val teamName: String?,
            val teamCity: String?,
            val teamTricode: String?,
            val score: Int?,
            val inBonus: String?,
            val timeoutsRemaining: Int?,
            val periods: List<Period?>?,
            val players: List<Player?>?,
            val statistics: Statistics?
        ) {
            @Serializable
            data class Period(
                val period: Int?,
                val periodType: String?,
                val score: Int?
            )

            @Serializable
            data class Player(
                val status: String?,
                val notPlayingReason: String?,
                val order: Int?,
                val personId: Int?,
                val jerseyNum: String?,
                val position: String?,
                val starter: String?,
                val oncourt: String?,
                val played: String?,
                val statistics: Statistics?,
                val name: String?,
                val nameI: String?,
                val firstName: String?,
                val familyName: String?
            ) {
                @Serializable
                data class Statistics(
                    val assists: Int?,
                    val blocks: Int?,
                    val blocksReceived: Int?,
                    val fieldGoalsAttempted: Int?,
                    val fieldGoalsMade: Int?,
                    val fieldGoalsPercentage: Double?,
                    val foulsOffensive: Int?,
                    val foulsDrawn: Int?,
                    val foulsPersonal: Int?,
                    val foulsTechnical: Int?,
                    val freeThrowsAttempted: Int?,
                    val freeThrowsMade: Int?,
                    val freeThrowsPercentage: Double?,
                    val minus: Double?,
                    val minutes: String?,
                    val plus: Double?,
                    val plusMinusPoints: Double?,
                    val points: Int?,
                    val reboundsDefensive: Int?,
                    val reboundsOffensive: Int?,
                    val reboundsTotal: Int?,
                    val steals: Int?,
                    val threePointersAttempted: Int?,
                    val threePointersMade: Int?,
                    val threePointersPercentage: Double?,
                    val turnovers: Int?,
                    val twoPointersAttempted: Int?,
                    val twoPointersMade: Int?,
                    val twoPointersPercentage: Double?
                )
            }

            @Serializable
            data class Statistics(
                val assists: Int?,
                val blocks: Int?,
                val blocksReceived: Int?,
                val fieldGoalsAttempted: Int?,
                val fieldGoalsMade: Int?,
                val fieldGoalsPercentage: Double?,
                val foulsOffensive: Int?,
                val foulsDrawn: Int?,
                val foulsPersonal: Int?,
                val foulsTeam: Int?,
                val foulsTechnical: Int?,
                val freeThrowsAttempted: Int?,
                val freeThrowsMade: Int?,
                val freeThrowsPercentage: Double?,
                val points: Int?,
                val reboundsDefensive: Int?,
                val reboundsOffensive: Int?,
                val reboundsPersonal: Int?,
                val reboundsTotal: Int?,
                val steals: Int?,
                val threePointersAttempted: Int?,
                val threePointersMade: Int?,
                val threePointersPercentage: Double?,
                val turnovers: Int?,
                val turnoversTeam: Int?,
                val turnoversTotal: Int?,
                val twoPointersAttempted: Int?,
                val twoPointersMade: Int?,
                val twoPointersPercentage: Double?,
                val pointsFastBreak: Int?,
                val pointsFromTurnovers: Int?,
                val pointsInThePaint: Int?,
                val pointsSecondChance: Int?,
                val benchPoints: Int?
            )
        }
    }
}