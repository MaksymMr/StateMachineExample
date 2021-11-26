package ua.maksym.data

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

case class State(id: Long, name: String)

object State {
  implicit val stateEncoder: Encoder[State] = deriveEncoder[State]
  implicit val stateDecoder: Decoder[State] = deriveDecoder[State]
}