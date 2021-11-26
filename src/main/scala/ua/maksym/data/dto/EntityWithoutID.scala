package ua.maksym.data.dto

import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder

case class EntityWithoutID(name: String, stateID: Long)

object EntityWithoutID {
  implicit val entityWithoutIDDecoder: Decoder[EntityWithoutID] = deriveDecoder[EntityWithoutID]
}