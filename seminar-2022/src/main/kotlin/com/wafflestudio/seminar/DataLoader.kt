package com.wafflestudio.seminar

import com.wafflestudio.seminar.domain.os.model.OperatingSystem
import com.wafflestudio.seminar.domain.os.model.OperatingSystemType
import com.wafflestudio.seminar.domain.os.repository.OperatingSystemRepository
import com.wafflestudio.seminar.domain.survey.model.SurveyResponse
import com.wafflestudio.seminar.domain.survey.repository.SurveyResponseRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.FileReader
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Component
class DataLoader(
    private val operatingSystemRepository: OperatingSystemRepository,
    private val surveyResponseRepository: SurveyResponseRepository,
) : ApplicationRunner {

    /**
     * Runs automatically whe Application starts.
     */
    override fun run(args: ApplicationArguments) {
        operatingSystemRepository.saveAll(listOf(
            OperatingSystem.of(OperatingSystemType.Windows, price = 200000),
            OperatingSystem.of(OperatingSystemType.MacOS, price = 300000),
            OperatingSystem.of(OperatingSystemType.Linux, price = 0),
        ))

        BufferedReader(FileReader(ClassPathResource("data/example_surveyresult.tsv").file))
            .use { br ->
                br.readLines()
                    .map { parseLine(it) }
                    .toList()
                    .let(surveyResponseRepository::saveAll)
            }
    }

    private fun parseLine(parseLine: String): SurveyResponse {
        val rawSurveyResponse = parseLine.split("\t")
        return SurveyResponse(
            timestamp = LocalDateTime.parse(rawSurveyResponse[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            os = operatingSystemRepository.findByNameEquals(rawSurveyResponse[1]),
            springExp = rawSurveyResponse[2].toInt(),
            rdbExp = rawSurveyResponse[3].toInt(),
            programmingExp = rawSurveyResponse[4].toInt(),
            major = rawSurveyResponse[5],
            grade = rawSurveyResponse[6],
            backendReason = "",
            waffleReason = "",
            somethingToSay = "",
        )
    }
}
