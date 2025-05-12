import { Request, Response } from "express";
import { AuthService } from "@domain/services/AuthService";
import { MongoUserRepository } from "@infrastructure/database/repositories/MongoUserRepository";

const authService = new AuthService(new MongoUserRepository());

export class AuthController {
  async register(req: Request, res: Response) {
    try {
      console.log("here");
      const { email, password, fullName } = req.body;
      const user = await authService.register({ email, password, fullName });
      res.status(201).json({ message: "User registered successfully", user });
    } catch (error: any) {
      res
        .status(400)
        .json({ message: error?.message || "Registration failed" });
    }
  }

  async login(req: Request, res: Response) {
    try {
      const { email, password } = req.body;
      const result = await authService.login(email, password);
      res.json(result);
    } catch (error: any) {
      res.status(401).json({ message: error?.message || "Login failed" });
    }
  }

  async requestPasswordReset(req: Request, res: Response) {
    try {
      const { email } = req.body;
      // Find user
      const userRepo = new MongoUserRepository();
      const user = await userRepo.findByEmail(email);
      if (!user) {
        return res.status(404).json({ message: "User not found" });
      }
      // Generate 4-digit code
      const code = Math.floor(1000 + Math.random() * 9000).toString();
      const expires = new Date(Date.now() + 10 * 60 * 1000); // 10 minutes
      // Update user with code and expiration
      await userRepo.update(user.id, {
        resetCode: code,
        resetCodeExpires: expires,
      });
      // Return code in response (for local dev)
      res.json({
        resetCode: code,
        message: "Reset code generated. Use this code to reset your password.",
      });
    } catch (error: any) {
      res
        .status(500)
        .json({ message: error?.message || "Failed to generate reset code" });
    }
  }

  async resetPassword(req: Request, res: Response) {
    try {
      const { email, code, newPassword } = req.body;
      const userRepo = new MongoUserRepository();
      const user = await userRepo.findByEmail(email);
      if (!user) {
        return res.status(404).json({ message: "User not found" });
      }
      // Check code and expiration
      if (
        user.resetCode !== code ||
        !user.resetCodeExpires ||
        new Date(user.resetCodeExpires) < new Date()
      ) {
        return res.status(400).json({ message: "Invalid or expired code" });
      }
      // Update password and clear code
      const bcrypt = require("bcryptjs");
      const hashed = await bcrypt.hash(newPassword, 10);
      await userRepo.update(user.id, {
        password: hashed,
        resetCode: undefined,
        resetCodeExpires: undefined,
      });
      res.json({ message: "Password reset successful" });
    } catch (error: any) {
      res
        .status(500)
        .json({ message: error?.message || "Failed to reset password" });
    }
  }

  async changePassword(req: Request, res: Response) {
    try {
      const userId = req.user?._id;
      console.log("User ID from request (ObjectId):", userId);
      console.log("User ID as string:", userId?.toString());

      if (!userId) {
        return res.status(401).json({ message: "User not authenticated" });
      }

      const { oldPassword, newPassword } = req.body;
      const userRepo = new MongoUserRepository();

      // Always use string for findById
      const user = await userRepo.findById(userId.toString());
      console.log("Found user object from repo:", user);
      if (user) {
        console.log("User id from repo:", user.id);
        console.log("User email from repo:", user.email);
      } else {
        console.log("No user found with ID:", userId.toString());
        // Let's check if the user exists by email
        const userByEmail = await userRepo.findByEmail(req.user?.email || "");
        console.log("User found by email:", userByEmail);
      }

      if (!user) {
        return res.status(404).json({ message: "User not found" });
      }

      const bcrypt = require("bcryptjs");
      const isMatch = await bcrypt.compare(oldPassword, user.password);
      if (!isMatch) {
        return res.status(400).json({ message: "Old password is incorrect" });
      }

      const hashed = await bcrypt.hash(newPassword, 10);
      // Use user.id for update
      const updatedUser = await userRepo.update(user.id, { password: hashed });
      console.log("Updated user:", updatedUser);

      res.json({ message: "Password changed successfully" });
    } catch (error: any) {
      console.error("Change password error:", error);
      res
        .status(500)
        .json({ message: error?.message || "Failed to change password" });
    }
  }

  async deleteAccount(req: Request, res: Response) {
    try {
      const userId = req.user?._id;
      if (!userId) {
        return res.status(401).json({ message: "User not authenticated" });
      }
      const userRepo = new MongoUserRepository();
      const deleted = await userRepo.delete(userId.toString());
      if (deleted) {
        res.json({ message: "Account deleted successfully" });
      } else {
        res.status(404).json({ message: "User not found" });
      }
    } catch (error: any) {
      res
        .status(500)
        .json({ message: error?.message || "Failed to delete account" });
    }
  }

  async updateProfile(req: Request, res: Response) {
    try {
      const userId = req.user?._id;
      if (!userId) {
        return res.status(401).json({ message: "User not authenticated" });
      }
      const { fullName, email } = req.body;
      const userRepo = new MongoUserRepository();
      const updatedUser = await userRepo.update(userId.toString(), {
        fullName,
        email,
      });
      if (updatedUser) {
        res.json({
          message: "Profile updated successfully",
          user: updatedUser,
        });
      } else {
        res.status(404).json({ message: "User not found" });
      }
    } catch (error: any) {
      res
        .status(500)
        .json({ message: error?.message || "Failed to update profile" });
    }
  }

  async debugListUsers(req: Request, res: Response) {
    try {
      const userRepo = new MongoUserRepository();
      const users = await userRepo.findAll();
      res.json(
        users.map((u) => ({ id: u.id, email: u.email, fullName: u.fullName }))
      );
    } catch (error: any) {
      res
        .status(500)
        .json({ message: error?.message || "Failed to list users" });
    }
  }
}
