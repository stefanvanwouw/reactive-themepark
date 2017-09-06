package io.themepark.domain

import java.time.ZonedDateTime
import java.util.UUID

import rx.lang.scala.Observable

import scala.language.postfixOps


/**
  * Base for all events. All events should be in past tense.
  */
trait Event {

  /**
    * Event is part of this DDD aggregate. E.g. an AttractionId or VisitorId
    * @return The corresponding aggregate ID.
    */
  def aggregateId: UUID


  /**
    * The point in time this event has happened.
    * @return The corresponding date time.
    */
  def timestamp: ZonedDateTime

  /**
    * The version of the (view of) the aggregate this event was based upon. Used for conflict resolution.
    * @return A version number.
    */
  def basedOnVersion: Long


  /**
    * Id of the (long running) process this event was part of if any.
    * @return The process ID.
    */
  def processId: Option[UUID]

  /**
    * Id of the user that generated this event if any.
    * @return The user ID.
    */
  def userId: Option[UUID]


}

trait AttractionEvent extends Event

/**
  * An Attraction has been created in the stream. Should happen only once for every attraction.
  * @param aggregateId The ID of the attraction that was created.
  * @param timestamp The timestamp at which the attraction was created.
  * @param basedOnVersion The version of the event stream this attraction event was based upon.
  * @param processId Optional ID of long running process this is part of.
  * @param userId Optional user ID.
  * @param attraction The attraction object.
  */
case class CreatedAttraction(aggregateId: AttractionId,
                             timestamp: ZonedDateTime,
                             basedOnVersion: Long,
                             processId: Option[UUID],
                             userId: Option[UUID],
                             attraction: Attraction) extends AttractionEvent

/**
  * A new capacity has been set for an attraction in the stream.
  * @param aggregateId The ID of the attraction related to the capacity set.
  * @param timestamp The timestamp at which this event was created.
  * @param basedOnVersion The version of the event stream this attraction event was based upon.
  * @param processId Optional ID of long running process this is part of.
  * @param userId Optional user ID.
  * @param capacity The new capacity.
  */
case class SetCapacity(aggregateId: AttractionId,
                       timestamp: ZonedDateTime,
                       basedOnVersion: Long,
                       processId: Option[UUID],
                       userId: Option[UUID],
                       capacity: Int) extends AttractionEvent

case class QueuedVisitor(aggregateId: AttractionId, timestamp: ZonedDateTime, basedOnVersion: Long, processId: Option[UUID], userId: Option[UUID], visitorId: VisitorId) extends AttractionEvent
case class DeQueuedVisitor(aggregateId: AttractionId, timestamp: ZonedDateTime, basedOnVersion: Long, processId: Option[UUID], userId: Option[UUID], visitorId: VisitorId) extends AttractionEvent
case class EnteredRide(aggregateId: AttractionId, timestamp: ZonedDateTime, basedOnVersion: Long, processId: Option[UUID], userId: Option[UUID], visitorId: VisitorId) extends AttractionEvent
case class ExitedRide(aggregateId: AttractionId, timestamp: ZonedDateTime, basedOnVersion: Long, processId: Option[UUID], userId: Option[UUID], visitorId: VisitorId) extends AttractionEvent




trait AttractionEventStream {


  def subscribe(f: AttractionEvent => Unit)
}

class RxAttractionEventStream extends AttractionEventStream {
  val rxObservable: Observable[AttractionEvent] = _ //Observable.interval(200 seconds)

  override def subscribe(f: (AttractionEvent) => Unit): Unit = {
    rxObservable.subscribe(f)
  }
}
