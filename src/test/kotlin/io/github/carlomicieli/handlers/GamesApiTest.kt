/*
 *   Copyright (c) 2022 Carlo Micieli
 *
 *    Licensed to the Apache Software Foundation (ASF) under one
 *    or more contributor license agreements.  See the NOTICE file
 *    distributed with this work for additional information
 *    regarding copyright ownership.  The ASF licenses this file
 *    to you under the Apache License, Version 2.0 (the
 *    "License"); you may not use this file except in compliance
 *    with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing,
 *    software distributed under the License is distributed on an
 *    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *    KIND, either express or implied.  See the License for the
 *    specific language governing permissions and limitations
 *    under the License.
 */
package io.github.carlomicieli.handlers

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono

@DisplayName("Games Api")
@TestInstance(Lifecycle.PER_CLASS)
class GamesApiTest {
    private lateinit var webclient: WebTestClient

    @BeforeAll
    fun setup() {
        val handlers = GameHandlers()
        webclient =
            WebTestClient.bindToRouterFunction(
                Games.routes(handlers)
            ).build()
    }

    @Test
    fun `should return the list of games`() {
        webclient
            .get()
            .uri("/api/games")
            .exchange()
            .expectStatus()
            .isOk
            .expectBody(String::class.java)
            .value { Assertions.assertEquals("TODO", it) }
    }

    @Test
    fun `should return the game by its id`() {
        webclient
            .get()
            .uri("/api/games/{game}", "fatfury")
            .exchange()
            .expectStatus()
            .isOk
            .expectBody(String::class.java)
            .value { Assertions.assertEquals("TODO", it) }
    }

    @Test
    fun `should post a new game`() {
        webclient
            .post()
            .uri("/api/games")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.empty(), String::class.java)
            .exchange()
            .expectStatus()
            .isCreated
    }

    @Test
    fun `should replace a game`() {
        webclient
            .put()
            .uri("/api/games/{game}", "fatfury")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.empty(), String::class.java)
            .exchange()
            .expectStatus()
            .isOk
            .expectBody(String::class.java)
            .value { Assertions.assertEquals("TODO", it) }
    }
}
