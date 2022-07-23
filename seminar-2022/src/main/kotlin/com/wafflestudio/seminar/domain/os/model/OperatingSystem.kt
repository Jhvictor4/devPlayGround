package com.wafflestudio.seminar.domain.os.model

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
class OperatingSystem(
    var price: Long,

    @field:NotBlank
    var name: String,

    @field:NotBlank
    var description: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
