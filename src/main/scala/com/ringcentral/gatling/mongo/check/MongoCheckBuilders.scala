package com.ringcentral.gatling.mongo.check

import com.ringcentral.gatling.mongo.response.MongoResponse
import io.gatling.core.check.{Check, Extender, Preparer}
import io.gatling.commons.validation._

object MongoCheckBuilders {
  val countExtender: Extender[MongoCheck, MongoResponse] = (wrapped: Check[MongoResponse]) => MongoCheck(wrapped)
  val bodyExtender: Extender[MongoCheck, MongoResponse] = (wrapped: Check[MongoResponse]) => MongoCheck(wrapped)
  val passThroughResponsePreparer: Preparer[MongoResponse, MongoResponse] = _.success
}
