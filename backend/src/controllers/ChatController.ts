import { Request, Response } from 'express';
import { ChatService } from '../domain/services/ChatService';

export class ChatController {
    private chatService: ChatService;

    constructor() {
        this.chatService = new ChatService();
    }

    getUserChats = async (req: Request, res: Response) => {
        try {
            const userId = req.user?._id.toString();
            if (!userId) {
                return res.status(401).json({ message: 'User not authenticated' });
            }
            const chats = await this.chatService.getChats(userId);
            res.json(chats);
        } catch (error) {
            res.status(500).json({ message: 'Error fetching chats' });
        }
    };

    getChatById = async (req: Request, res: Response) => {
        try {
            const chatId = req.params.id;
            const chat = await this.chatService.getChatById(chatId);
            if (!chat) {
                return res.status(404).json({ message: 'Chat not found' });
            }
            res.json(chat);
        } catch (error) {
            res.status(500).json({ message: 'Error fetching chat' });
        }
    };

    createChat = async (req: Request, res: Response) => {
        try {
            const { otherUserId } = req.body;
            const userId = req.user?._id.toString();
            if (!userId) {
                return res.status(401).json({ message: 'User not authenticated' });
            }
            const chat = await this.chatService.getOrCreateChat(userId, otherUserId);
            res.status(201).json(chat);
        } catch (error) {
            res.status(500).json({ message: 'Error creating chat' });
        }
    };

    getChatMessages = async (req: Request, res: Response) => {
        try {
            const chatId = req.params.id;
            const messages = await this.chatService.getMessages(chatId);
            res.json(messages);
        } catch (error) {
            res.status(500).json({ message: 'Error fetching messages' });
        }
    };

    sendMessage = async (req: Request, res: Response) => {
        try {
            const chatId = req.params.id;
            const { content } = req.body;
            const senderId = req.user?._id.toString();
            if (!senderId) {
                return res.status(401).json({ message: 'User not authenticated' });
            }
            const message = await this.chatService.sendMessage(chatId, senderId, content);
            res.status(201).json(message);
        } catch (error) {
            res.status(500).json({ message: 'Error sending message' });
        }
    };

    markMessageAsRead = async (req: Request, res: Response) => {
        try {
            const messageId = req.params.messageId;
            const message = await this.chatService.markMessageAsRead(messageId);
            res.json(message);
        } catch (error) {
            res.status(500).json({ message: 'Error marking message as read' });
        }
    };

    markAllMessagesAsRead = async (req: Request, res: Response) => {
        try {
            const chatId = req.params.id;
            const userId = req.user?._id.toString();
            if (!userId) {
                return res.status(401).json({ message: 'User not authenticated' });
            }
            await this.chatService.markAllMessagesAsRead(chatId, userId);
            res.json({ message: 'All messages marked as read' });
        } catch (error) {
            res.status(500).json({ message: 'Error marking messages as read' });
        }
    };
} 