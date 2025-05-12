import express from 'express';
import { ChatController } from '../../controllers/ChatController';
import { authMiddleware } from '../../infrastructure/middleware/auth';

const router = express.Router();
const chatController = new ChatController();

// Get all chats for a user
router.get('/', authMiddleware, chatController.getUserChats);

// Get a specific chat
router.get('/:id', authMiddleware, chatController.getChatById);

// Create a new chat
router.post('/create', authMiddleware, chatController.createChat);

// Get messages for a chat
router.get('/:id/messages', authMiddleware, chatController.getChatMessages);

// Send a message
router.post('/:id/messages', authMiddleware, chatController.sendMessage);

// Mark message as read
router.put('/messages/:messageId/read', authMiddleware, chatController.markMessageAsRead);

// Mark all messages as read
router.put('/:id/messages/read', authMiddleware, chatController.markAllMessagesAsRead);

export default router; 