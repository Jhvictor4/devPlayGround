package com.wafflestudio.seminar.domain.survey.model

import com.wafflestudio.seminar.domain.os.model.OperatingSystem
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class SurveyResponse(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    var os: OperatingSystem?,

    @field:Min(0, message = "The value must be between 1 and 5")
    @field:Max(5, message = "The value must be between 1 and 5")
    var springExp: Int,

    @field:Min(0, message = "The value must be between 1 and 5")
    @field:Max(5, message = "The value must be between 1 and 5")
    var rdbExp: Int?,

    @field:Min(0, message = "The value must be between 1 and 5")
    @field:Max(5, message = "The value must be between 1 and 5")
    var programmingExp: Int?,

    @field:NotBlank
    var major: String,

    @field:NotBlank
    var grade: String,

    @Column(name = "backend_reason")
    var backendReason: String,

    @Column(name = "waffle_reason")
    var waffleReason: String,

    @Column(name = "something_to_say")
    var somethingToSay: String,

    @field:NotNull
    var timestamp: LocalDateTime = LocalDateTime.now(),
)