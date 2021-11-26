package ua.maksym.services

import cats.data.EitherT
import ua.maksym.data.TransitionLog
import ua.maksym.errors.Err
import ua.maksym.repo.TransitionLogRepo

class TransitionLogService[F[_]](transitionLogRepo: TransitionLogRepo[F]) {
  def getTransitionLogsForEntity(entityID: Long): EitherT[F, Err, Seq[TransitionLog]] =
    transitionLogRepo.getForEntity(entityID)
}
