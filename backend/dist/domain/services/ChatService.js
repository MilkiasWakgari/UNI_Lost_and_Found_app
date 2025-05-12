"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ChatService = void 0;
const ChatRepository_1 = require("../repositories/ChatRepository");
class ChatService {
    constructor() {
        this.chatRepository = new ChatRepository_1.ChatRepository();
    }
    async getChats(userId) {
        return this.chatRepository.getChats(userId);
    }
    async getChatById(chatId) {
        return this.chatRepository.getChatById(chatId);
    }
    async getOrCreateChat(userId, otherUserId) {
        return this.chatRepository.getOrCreateChat(userId, otherUserId);
    }
    async getMessages(chatId) {
        return this.chatRepository.getMessages(chatId);
    }
    async sendMessage(chatId, senderId, content) {
        if (!content.trim()) {
            throw new Error('Message content cannot be empty');
        }
        return this.chatRepository.createMessage(chatId, senderId, content);
    }
    async markMessageAsRead(messageId) {
        return this.chatRepository.markMessageAsRead(messageId);
    }
    async markAllMessagesAsRead(chatId, userId) {
        return this.chatRepository.markAllMessagesAsRead(chatId, userId);
    }
}
exports.ChatService = ChatService;
