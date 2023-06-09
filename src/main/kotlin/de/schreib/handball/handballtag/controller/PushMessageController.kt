package de.schreib.handball.handballtag.controller

import de.schreib.handball.handballtag.entities.PushMessage
import de.schreib.handball.handballtag.entities.TargetTopic
import de.schreib.handball.handballtag.repositories.PushMessageRepository
import de.schreib.handball.handballtag.services.PushMessageService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("pushmessage")
class PushMessageController(
    val pushMessageRepository: PushMessageRepository, val pushMessageService: PushMessageService
) {
    // TODO mit query parameter severity und target audience
    @GetMapping()
    fun loadAllPushMessage(): List<PushMessage> {
        return pushMessageRepository.findAll()
    }

    @PostMapping("/{clientToken}")
    fun registerClient(
        @PathVariable clientToken: String
    ) {
        pushMessageService.registerClientToTopic(clientToken, TargetTopic.DEFAULT)
    }

    @PostMapping
    fun publishNewMessage(
        @RequestBody
        pushMessage: PushMessage
    ) {
        pushMessageService.publishMessage(pushMessage)
    }
}

