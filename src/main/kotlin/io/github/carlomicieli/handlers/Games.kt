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

import kotlinx.coroutines.reactor.awaitSingle
import org.slf4j.LoggerFactory
import org.springframework.context.support.beans
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.buildAndAwait
import org.springframework.web.reactive.function.server.coRouter
import java.net.URI

object Games {
    val beans = beans {
        bean<GameHandlers>()

        bean {
            val gameHandlers = ref<GameHandlers>()
            routes(gameHandlers)
        }
    }

    fun routes(
        gameHandlers: GameHandlers
    ): RouterFunction<ServerResponse> = coRouter {
        "/api/games".nest {
            accept(MediaType.APPLICATION_JSON).nest {
                POST("", gameHandlers::createGame)
                PUT("{game}", gameHandlers::updateGame)
            }

            GET("{game}", gameHandlers::getGameById)
            GET("", gameHandlers::getGames)
        }
    }
}

class GameHandlers {
    companion object {
        val log = LoggerFactory.getLogger(GameHandlers::class.java)
    }

    suspend fun createGame(request: ServerRequest): ServerResponse = ServerResponse.created(URI("localhost")).buildAndAwait()
    suspend fun updateGame(request: ServerRequest): ServerResponse = ServerResponse.ok().bodyValue("TODO").awaitSingle()
    suspend fun getGameById(request: ServerRequest): ServerResponse = ServerResponse.ok().bodyValue("TODO").awaitSingle()
    suspend fun getGames(request: ServerRequest): ServerResponse = ServerResponse.ok().bodyValue("TODO").awaitSingle()
}
