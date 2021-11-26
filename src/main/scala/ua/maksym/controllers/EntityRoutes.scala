package ua.maksym.controllers

import cats.Monad
import cats.effect.Concurrent
import cats.implicits._
import io.circe.syntax._
import org.http4s.HttpRoutes
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl
import ua.maksym.data.dto.{EntityWithoutID, TransitionOfEntity}
import ua.maksym.errors.StateNotFound
import ua.maksym.services._

class EntityRoutes[F[_]: Monad](
  entityService: EntityService[F],
  transitionLogService: TransitionLogService[F]
)(implicit ce: ConcurrentEffect[F]) extends Http4sDsl[F] {

  def routes: HttpRoutes[F] = {
    HttpRoutes.of[F] {

        case GET -> Root / "entity" / id =>
          entityService
            .getEntity(id.toLong)
            .value
            .flatMap {
              case Right(entity) =>  Ok(entity.asJson)
              case Left(err) => NotFound(err.msg)
            }

        case GET -> Root / "entity" / id / "transitions" =>
          transitionLogService
            .getTransitionLogsForEntity(id.toLong)
            .value
            .flatMap {
              case Right(transitions) => Ok(transitions.asJson)
              case Left(err) => NotFound(err.msg)
            }

        case req@ POST -> Root / "entity" =>
          req
            .decodeJson[EntityWithoutID]
            .flatMap(e => entityService.addEntity(e.name, e.stateID).value)
            .flatMap {
              case Right(id) => Created(id.asJson)
              case Left(err) => BadRequest(err.msg)
            }

        case req@ POST -> Root / "entity" / "transit" =>
          req
            .decodeJson[TransitionOfEntity]
            .flatMap(transition => entityService.transitEntity(transition.entityID, transition.stateID).value)
            .flatMap {
              case Right(state) => Ok(state.asJson)
              case Left(err) => err match {
                case e: StateNotFound => NotFound(e.msg)
                case e => BadRequest(e.msg)
              }
            }
    }
  }

}
