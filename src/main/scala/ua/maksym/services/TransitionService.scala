package ua.maksym.services

import cats.Monad
import cats.data.EitherT
import ua.maksym.data.Transition
import ua.maksym.errors.Err
import ua.maksym.repo.{StateRepo, TransitionRepo}

class TransitionService[F[_]: TransitionRepo: StateRepo: Monad](stateRepo: StateRepo[F], transitionRepo: TransitionRepo[F]) {

  def addTransition(from: Long, to: Long): EitherT[F, Err, Transition] = {
    for {
      _ <- stateRepo.getState(from)
      _ <- stateRepo.getState(to)
      transition <- transitionRepo.addTransition(from, to)
    } yield transition
  }

  def removeTransition(from: Long, to: Long): EitherT[F, Err, Transition] =
    transitionRepo.removeTransition(from, to)
}
