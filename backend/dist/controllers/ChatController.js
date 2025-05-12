"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ChatController = void 0;
const ChatService_1 = require("../domain/services/ChatService");
class ChatController {
    constructor() {
        this.getUserChats = async (req, res) => {
            var _a;
            try {
                const userId = (_a = req.user) === null || _a === void 0 ? void 0 : _a._id.toString();
                if (!userId) {
                    return res.status(401).json({ message: 'User not authenticated' });
                }
                const chats = await this.chatService.getChats(userId);
                res.json(chats);
            }
            catch (error) {
                res.status(500).json({ message: 'Error fetching chats' });
            }
        };
        this.getChatById = async (req, res) => {
            try {
                const chatId = req.params.id;
                const chat = await this.chatService.getChatById(chatId);
                if (!chat) {
                    return res.status(404).json({ message: 'Chat not found' });
                }
                res.json(chat);
            }
            catch (error) {
                res.status(500).json({ message: 'Error fetching chat' });
            }
        };
        this.createChat = async (req, res) => {
            var _a;
            try {
                const { otherUserId } = req.body;
                const userId = (_a = req.user) === null || _a === void 0 ? void 0 : _a._id.toString();
                if (!userId) {
                    return res.status(401).json({ message: 'User not authenticated' });
                }
                const chat = await this.chatService.getOrCreateChat(userId, otherUserId);
                res.status(201).json(chat);
            }
            catch (error) {
                res.status(500).json({ message: 'Error creating chat' });
            }
        };
        this.getChatMessages = async (req, res) => {
            try {
                const chatId = req.params.id;
                const messages = await this.chatService.getMessages(chatId);
                res.json(messages);
            }
            catch (error) {
                res.status(500).json({ message: 'Error fetching messages' });
            }
        };
        this.sendMessage = async (req, res) => {
            var _a;
            try {
                const chatId = req.params.id;
                const { content } = req.body;
                const senderId = (_a = req.user) === null || _a === void 0 ? void 0 : _a._id.toString();
                if (!senderId) {
                    return res.status(401).json({ message: 'User not authenticated' });
                }
                const message = await this.chatService.sendMessage(chatId, senderId, content);
                res.status(201).json(message);
            }
            catch (error) {
                res.status(500).json({ message: 'Error sending message' });
            }
        };
        this.markMessageAsRead = async (req, res) => {
            try {
                const messageId = req.params.messageId;
                const message = await this.chatService.markMessageAsRead(messageId);
                res.json(message);
            }
            catch (error) {
                res.status(500).json({ message: 'Error marking message as read' });
            }
        };
        this.markAllMessagesAsRead = async (req, res) => {
            var _a;
            try {
                const chatId = req.params.id;
                const userId = (_a = req.user) === null || _a === void 0 ? void 0 : _a._id.toString();
                if (!userId) {
                    return res.status(401).json({ message: 'User not authenticated' });
                }
                await this.chatService.markAllMessagesAsRead(chatId, userId);
                res.json({ message: 'All messages marked as read' });
            }
            catch (error) {
                res.status(500).json({ message: 'Error marking messages as read' });
            }
        };
        this.chatService = new ChatService_1.ChatService();
    }
}
exports.ChatController = ChatController;
