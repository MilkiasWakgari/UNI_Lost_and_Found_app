import bcrypt from 'bcryptjs';
import jwt, { SignOptions } from 'jsonwebtoken';
import { IUser } from '../entities/User';
import { IUserRepository } from '../repositories/IUserRepository';

export class AuthService {
  constructor(
    private userRepository: IUserRepository,
    private jwtSecret: string = process.env.JWT_SECRET || 'your_jwt_secret_key_here',
    private jwtExpiresIn: string = process.env.JWT_EXPIRES_IN || '7d'
  ) {}

  async register(userData: { email: string; password: string; fullName: string }): Promise<IUser> {
    const existingUser = await this.userRepository.findByEmail(userData.email);
    if (existingUser) {
      throw new Error('User already exists');
    }

    const hashedPassword = await bcrypt.hash(userData.password, 10);
    const isAdmin = userData.email === 'admin@example.com'; // Set your admin email here
    const user = await this.userRepository.create({
      ...userData,
      password: hashedPassword,
      role: isAdmin ? 'admin' : 'user'
    } as IUser);

    return user;
  }

  async login(email: string, password: string): Promise<{ user: IUser; token: string }> {
    const user = await this.userRepository.findByEmail(email);
    if (!user) {
      throw new Error('User not found');
    }

    const isValidPassword = await bcrypt.compare(password, user.password);
    if (!isValidPassword) {
      throw new Error('Invalid password');
    }

    const signOptions: SignOptions = { expiresIn: this.jwtExpiresIn as any };
    const token = jwt.sign(
      { _id: user.id, email: user.email, role: user.role },
      this.jwtSecret,
      signOptions
    );

    return { user, token };
  }

  async verifyToken(token: string): Promise<IUser> {
    try {
      const decoded = jwt.verify(token, this.jwtSecret) as { userId: string };
      const user = await this.userRepository.findById(decoded.userId);
      if (!user) {
        throw new Error('User not found');
      }
      return user;
    } catch (error) {
      throw new Error('Invalid token');
    }
  }
} 