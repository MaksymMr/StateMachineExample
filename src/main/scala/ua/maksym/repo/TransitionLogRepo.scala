package ua.maksym.repo

import cats.data.EitherT
import ua.maksym.data.TransitionLog
import ua.maksym.errors.Err

trait TransitionLogRepo[F[_]] {
  def add(entityID: Long, from: String, to: String): EitherT[F, Err, Long]
  def getForEntity(entityID: Long): EitherT[F, Err, Seq[TransitionLog]]
  def removeForEntity(entityID: Long): EitherT[F, Err, TransitionLog]
}