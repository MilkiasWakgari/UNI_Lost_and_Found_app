"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const ChatController_1 = require("../../controllers/ChatController");
const auth_1 = require("../../infrastructure/middleware/auth");
const router = express_1.default.Router();
const chatController = new ChatController_1.ChatController();
// Get all chats for a user
router.get('/', auth_1.authMiddleware, chatController.getUserChats);
// Get a specific chat
router.get('/:id', auth_1.authMiddleware, chatController.getChatById);
// Create a new chat
router.post('/create', auth_1.authMiddleware, chatController.createChat);
// Get messages for a chat
router.get('/:id/messages', auth_1.authMiddleware, chatController.getChatMessages);
// Send a message
router.post('/:id/messages', auth_1.authMiddleware, chatController.sendMessage);
// Mark message as read
router.put('/messages/:messageId/read', auth_1.authMiddleware, chatController.markMessageAsRead);
// Mark all messages as read
router.put('/:id/messages/read', auth_1.authMiddleware, chatController.markAllMessagesAsRead);
exports.default = router;
