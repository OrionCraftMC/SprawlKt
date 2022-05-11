package io.github.orioncraftmc.stretchkt.node

import java.util.*

typealias NodeId = Int

data class Node(
    val instance: UUID, val local: UUID
)

class Stretch private constructor(
    val id: UUID = UUID.randomUUID(),
    val nodesToIds: MutableMap<Node, NodeId> = mutableMapOf(),
    val idsToNodes: MutableMap<NodeId, Node> = mutableMapOf(),
    //TODO: val forest: Forest = Forest()
)