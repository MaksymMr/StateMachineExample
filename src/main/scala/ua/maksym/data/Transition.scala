package ua.maksym.data

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

case class Transition(from: Long, to: Long)

object Transition {
  implicit val transitionEncoder: Encoder[Transition] = deriveEncoder[Transition]
  implicit val transitionDecoder: Decoder[Transition] = deriveDecoder[Transition]
}