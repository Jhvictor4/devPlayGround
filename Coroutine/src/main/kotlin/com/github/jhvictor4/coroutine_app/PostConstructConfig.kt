package com.github.jhvictor4.coroutine_app

import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
class PostConstructConfig(
    private val userRepository: UserRepository,
) {

    @PostConstruct
    fun a() {
        userRepository.save(User("지혁"))
        userRepository.findAll()
    }

}
