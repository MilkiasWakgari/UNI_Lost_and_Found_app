import { Request, Response, NextFunction } from "express";
import { ItemService } from "@domain/services/ItemService";
import mongoose from "mongoose";

export class ItemController {
  private itemService: ItemService;

  constructor() {
    this.itemService = new ItemService();
  }

  async createItem(req: Request, res: Response, next?: NextFunction) {
    try {
      //   if (!req.user) {
      //     return res.status(401).json({ message: "User not authenticated" });
      //   }
      const itemData = {
        ...req.body,
        foundBy: new mongoose.Types.ObjectId("682227ec446143734e3a3ed4"),
      };

      const item = await this.itemService.createItem(itemData);
      res.status(201).json(item);
    } catch (error: any) {
      console.log(error);
      res.status(500).json({ message: "Error creating item" });
    }
  }

  async getAllItems(req: Request, res: Response, next?: NextFunction) {
    try {
      const items = await this.itemService.getAllItems();
      res.json(items);
    } catch (error: any) {
      res
        .status(500)
        .json({ message: error?.message || "Failed to fetch items" });
    }
  }

  async getItemById(req: Request, res: Response, next?: NextFunction) {
    try {
      const item = await this.itemService.getItemById(req.params.id);
      if (!item) {
        return res.status(404).json({ message: "Item not found" });
      }
      res.json(item);
    } catch (error: any) {
      res
        .status(500)
        .json({ message: error?.message || "Failed to fetch item" });
    }
  }

  async getItemsByFoundBy(req: Request, res: Response, next?: NextFunction) {
    try {
      if (!req.user) {
        return res.status(401).json({ message: "User not authenticated" });
      }
      const items = await this.itemService.getItemsByFoundBy(
        req.user._id.toString()
      );
      res.json(items);
    } catch (error: any) {
      res.status(500).json({ message: "Error fetching items" });
    }
  }

  async getItemsByClaimedBy(req: Request, res: Response, next?: NextFunction) {
    try {
      if (!req.user) {
        return res.status(401).json({ message: "User not authenticated" });
      }
      const items = await this.itemService.getItemsByClaimedBy(
        req.user._id.toString()
      );
      res.json(items);
    } catch (error: any) {
      res.status(500).json({ message: "Error fetching items" });
    }
  }

  async updateItem(req: Request, res: Response, next?: NextFunction) {
    try {
      const item = await this.itemService.updateItem(req.params.id, req.body);
      if (!item) {
        return res.status(404).json({ message: "Item not found" });
      }
      res.json(item);
    } catch (error: any) {
      res
        .status(400)
        .json({ message: error?.message || "Failed to update item" });
    }
  }

  async deleteItem(req: Request, res: Response, next?: NextFunction) {
    try {
      const item = await this.itemService.getItemById(req.params.id);
      if (!item) {
        return res.status(404).json({ message: "Item not found" });
      }
      // Allow if user is owner or admin
      if (
        req.user &&
        (req.user._id.equals(item.foundBy) || req.user.role === "admin")
      ) {
        await this.itemService.deleteItem(req.params.id);
        return res.json({ message: "Item deleted successfully" });
      }
      return res
        .status(403)
        .json({ message: "Not authorized to delete this item" });
    } catch (error: any) {
      res
        .status(500)
        .json({ message: error?.message || "Failed to delete item" });
    }
  }

  async getRecentItems(req: Request, res: Response, next?: NextFunction) {
    try {
      const days = parseInt(req.query.days as string) || 7;
      const items = await this.itemService.getRecentItems(days);
      res.json(items);
    } catch (error: any) {
      res
        .status(500)
        .json({ message: error?.message || "Failed to fetch recent items" });
    }
  }

  async getTodayItems(req: Request, res: Response, next?: NextFunction) {
    try {
      const items = await this.itemService.getTodayItems();
      res.json(items);
    } catch (error: any) {
      res
        .status(500)
        .json({ message: error?.message || "Failed to fetch today's items" });
    }
  }

  async claimItem(req: Request, res: Response, next?: NextFunction) {
    try {
      if (!req.user) {
        return res.status(401).json({ message: "User not authenticated" });
      }
      const item = await this.itemService.claimItem(
        req.params.id,
        req.user._id.toString()
      );
      if (!item) {
        return res.status(404).json({ message: "Item not found" });
      }
      res.json(item);
    } catch (error: any) {
      res.status(500).json({ message: "Error claiming item" });
    }
  }

  async markAsReturned(req: Request, res: Response, next?: NextFunction) {
    try {
      const item = await this.itemService.markAsReturned(req.params.id);
      if (!item) {
        return res.status(404).json({ message: "Item not found" });
      }
      res.json(item);
    } catch (error: any) {
      res
        .status(400)
        .json({ message: error?.message || "Failed to mark item as returned" });
    }
  }
}
