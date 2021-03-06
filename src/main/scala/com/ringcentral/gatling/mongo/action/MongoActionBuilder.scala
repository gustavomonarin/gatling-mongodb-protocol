package com.ringcentral.gatling.mongo.action

import com.ringcentral.gatling.mongo.command._
import com.ringcentral.gatling.mongo.protocol.{MongoComponents, MongoProtocolKey}
import io.gatling.core.action.Action
import io.gatling.core.action.builder.ActionBuilder
import io.gatling.core.config.GatlingConfiguration
import io.gatling.core.structure.ScenarioContext

class MongoActionBuilder(command: MongoCommand, configuration: GatlingConfiguration) extends ActionBuilder {

  protected def mongoComponents(ctx: ScenarioContext): MongoComponents = {
    ctx.protocolComponentsRegistry.components(MongoProtocolKey)
  }

  override def build(ctx: ScenarioContext, next: Action): Action = {
    val statsEngine = ctx.coreComponents.statsEngine
    val components = mongoComponents(ctx)
    val databaseContext = components.mongoContext
    command match {
      case rawCommand: MongoRawCommand => new MongoRawCommandAction(rawCommand, databaseContext.database, statsEngine, configuration, next)
      case countCommand: MongoCountCommand => new MongoCountAction(countCommand, databaseContext.database, statsEngine, configuration, next)
      case findCommand: MongoFindCommand => new MongoFindAction(findCommand, databaseContext.database, statsEngine, configuration, next)
      case removeCommand: MongoRemoveCommand => new MongoRemoveAction(removeCommand, databaseContext.database, statsEngine, configuration, next)
      case insertCommand: MongoInsertCommand => new MongoInsertAction(insertCommand, databaseContext.database, statsEngine, configuration, next)
      case updateCommand: MongoUpdateCommand => new MongoUpdateAction(updateCommand, databaseContext.database, statsEngine, configuration, next)
    }

  }
}