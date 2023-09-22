package com.food.order.valueobject

import java.util.UUID

class TrackingId(val id: UUID) : BaseId<UUID>(id) {
}