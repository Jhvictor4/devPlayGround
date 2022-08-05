package com.wafflestudio.seminar.domain.os.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
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

    companion object {
        fun of(type: OperatingSystemType, price: Long): OperatingSystem {
            return OperatingSystem(
                price,
                type.input,
                type.desc,
            )
        }
    }
}