import Message, { IMessage } from '@domain/models/Message';

export class MessageRepository {
    async findById(id: string): Promise<IMessage | null> {
        return Message.findById(id);
    }

    async findByChatId(chatId: string): Promise<IMessage[]> {
        return Message.find({ chatId })
            .sort({ timestamp: 1 });
    }

    async create(data: Partial<IMessage>): Promise<IMessage> {
        const message = new Message(data);
        return message.save();
    }

    async markAsRead(messageId: string): Promise<IMessage | null> {
        return Message.findByIdAndUpdate(
            messageId,
            { isRead: true },
            { new: true }
        );
    }

    async markAllAsRead(chatId: string, userId: string): Promise<void> {
        await Message.updateMany(
            {
                chatId,
                sender: { $ne: userId },
                isRead: false
            },
            { isRead: true }
        );
    }

    async delete(id: string): Promise<IMessage | null> {
        return Message.findByIdAndDelete(id);
    }
} 