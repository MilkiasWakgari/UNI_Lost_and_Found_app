import Chat, { IChat } from '../models/Chat';
import Message, { IMessage } from '../models/Message';
import { Types } from 'mongoose';

export class ChatRepository {
    async getChats(userId: string): Promise<IChat[]> {
        return Chat.find({ participants: userId })
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

    async getChatById(chatId: string): Promise<IChat | null> {
        return Chat.findById(chatId)
            .populate('participants', 'fullName email profilePicture')
            .populate({
                path: 'lastMessage',
                populate: {
                    path: 'sender',
                    select: 'fullName'
                }
            });
    }

    async getOrCreateChat(userId: string, otherUserId: string): Promise<IChat> {
        const existingChat = await Chat.findOne({
            participants: { $all: [userId, otherUserId] }
        });

        if (existingChat) {
            return existingChat;
        }

        const newChat = new Chat({
            participants: [userId, otherUserId]
        });

        return newChat.save();
    }

    async getMessages(chatId: string): Promise<IMessage[]> {
        return Message.find({ chat: chatId })
            .populate('sender', 'fullName')
            .sort({ createdAt: 1 });
    }

    async createMessage(chatId: string, senderId: string, content: string): Promise<IMessage> {
        const message = new Message({
            chat: chatId,
            sender: senderId,
            content
        });

        const savedMessage = await message.save();

        // Update chat's last message
        await Chat.findByIdAndUpdate(chatId, {
            lastMessage: savedMessage._id
        });

        return savedMessage.populate('sender', 'fullName');
    }

    async markMessageAsRead(messageId: string): Promise<IMessage | null> {
        return Message.findByIdAndUpdate(
            messageId,
            { isRead: true },
            { new: true }
        );
    }

    async markAllMessagesAsRead(chatId: string, userId: string): Promise<void> {
        await Message.updateMany(
            {
                chat: chatId,
                sender: { $ne: userId },
                isRead: false
            },
            { isRead: true }
        );
    }
} 