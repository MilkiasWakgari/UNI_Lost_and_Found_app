"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.ChatRepository = void 0;
const Chat_1 = __importDefault(require("../models/Chat"));
const Message_1 = __importDefault(require("../models/Message"));
class ChatRepository {
    async getChats(userId) {
        return Chat_1.default.find({ participants: userId })
            .populate('participants', 'fullName email profilePicture')
            .populate({
            path: 'lastMessage',
            populate: {
                path: 'sender',
                select: 'fullName'
            }
        })
            .sort({ updatedAt: -1 });
    }
    async getChatById(chatId) {
        return Chat_1.default.findById(chatId)
            .populate('participants', 'fullName email profilePicture')
            .populate({
            path: 'lastMessage',
            populate: {
                path: 'sender',
                select: 'fullName'
            }
        });
    }
    async getOrCreateChat(userId, otherUserId) {
        const existingChat = await Chat_1.default.findOne({
            participants: { $all: [userId, otherUserId] }
        });
        if (existingChat) {
            return existingChat;
        }
        const newChat = new Chat_1.default({
            participants: [userId, otherUserId]
        });
        return newChat.save();
    }
    async getMessages(chatId) {
        return Message_1.default.find({ chat: chatId })
            .populate('sender', 'fullName')
            .sort({ createdAt: 1 });
    }
    async createMessage(chatId, senderId, content) {
        const message = new Message_1.default({
            chat: chatId,
            sender: senderId,
            content
        });
        const savedMessage = await message.save();
        // Update chat's last message
        await Chat_1.default.findByIdAndUpdate(chatId, {
            lastMessage: savedMessage._id
        });
        return savedMessage.populate('sender', 'fullName');
    }
    async markMessageAsRead(messageId) {
        return Message_1.default.findByIdAndUpdate(messageId, { isRead: true }, { new: true });
    }
    async markAllMessagesAsRead(chatId, userId) {
        await Message_1.default.updateMany({
            chat: chatId,
            sender: { $ne: userId },
            isRead: false
        }, { isRead: true });
    }
}
exports.ChatRepository = ChatRepository;
