package dev.moru3.SpringWebSocket.websocket

import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class WebSocketController: TextWebSocketHandler() {
    val sessions = mutableListOf<WebSocketSession>()

    /**
     * セッションが閉じた際に呼び出される関数
     */
    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        sessions.remove(session)
    }

    /**
     * セッションが開いた際に呼び出される関数
     */
    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessions.add(session)
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        // ここでJSON解析とかいろいろ
        sessions.forEach { it.sendMessage(TextMessage("こんちゃ！")) }
    }

}