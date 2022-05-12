package io.github.orioncraftmc.stretchkt.node

import io.github.orioncraftmc.stretchkt.forest.Forest
import java.util.*

typealias NodeId = UUID

data class Node(
    val instance: UUID, val local: UUID
)

class Stretch private constructor(
    val id: UUID = UUID.randomUUID(),
    val nodesToIds: MutableMap<Node, NodeId> = mutableMapOf(),
    val idsToNodes: MutableMap<NodeId, Node> = mutableMapOf(),
    val forest: Forest = Forest()
)