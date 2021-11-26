package ua.maksym.data

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

case class TransitionLog(id: Long, entityID: String, fromState: String, toState: String)

object TransitionLog {
  implicit val transitionLogEncoder: Encoder[TransitionLog] = deriveEncoder[TransitionLog]
  implicit val transitionLogDecoder: Decoder[TransitionLog] = deriveDecoder[TransitionLog]
}