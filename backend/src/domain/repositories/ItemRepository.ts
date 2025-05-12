import { IItem } from "../models/Item";
import { Item } from "../models/Item";

export class ItemRepository {
  async create(itemData: Partial<IItem>): Promise<IItem> {
    console.log(itemData);
    const item = new Item(itemData);
    return await item.save();
  }

  async findAll(): Promise<IItem[]> {
    return await Item.find()
      .populate("foundBy", "fullName email")
      .populate("claimedBy", "fullName email")
      .sort({ dateFound: -1 });
  }

  async findById(id: string): Promise<IItem | null> {
    return await Item.findById(id)
      .populate("foundBy", "fullName email")
      .populate("claimedBy", "fullName email");
  }

  async findByFoundBy(userId: string): Promise<IItem[]> {
    return await Item.find({ foundBy: userId })
      .populate("foundBy", "fullName email")
      .populate("claimedBy", "fullName email")
      .sort({ dateFound: -1 });
  }

  async findByClaimedBy(userId: string): Promise<IItem[]> {
    return await Item.find({ claimedBy: userId })
      .populate("foundBy", "fullName email")
      .populate("claimedBy", "fullName email")
      .sort({ dateFound: -1 });
  }

  async update(id: string, itemData: Partial<IItem>): Promise<IItem | null> {
    return await Item.findByIdAndUpdate(id, itemData, { new: true })
      .populate("foundBy", "fullName email")
      .populate("claimedBy", "fullName email");
  }

  async delete(id: string): Promise<IItem | null> {
    return await Item.findByIdAndDelete(id);
  }

  async findRecentItems(days: number = 7): Promise<IItem[]> {
    const date = new Date();
    date.setDate(date.getDate() - days);

    return await Item.find({
      dateFound: { $gte: date },
    })
      .populate("foundBy", "fullName email")
      .populate("claimedBy", "fullName email")
      .sort({ dateFound: -1 });
  }

  async findTodayItems(): Promise<IItem[]> {
    const today = new Date();
    today.setHours(0, 0, 0, 0);

    return await Item.find({
      dateFound: { $gte: today },
    })
      .populate("foundBy", "fullName email")
      .populate("claimedBy", "fullName email")
      .sort({ dateFound: -1 });
  }
}
