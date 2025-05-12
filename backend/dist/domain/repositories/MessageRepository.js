"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.MessageRepository = void 0;
const Message_1 = __importDefault(require("@domain/models/Message"));
class MessageRepository {
    async findById(id) {
        return Message_1.default.findById(id);
    }
    async findByChatId(chatId) {
        return Message_1.default.find({ chatId })
            .sort({ timestamp: 1 });
    }
    async create(data) {
        const message = new Message_1.default(data);
        return message.save();
    }
    async markAsRead(messageId) {
        return Message_1.default.findByIdAndUpdate(messageId, { isRead: true }, { new: true });
    }
    async markAllAsRead(chatId, userId) {
        await Message_1.default.updateMany({
            chatId,
            sender: { $ne: userId },
            isRead: false
        }, { isRead: true });
    }
    async delete(id) {
        return Message_1.default.findByIdAndDelete(id);
    }
}
exports.MessageRepository = MessageRepository;
