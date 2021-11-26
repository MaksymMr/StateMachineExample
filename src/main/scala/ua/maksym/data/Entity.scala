package ua.maksym.data

import io.circe._
import io.circe.generic.semiauto._

case class Entity(id: Long, name: String, stateID: Long)

object Entity {
  implicit val entityEncoder: Encoder[Entity] = deriveEncoder[Entity]
  implicit val entityDecoder: Decoder[Entity] = deriveDecoder[Entity]
}