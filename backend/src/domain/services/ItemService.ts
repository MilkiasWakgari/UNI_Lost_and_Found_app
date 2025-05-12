import { IItem } from '../models/Item';
import { ItemRepository } from '../repositories/ItemRepository';
import mongoose from 'mongoose';

export class ItemService {
    private itemRepository: ItemRepository;

    constructor() {
        this.itemRepository = new ItemRepository();
    }

    async createItem(itemData: Partial<IItem>): Promise<IItem> {
        return await this.itemRepository.create(itemData);
    }

    async getAllItems(): Promise<IItem[]> {
        return await this.itemRepository.findAll();
    }

    async getItemById(id: string): Promise<IItem | null> {
        return await this.itemRepository.findById(id);
    }

    async getItemsByFoundBy(userId: string): Promise<IItem[]> {
        return await this.itemRepository.findByFoundBy(userId);
    }

    async getItemsByClaimedBy(userId: string): Promise<IItem[]> {
        return await this.itemRepository.findByClaimedBy(userId);
    }

    async updateItem(id: string, itemData: Partial<IItem>): Promise<IItem | null> {
        const item = await this.itemRepository.findById(id);
        if (!item) {
            throw new Error('Item not found');
        }
        return await this.itemRepository.update(id, itemData);
    }

    async deleteItem(id: string): Promise<IItem | null> {
        const item = await this.itemRepository.findById(id);
        if (!item) {
            throw new Error('Item not found');
        }
        return await this.itemRepository.delete(id);
    }

    async getRecentItems(days: number = 7): Promise<IItem[]> {
        return await this.itemRepository.findRecentItems(days);
    }

    async getTodayItems(): Promise<IItem[]> {
        return await this.itemRepository.findTodayItems();
    }

    async claimItem(itemId: string, userId: string): Promise<IItem | null> {
        const item = await this.itemRepository.findById(itemId);
        if (!item) {
            throw new Error('Item not found');
        }
        if (item.status !== 'found') {
            throw new Error('Item is not available for claiming');
        }
        return await this.itemRepository.update(itemId, {
            status: 'claimed',
            claimedBy: new mongoose.Types.ObjectId(userId)
        });
    }

    async markAsReturned(itemId: string): Promise<IItem | null> {
        const item = await this.itemRepository.findById(itemId);
        if (!item) {
            throw new Error('Item not found');
        }
        if (item.status !== 'claimed') {
            throw new Error('Item must be claimed before marking as returned');
        }
        return await this.itemRepository.update(itemId, {
            status: 'returned'
        });
    }
} 