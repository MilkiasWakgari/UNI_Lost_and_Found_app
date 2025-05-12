import { ChatRepository } from '../repositories/ChatRepository';
import { IChat } from '../models/Chat';
import { IMessage } from '../models/Message';

export class ChatService {
    private chatRepository: ChatRepository;

    constructor() {
        this.chatRepository = new ChatRepository();
    }

    async getChats(userId: string): Promise<IChat[]> {
        return this.chatRepository.getChats(userId);
    }

    async getChatById(chatId: string): Promise<IChat | null> {
        return this.chatRepository.getChatById(chatId);
    }

    async getOrCreateChat(userId: string, otherUserId: string): Promise<IChat> {
        return this.chatRepository.getOrCreateChat(userId, otherUserId);
    }

    async getMessages(chatId: string): Promise<IMessage[]> {
        return this.chatRepository.getMessages(chatId);
    }

    async sendMessage(chatId: string, senderId: string, content: string): Promise<IMessage> {
        if (!content.trim()) {
            throw new Error('Message content cannot be empty');
        }

        return this.chatRepository.createMessage(chatId, senderId, content);
    }

    async markMessageAsRead(messageId: string): Promise<IMessage | null> {
        return this.chatRepository.markMessageAsRead(messageId);
    }

    async markAllMessagesAsRead(chatId: string, userId: string): Promise<void> {
        return this.chatRepository.markAllMessagesAsRead(chatId, userId);
    }
} 