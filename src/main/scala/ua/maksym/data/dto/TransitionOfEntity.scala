package ua.maksym.data.dto

import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder

case class TransitionOfEntity(entityID: Long, stateID: Long)

object TransitionOfEntity {
  implicit val transitionOfEntityDecoder: Decoder[TransitionOfEntity] = deriveDecoder[TransitionOfEntity]
}