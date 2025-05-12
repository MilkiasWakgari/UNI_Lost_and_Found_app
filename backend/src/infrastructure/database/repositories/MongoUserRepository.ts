import { IUser } from '@domain/entities/User';
import { IUserRepository } from '@domain/repositories/IUserRepository';
import mongoose from 'mongoose';

const userSchema = new mongoose.Schema({
  email: { type: String, required: true, unique: true },
  password: { type: String, required: true },
  fullName: { type: String, required: true },
  role: { type: String, enum: ['user', 'admin'], default: 'user' },
  createdAt: { type: Date, default: Date.now },
  updatedAt: { type: Date, default: Date.now },
  resetCode: { type: String },
  resetCodeExpires: { type: Date }
});

const UserModel = mongoose.model('User', userSchema);

export class MongoUserRepository implements IUserRepository {
  async create(user: IUser): Promise<IUser> {
    const newUser = new UserModel(user);
    const savedUser = await newUser.save();
    return this.mapToEntity(savedUser);
  }

  async findById(id: string): Promise<IUser | null> {
    const user = await UserModel.findById(id);
    return user ? this.mapToEntity(user) : null;
  }

  async findByEmail(email: string): Promise<IUser | null> {
    const user = await UserModel.findOne({ email });
    return user ? this.mapToEntity(user) : null;
  }

  async update(id: string, userData: Partial<IUser>): Promise<IUser | null> {
    const updatedUser = await UserModel.findByIdAndUpdate(
      id,
      { ...userData, updatedAt: new Date() },
      { new: true }
    );
    return updatedUser ? this.mapToEntity(updatedUser) : null;
  }

  async delete(id: string): Promise<boolean> {
    const result = await UserModel.findByIdAndDelete(id);
    return !!result;
  }

  async findAll(): Promise<IUser[]> {
    const users = await UserModel.find();
    return users.map(this.mapToEntity);
  }

  private mapToEntity(user: any): IUser {
    return {
      id: user._id.toString(),
      email: user.email,
      password: user.password,
      fullName: user.fullName,
      role: user.role,
      createdAt: user.createdAt,
      updatedAt: user.updatedAt
    };
  }
} 